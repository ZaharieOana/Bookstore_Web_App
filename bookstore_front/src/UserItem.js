import React from "react";
import {Avatar, ListItem, ListItemIcon, ListItemText} from "@mui/material";
import UserDetails from "./UserDetails";
import Button from "@mui/material/Button";
import {Edit} from "@mui/icons-material";

class UserItem extends React.Component {
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
                        <Avatar>{this.props.user.type === "CLIENT" ? "C" : "A"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={this.props.user.email}/>
                    <br/>
                    <Button onClick={() => this.displayDetails()}>
                        <Edit />DETAILS
                    </Button>
                    <div>
                        {this.state.displayDetails ? <UserDetails user={this.props.user} /> : null}
                    </div>
                </ListItem>
            </React.Fragment>
        )
    }
}

export default UserItem;