import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from "./axios";
import { Avatar, Checkbox, List, ListItem, ListItemIcon, ListItemText } from "@mui/material";
import BookItem from "./BookItem";

const HomeClient = (props) => {
    const [email, setEmail] = useState("");
    const [books, setBooks] = useState([]);
    const [currencies, setCurrencies] = useState({});
    const [currencyOptions, setCurrencyOptions] = useState([]);
    const [selectedCurrency, setSelectedCurrency] = useState("");
    const [selectedCurrencyValue, setSelectedCurrencyValue] = useState(1);
    const [checked, setChecked] = useState(false);
    const [subscriptionLoaded, setSubscriptionLoaded] = useState(false);
    const [showBooksError, setShowBooksError] = useState(false);

    const params = useParams();

    const [message, setMessage] = useState("");

    useEffect(() => {
        setEmail(params.email);
        axiosInstance
            .get("/book/getAllAvailable")
            .then(res => {
                const val = res.data;
                setBooks(val);
            })
            .catch(error => {
                console.log(error);
                setShowBooksError(true);
            });

        axiosInstance
            .get("/book/currency")
            .then(res => {
                const val = res.data;
                setCurrencies(val);
                setCurrencyOptions(Object.keys(val));
            })
            .catch(error => {
                console.log(error);
            });

        axiosInstance
            .get("/user/getSubscription", {
                params: {
                    email: params.email
                }
            })
            .then(res => {
                const val = res.data;
                setChecked(val);
                setSubscriptionLoaded(true);
                console.log("Subscription status:", val);
            })
            .catch(error => {
                console.log(error);
                setSubscriptionLoaded(true);
            });

    }, [params.email]);

    const handleCurrencyChange = (event) => {
        setSelectedCurrency(event.target.value);
        setSelectedCurrencyValue(currencies[event.target.value]);
     }

    const handleChange = () => {
        const isChecked = !checked;
        setChecked(isChecked);

        let credentials = {
            email: email,
            ok: isChecked
        }

        axiosInstance
            .put("/user/subscribe",  credentials)
            .then(res => {
                console.log(res.data);
            })
            .catch(error => {
                console.log(error);
            });
    }

    if (!subscriptionLoaded) {
        return <div>Loading...</div>;
    }

    return (
        <React.Fragment>
            <h1>Hello, {email}!</h1>
            <label>
                <input
                    type="checkbox"
                    checked={checked}
                    onChange={handleChange}
                />
                Subscribed to Newsletter
            </label>
            {showBooksError ? <div>No books available</div> : <div></div>}
            {message && <div>{message}</div>}
            <select value={selectedCurrency} onChange={handleCurrencyChange}>
                {currencyOptions.map(currency => (
                    <option key={currency} value={currency}>{currency}</option>
                ))}
            </select>
            <List>
                {books.map(book => (
                    <BookItem key={book.id} book={book} selectedCurrencyValue={selectedCurrencyValue} />
                ))}
            </List>
        </React.Fragment>
    );
}

export default HomeClient;