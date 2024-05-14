import React from 'react'
import { useNavigate } from 'react-router-dom'
import axiosInstance from "./axios";

import {Avatar, List, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import BookItem from "./BookItem";
import UserItem from "./UserItem";
import Button from "@mui/material/Button";
import history from "./history";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import { saveAs } from 'file-saver';

class HomeAdmin extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            books: [],
            users: [],
            currencies: {},
            currencyOptions: [],
            selectedCurrency: "",
            selectedCurrencyValue: 1,
            title: "",
            author: "",
            stock: 0,
            price: 0.0,
        }
    };


    componentDidMount() {
        axiosInstance
            .get(
                "/book/getAllAvailable",
            )
            .then(res => {
                const val = res.data;
                this.setState({
                    books: val
                });
                console.log(val);
                console.log(this.state.books);
            })
            .catch(error => {
                console.log(error);
                this.setState({
                    showBooksError: true
                });
            });

        axiosInstance
            .get(
                "/user/getAll",
            )
            .then(res => {
                const val = res.data;
                this.setState({
                    users: val
                });
                console.log(val);
                console.log(this.state.users);
            })
            .catch(error => {
                console.log(error);
                this.setState({
                    showUsersError: true
                });
            });

        axiosInstance
            .get(
                "/book/currency",
            )
            .then(res => {
                const val = res.data;
                this.setState({
                    currencies: val,
                    currencyOptions: Object.keys(val)
                });
                console.log(val);
                console.log(this.state.currencies);
            })
            .catch(error => {
                console.log(error);
            });
    };

    handleCurrencyChange = (event) => {
        this.setState({ selectedCurrency: event.target.value, selectedCurrencyValue: this.state.currencies[event.target.value] });
    }

    exportData(fileType) {
        axiosInstance
            .get(
                "/sale/download",
            )
            .then(res => {
                let typeForBlob = fileType === 'txt' ? 'text/plain;charset=utf-8' : 'text/xml;charset=utf-8';
                let blob = new Blob([res.data], { type: typeForBlob });
                saveAs(blob, "owner-data." + fileType);
                console.log(blob);

            })
            .catch(error => {
                console.log(error);
            });
    }

    onSubmitFunction = event => {
        event.preventDefault();
        let newBook = {
            title: this.state.title,
            author: this.state.author,
            stock: this.state.stock,
            price: this.state.price,
            available: true,
        }
        //todo
    }

    render() {
        const role = "ADMIN";
        return (
            <React.Fragment>
                <h1>Hello Admin!</h1>
                <div>
                    <Button
                        onClick={() => this.exportData("xml")}
                        type="button"
                        variant="contained"
                        color="primary"
                        style={{backgroundColor: "darkred"}}
                    >
                        Download Sales Report
                    </Button>
                </div>
                <div>
                    <Grid maxWidth="sm" style={{padding: "20px"}}>
                        <form onSubmit={this.onSubmitFunction}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="title"
                                label="Title"
                                name="title"
                                autoComplete="string"
                                onChange={this.handleInput}
                                autoFocus
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="author"
                                label="Author"
                                name="author"
                                autoComplete="string"
                                onChange={this.handleInput}
                                autoFocus
                            />
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
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="price"
                                label="Price"
                                id="price"
                                autoComplete="float"
                                onChange={this.handleInput}
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                style={{backgroundColor: "darkred"}}
                            >
                                Add New Book
                            </Button>
                            <p> </p>
                        </form>
                        {this.state.signupError ?  <div style={{color: "red"}}>Invalid data</div> : <div></div>}
                    </Grid>
                </div>
                <div>
                    <h2>All books:</h2>
                    {this.state.showBooksError ? <div>No books available</div> : <div></div>}
                    <select value={this.state.selectedCurrency} onChange={this.handleCurrencyChange}>
                        {this.state.currencyOptions.map(currency => (
                            <option key={currency} value={currency}>{currency}</option>
                        ))}
                    </select>
                    <List>
                        {this.state.books.map(book => (
                            <BookItem book={book} role={role} selectedCurrencyValue={this.state.selectedCurrencyValue}/>
                        ))}
                    </List>
                </div>
                <div>
                    <h2>All users:</h2>
                    {this.state.showUsersError ? <div>No books available</div> : <div></div>}
                    <List>
                        {this.state.users.map(user => (
                            <UserItem user={user}/>
                        ))}
                    </List>
                </div>
            </React.Fragment>
        )
    }
}

export default HomeAdmin;
