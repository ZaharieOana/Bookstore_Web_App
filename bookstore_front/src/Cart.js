import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import axiosInstance from "./axios";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import {ThemeProvider, createTheme} from "@mui/material/styles";
import {Switch} from "@mui/material";

const Cart = () => {
    const {email} = useParams();
    const [books, setBooks] = useState([]);
    const [showBooksError, setShowBooksError] = useState(false);
    const [totalPrice, setTotalPrice] = useState(0);
    const [orderError, setOrderError] = useState("");
    const [toggleDarkMode, setToggleDarkMode] = useState(true);


    useEffect(() => {
        axiosInstance.get("/user/getCart", {params: {email: email}})
            .then(res => setBooks(res.data))
            .catch(error => setShowBooksError(true));

        axiosInstance.get("/user/getTotal", {params: {email: email}})
            .then(res => setTotalPrice(res.data))
            .catch(error => {
                console.log(JSON.stringify(error, null, 3));
                setShowBooksError(true)
            });

    }, [email]);

    const removeBook = (title) => {
        axiosInstance.put("/user/removeBook", null, {params: {email: email, title: title}})
            .then(res => {
            })
            .catch(error => console.log("Error removing book from cart:", error));
    };

    const placeOrder = () => {
        axiosInstance.post("/sale/make", null, {params: {email: email}})
            .then(res => {
                setOrderError(""); // Clear any previous errors on successful order
            })
            .catch(error => {
                const errorMessage = error.response && error.response.data && error.response.data.message
                    ? error.response.data.message
                    : "Error placing order. Please try again later.";
                setOrderError(errorMessage);
                console.log("Error placing order:", error);
            });
    };

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

    return (
        <ThemeProvider theme={darkTheme}>
            <CssBaseline/>
            <div>
                <Switch checked={toggleDarkMode} onChange={toggleDarkTheme} />
                <h1>Cart for {email}</h1>
                {showBooksError ? (
                    <div>Error loading cart</div>
                ) : (
                    <div>
                        <h2>Total: {totalPrice}</h2>
                        {orderError && <div style={{color: "red"}}>{orderError}</div>}
                        {totalPrice > 0 && (
                            <Button
                                onClick={placeOrder}
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
                                Place order
                            </Button>
                        )}
                        <ul>
                            {books.map(book => (
                                <li key={book.id}>
                                    {book.title} by {book.author}
                                    <Button
                                        onClick={() => removeBook(book.title)}
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
                                        Remove
                                    </Button>
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
            </div>
        </ThemeProvider>
    );
};

export default Cart;
