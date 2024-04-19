import React from "react";
import {Avatar, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import BookDetails from "./BookDetails";
import Button from "@mui/material/Button";
import {Edit} from "@mui/icons-material";

class BookItem extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            displayDetails: false,
        }
    };

    displayDetails = () => {
        this.setState({
            displayDetails: !this.state.displayDetails
        })
    }

    render() {
        return (
            <React.Fragment>
                <ListItem>
                    <ListItemIcon>
                        <Avatar>{"B"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={"'" + this.props.book.title + "'    from: " + this.props.book.author}/>
                    <br/>
                    <Button onClick={() => this.displayDetails()}>
                        <Edit />DETAILS
                    </Button>
                    <div>
                        {this.state.displayDetails ? <BookDetails book={this.props.book} role={this.props.role} selectedCurrencyValue={this.props.selectedCurrencyValue}/> : null}
                    </div>
                </ListItem>
            </React.Fragment>
        )
    }
}

export default BookItem;