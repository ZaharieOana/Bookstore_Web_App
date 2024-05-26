import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from "./axios";
import { Avatar, List, ListItem, ListItemIcon, ListItemText, Switch } from "@mui/material";
import BookItem from "./BookItem";
import Button from "@mui/material/Button";
import history from "./history";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";

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
    const [bookTypes, setBookTypes] = useState([]);
    const [selectedBookType, setSelectedBookType] = useState("all");
    const [toggleDarkMode, setToggleDarkMode] = useState(true);
    const [cart, setCart] = useState([]);

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

        axiosInstance
            .get("/type/getAll")
            .then(res => {
                setBookTypes(res.data);
            })
            .catch(error => {
                console.log(error);
            });

    }, [params.email]);

    const handleCurrencyChange = (event) => {
        setSelectedCurrency(event.target.value);
        setSelectedCurrencyValue(currencies[event.target.value]);
    }

    const handleTypeFilterChange = (event) => {
        setSelectedBookType(event.target.value);
    };

    const logout = () => {
        axiosInstance
            .put("/user/logout", null, {
                params: {
                    email: params.email
                } })
            .then(res => {
                history.push("/log-in");
                window.location.reload();
            })
            .catch(error => {
                console.log(error);
            });
    }

    const gotoCart = () => {
        history.push(`/cart/${params.email}`);
        window.location.reload();
    }

    const handleChange = () => {
        const isChecked = !checked;
        setChecked(isChecked);

        let credentials = {
            email: email,
            ok: isChecked
        }

        axiosInstance
            .put("/user/subscribe", credentials)
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

    let filteredBooks = books;
    if (selectedBookType !== "all") {
        filteredBooks = books.filter(book => book.type === selectedBookType);
    }

    const toggleDarkTheme = () => {
        setToggleDarkMode(!toggleDarkMode);
    };

    const darkTheme = createTheme({
        palette: {
            mode: toggleDarkMode ? 'dark' : 'light',
            background: {
                default: toggleDarkMode ? '#121212' : '#ffffff',
                paper: toggleDarkMode ? '#121212' : '#ffffff',
            },
        },
    });

    const addToCart = (book) => {
        setCart([...cart, book]);
    };

    return (
        <ThemeProvider theme={darkTheme}>
            <CssBaseline />
            <div style={{ padding: '20px' }}>
                <Switch checked={toggleDarkMode} onChange={toggleDarkTheme} />
                <h1>Hello, {email}!</h1>
                <label>
                    <input
                        type="checkbox"
                        checked={checked}
                        onChange={handleChange}
                    />
                    Subscribed to Newsletter
                </label>
                <div>
                    <Button
                        onClick={logout}
                        type="button"
                        variant="contained"
                        color="primary"
                        style={{
                            backgroundColor: "darkred",
                            marginBottom: '20px',
                            marginTop: '20px',
                            marginLeft: '20px'
                        }}
                    >
                        Log Out
                    </Button>
                    <Button
                        onClick={gotoCart}
                        type="button"
                        variant="contained"
                        color="primary"
                        style={{
                            backgroundColor: "darkred",
                            marginBottom: '20px',
                            marginTop: '20px',
                            marginLeft: '20px'
                        }}
                    >
                        View Cart
                    </Button>
                </div>
                {showBooksError ? <div>No books available</div> : <div></div>}
                {message && <div>{message}</div>}
                <select
                    value={selectedCurrency}
                    style={{ marginLeft: '20px', fontSize: '16px' }}
                    onChange={handleCurrencyChange}>
                    {currencyOptions.map(currency => (
                        <option key={currency} value={currency}>{currency}</option>
                    ))}
                </select>

                <select
                    value={selectedBookType}
                    style={{ marginLeft: '20px', fontSize: '16px' }}
                    onChange={handleTypeFilterChange}>
                    <option value="all" >All Types</option>
                    {bookTypes.map(bookType => (
                        <option key={bookType.id} value={bookType.name}>
                            {bookType.name.charAt(0).toUpperCase() + bookType.name.slice(1)}
                        </option>
                    ))}
                </select>

                <List>
                    {filteredBooks.map(book => (
                        <BookItem
                            key={book.id}
                            book={book}
                            email={email}
                            selectedCurrencyValue={selectedCurrencyValue}
                            addToCart={addToCart}
                        />
                    ))}
                </List>
            </div>
        </ThemeProvider>
    );
}

export default HomeClient;
