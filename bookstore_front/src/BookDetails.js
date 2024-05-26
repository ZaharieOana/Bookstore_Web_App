import React from "react";
import axiosInstance from "./axios";

import {Avatar, List, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";

class BookDetails extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            stockValue: ''
        };
    };

    buyBook = () => {
        //todo
        const { email, book } = this.props;

        console.log("Email:", email);
        console.log("Book Title:", book.title);

        axiosInstance
            .put("/user/addBook", null,  { params: { email: email, title: book.title } })
            .then(res => {
                console.log(res.data);
                // Optionally, update the UI after successfully adding the book to the cart
            })
            .catch(error => {
                console.log(error);
            });
    }

    addToStock = () => {
        const { book } = this.props;
        const { stockValue } = this.state;

        const data = {
            title: book.title,
            author: book.author,
            available: book.available,
            stock: parseInt(stockValue),
            price: book.price,
            type: book.type
        };

        axiosInstance
            .put("/book/addToStock?amount=" + stockValue, data)
            .then(res => {
                console.log(res.data);
                // Optionally, you can update the UI after successfully adding to stock
            })
            .catch(error => {
                console.log(error);
            });
    }

    handleInput = (event) => {
        this.setState({ stockValue: event.target.value });
    };

    render() {
        const { book, role, selectedCurrencyValue } = this.props;
        const { stockValue } = this.state;

        return (
            <React.Fragment>
                <ListItem key={this.props.book.title}>
                    <ListItemIcon>
                        <Avatar>{"T"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={"type: " + this.props.book.type}/>
                </ListItem>
                <ListItem key={this.props.book.title}>
                    <ListItemIcon>
                        <Avatar>{"S"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={"in stock: " + this.props.book.stock}/>
                </ListItem>
                <ListItem key={this.props.book.title}>
                    <ListItemIcon>
                        <Avatar>{"P"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={"price: " + (this.props.book.price * this.props.selectedCurrencyValue)}/>
                </ListItem>
                {this.props.role === "ADMIN" ? <ListItem key={this.props.book.title}>
                    <Button onClick={() => this.addToStock()}>Add to stock</Button>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="stock"
                        label="Stock"
                        name="stock"
                        autoComplete="integer"
                        onChange={this.handleInput}
                        autoFocus
                        value={stockValue}
                    />
                </ListItem> : <ListItem key={this.props.book.title}>
                    <Button onClick={() => this.buyBook()}>Buy book</Button>
                </ListItem>}

            </React.Fragment>
        )
    }
}

export default BookDetails;