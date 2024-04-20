// import React from 'react'
// import {useNavigate, useParams} from 'react-router-dom'
// import axiosInstance from "./axios";
//
// import {Avatar, Checkbox, List, ListItem, ListItemIcon, ListItemText} from "@mui/material";
// import BookItem from "./BookItem";
//
// class HomeClient extends React.Component {
//     constructor(props) {
//         super(props)
//         this.state = {
//             email: this.props.email,
//             books: [],
//             currencies: {},
//             currencyOptions: [],
//             selectedCurrency: "",
//             selectedCurrencyValue: 1,
//             checked: false,
//         }
//     };
//
//
//     componentDidMount() {
//          const { em } = useParams();
//         this.setState({ email: em });
//         axiosInstance
//             .get(
//                 "/book/getAllAvailable",
//             )
//             .then(res => {
//                 const val = res.data;
//                 this.setState({
//                     books: val
//                 });
//                 console.log(val);
//                 console.log(this.state.books);
//             })
//             .catch(error => {
//                 console.log(error);
//                 this.setState({
//                     showBooksError: true
//                 });
//             });
//
//         axiosInstance
//             .get(
//                 "/book/currency",
//             )
//             .then(res => {
//                 const val = res.data;
//                 this.setState({
//                     currencies: val,
//                     currencyOptions: Object.keys(val)
//                 });
//                 console.log(val);
//                 console.log(this.state.currencies);
//             })
//             .catch(error => {
//                 console.log(error);
//             });
//     };
//
//     handleCurrencyChange = (event) => {
//         this.setState({ selectedCurrency: event.target.value, selectedCurrencyValue: this.state.currencies[event.target.value] });
//     }
//
//     handleChange = (event) => {
//         // this.state.checked = !this.state.checked;
//         this.setState(prevState => ({ checked: !prevState.checked }));
//     }
//
//     render() {
//
//         return (
//             <React.Fragment>
//                 <h1>Hello, {this.state.email}!</h1>
//                 <label>
//                     <input
//                         type="checkbox"
//                         checked={this.state.checked}
//                         onChange={this.handleChange}
//                     />
//                     Subscribed to Newsletter
//                 </label>
//                 {this.state.showBooksError ? <div>No books available</div> : <div></div>}
//                 <select value={this.state.selectedCurrency} onChange={this.handleCurrencyChange}>
//                     {this.state.currencyOptions.map(currency => (
//                         <option key={currency} value={currency}>{currency}</option>
//                     ))}
//                 </select>
//                 <List>
//                     {this.state.books.map(book => (
//                         <BookItem book={book} selectedCurrencyValue={this.state.selectedCurrencyValue}/>
//                     ))}
//                 </List>
//             </React.Fragment>
//         )
//     }
// }
//
// export default HomeClient;


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
            .get("/user/getSubscription")
            .then(res => {
                const val = res.data;
                setChecked(val);
            })
            .catch(error => {
                console.log(error);
            });

    }, [params.email]);

    const handleCurrencyChange = (event) => {
        setSelectedCurrency(event.target.value);
        setSelectedCurrencyValue(currencies[event.target.value]);
    }

    const handleChange = () => {
        const isChecked = !checked;
        setChecked(isChecked);

        axiosInstance
            .put("/user/subscribe", { email: params.email, ok: isChecked })
            .then(res => {
                console.log(res.data);
            })
            .catch(error => {
                console.log(error);
            });
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