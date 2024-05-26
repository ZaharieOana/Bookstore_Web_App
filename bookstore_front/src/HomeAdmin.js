import React from 'react';
import { Avatar, InputLabel, List, ListItem, ListItemIcon, ListItemText, MenuItem, Select, Switch } from "@mui/material";
import BookItem from "./BookItem";
import UserItem from "./UserItem";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import { saveAs } from 'file-saver';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import axiosInstance from "./axios";

class HomeAdmin extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            books: [],
            types: [],
            selectedType: "",
            users: [],
            currencies: {},
            currencyOptions: [],
            selectedCurrency: "",
            selectedCurrencyValue: 1,
            title: "",
            author: "",
            stock: 0,
            price: 0.0,
            newType: "",
            showBookList: false,
            showUserList: false,
            filter: 'all',
            darkMode: true
        };
        this.wsconnected = false;
    };

    componentDidMount() {
        if (!this.wsconnected) {
            this.connect();
            this.wsconnected = true;
        }

        axiosInstance.get("/book/getAllAvailable")
            .then(res => this.setState({ books: res.data }))
            .catch(error => this.setState({ showBooksError: true }));

        axiosInstance.get("/type/getAll")
            .then(res => this.setState({ types: res.data }))
            .catch(error => this.setState({ showTypesError: true }));

        axiosInstance.get("/user/getAll")
            .then(res => this.setState({ users: res.data }))
            .catch(error => this.setState({ showUsersError: true }));

        axiosInstance.get("/book/currency")
            .then(res => this.setState({
                currencies: res.data,
                currencyOptions: Object.keys(res.data)
            }))
            .catch(error => console.log(error));
    }

    handleCurrencyChange = (event) => {
        this.setState({
            selectedCurrency: event.target.value,
            selectedCurrencyValue: this.state.currencies[event.target.value]
        });
    }

    exportData(fileType) {
        axiosInstance.get("/sale/download")
            .then(res => {
                let typeForBlob = fileType === 'txt' ? 'text/plain;charset=utf-8' : 'text/xml;charset=utf-8';
                let blob = new Blob([res.data], { type: typeForBlob });
                saveAs(blob, "owner-data." + fileType);
            })
            .catch(error => console.log(error));
    }

    handleTypeDropdownChange = (event) => {
        this.setState({ selectedType: event.target.value });
    }

    handleInput = (event) => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    connect() {
        const URL = "http://localhost:8080/socket";
        const websocket = new SockJS(URL);
        const stompClient = Stomp.over(websocket);
        stompClient.connect({}, frame => {
            stompClient.subscribe("/topic/socket/book/", notification => {
                let message = notification.body;
                alert(message);
            });
        });
    }

    onSubmitFunction = (event) => {
        event.preventDefault();
        let newBook = {
            title: this.state.title,
            author: this.state.author,
            stock: this.state.stock,
            price: this.state.price,
            available: true,
            type: this.state.selectedType,
        };
        axiosInstance.post("/book/save", newBook)
            .then(res => console.log(res.data))
            .catch(error => console.log(error));
    }

    toggleBookListVisibility = () => {
        this.setState(prevState => ({ showBookList: !prevState.showBookList }));
    }

    toggleUserListVisibility = () => {
        this.setState(prevState => ({ showUserList: !prevState.showUserList }));
    }

    handleFilterChange = (event) => {
        this.setState({ filter: event.target.value });
    }

    getFilteredUsers = () => {
        const { users, filter } = this.state;
        switch (filter) {
            case 'connected':
                return users.filter(user => user.connected);
            case 'notConnected':
                return users.filter(user => !user.connected);
            case 'active':
                return users.filter(user => user.active);
            case 'inactive':
                return users.filter(user => !user.active);
            default:
                return users;
        }
    }

    toggleDarkMode = () => {
        this.setState(prevState => ({ darkMode: !prevState.darkMode }));
    }

    saveNewType = () => {
        const newType = { name: this.state.newType };
        axiosInstance.post("/type/save", newType)
            .then(res => {
                this.setState(prevState => ({
                    types: [...prevState.types, res.data],
                    newType: ""
                }));
            })
            .catch(error => console.log(error));
    }

    handleTypeInputChange = (event) => {
        this.setState({ newType: event.target.value });
    }

    render() {
        const { darkMode } = this.state;
        const theme = createTheme({
            palette: {
                mode: darkMode ? 'dark' : 'light',
                background: {
                    default: darkMode ? '#121212' : '#ffffff',
                    paper: darkMode ? '#121212' : '#ffffff',
                },
            },
        });

        const filteredUsers = this.getFilteredUsers();
        const role = "ADMIN";

        return (
            <ThemeProvider theme={theme}>
                <CssBaseline />
                <div style={{padding: '20px'}}>
                    <Switch checked={darkMode} onChange={this.toggleDarkMode}/>
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
                            <form onSubmit={this.saveNewType}>
                                <TextField
                                    variant="outlined"
                                    margin="normal"
                                    required
                                    fullWidth
                                    id="newType"
                                    label="New Book Type"
                                    name="newType"
                                    autoComplete="string"
                                    onChange={this.handleTypeInputChange}
                                    autoFocus
                                />
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    style={{backgroundColor: "darkred"}}
                                >
                                    Add New Type
                                </Button>
                            </form>
                        </Grid>
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
                                <Select
                                    labelId="types-dropdown-label"
                                    value={this.state.selectedType}
                                    onChange={this.handleTypeDropdownChange}
                                    displayEmpty
                                    fullWidth
                                    style={{marginBottom: '20px', marginTop: '20px'}}
                                >
                                    <MenuItem value="" disabled>Select a book type</MenuItem>
                                    {this.state.types.map((type, index) => (
                                        <MenuItem key={index} value={type.name}>
                                            {type.name}
                                        </MenuItem>
                                    ))}
                                </Select>
                                <Button
                                    type="submit"
                                    fullWidth
                                    variant="contained"
                                    color="primary"
                                    style={{backgroundColor: "darkred"}}
                                >
                                    Add New Book
                                </Button>
                            </form>
                        </Grid>
                    </div>
                    <div>
                        <h2>All books:</h2>
                        {this.state.showBooksError && <div>No books available</div>}

                        <div onClick={this.toggleBookListVisibility} style={{cursor: 'pointer'}}>
                            {this.state.showBookList ? '▼ Hide Books' : '▶ Show Books'}
                        </div>

                        {this.state.showBookList && (
                            <div>
                                <select value={this.state.selectedCurrency} onChange={this.handleCurrencyChange}>
                                    {this.state.currencyOptions.map(currency => (
                                        <option key={currency} value={currency}>{currency}</option>
                                    ))}
                                </select>

                                <List>
                                    {this.state.books.map(book => (
                                        <BookItem book={book} role={role}
                                                  selectedCurrencyValue={this.state.selectedCurrencyValue}/>
                                    ))}
                                </List>
                            </div>
                        )}
                    </div>
                    <div>
                        <h2>All users:</h2>
                        {this.state.showUsersError && <div>No users available</div>}

                        <div onClick={this.toggleUserListVisibility} style={{cursor: 'pointer'}}>
                            {this.state.showUserList ? '▼ Hide Users' : '▶ Show Users'}
                        </div>

                        {this.state.showUserList && (
                            <div>
                                <select value={this.state.filter} onChange={this.handleFilterChange}>
                                    <option value="all">All</option>
                                    <option value="connected">Connected</option>
                                    <option value="notConnected">Not Connected</option>
                                    <option value="active">Active</option>
                                    <option value="inactive">Inactive</option>
                                </select>

                                <List>
                                    {filteredUsers.map(user => (
                                        <UserItem key={user.id} user={user}/>
                                    ))}
                                </List>
                            </div>
                        )}
                    </div>
                </div>
            </ThemeProvider>
        );
    }
}

export default HomeAdmin;
