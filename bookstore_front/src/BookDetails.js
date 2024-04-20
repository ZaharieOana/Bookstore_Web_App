import React from "react";

import {Avatar, List, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import Button from "@mui/material/Button";

class BookDetails extends React.Component {
    constructor(props) {
        super(props)
    };

    buyBook = () => {
        //todo
    }

    render() {
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
                {this.props.role === "ADMIN" ? <div></div> : <ListItem key={this.props.book.title}>
                    <Button onClick={() => this.buyBook()}>Buy book</Button>
                </ListItem>}

            </React.Fragment>
        )
    }
}

export default BookDetails;