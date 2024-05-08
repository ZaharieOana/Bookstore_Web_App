import React from "react";

import {Avatar, List, ListItem, ListItemIcon, ListItemText} from "@mui/material";

class UserDetails extends React.Component {
    constructor(props) {
        super(props)
    };


    render() {
        return (
            <React.Fragment>
                <ListItem key={this.props.user.email}>
                    <ListItemIcon>
                        <Avatar>{"N"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={"name: " + this.props.user.name}/>
                </ListItem>
                <ListItem key={this.props.user.email}>
                    <ListItemIcon>
                        <Avatar>{"A"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={"age: " + this.props.user.age}/>
                </ListItem>
                <ListItem key={this.props.user.email}>
                    <ListItemIcon>
                        <Avatar>{"A"}</Avatar>
                    </ListItemIcon>
                    <ListItemText
                        primary={this.props.user.active ? "active" : "inactive"}/>
                </ListItem>
            </React.Fragment>
        )
    }
}

export default UserDetails;