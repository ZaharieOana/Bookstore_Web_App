import React from 'react'
import { useNavigate } from 'react-router-dom'
import axiosInstance from "./axios";

import {Avatar, List, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import BookItem from "./BookItem";
import UserItem from "./UserItem";

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

    render() {
        const role = "ADMIN";
        return (
            <React.Fragment>
                <h1>Hello Admin!</h1>
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
