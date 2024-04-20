import React from "react";
import Button from "@mui/material/Button"
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import axiosInstance from "./axios";
import Grid from "@mui/material/Grid";
import history from './history';
import {useNavigate} from "react-router-dom";


class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            email: "",
            password: "",
            age: 0,
            signupSuccess: {
                id: 0,
                role: "CLIENT",
            }
        };
    }

    handleInput = event => {
        const {value, name} = event.target;
        this.setState({
            [name]: value
        });
        console.log(value);
    };

    onSubmitFunction = event => {
        event.preventDefault();
        let credentials = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
            age: this.state.age,
        }

        axiosInstance.post("/user/save", credentials)
            .then(
                res => {
                    const val = res.data;
                    this.setState({
                        signupSuccess: val
                    });
                    console.log("Success");
                    console.log(this.state.signupSuccess);
                    this.setState({
                        signupError: false
                    });

                    history.push("/log-in");
                    window.location.reload();
                }
            )
            .catch(error => {
                console.log(error)
                this.setState({
                    signupError: true
                });
            })
    }

    render() {
        return (
            <div className="mainContainer" style={{
                backgroundImage: `url(/images/dragon.jpg)`,
                position: 'absolute',
                top: 0,
                right: 0,
                bottom: 0,
                left: 0,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
                backgroundRepeat: 'no-repeat',
            }}>
                <div align="right">
                    <Grid maxWidth="sm" style={{padding: "20px"}}>
                        <form onSubmit={this.onSubmitFunction}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="name"
                                label="Name"
                                name="name"
                                autoComplete="string"
                                onChange={this.handleInput}
                                autoFocus
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="email"
                                label="Email"
                                name="email"
                                autoComplete="string"
                                onChange={this.handleInput}
                                autoFocus
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                onChange={this.handleInput}
                                autoComplete="current-password"
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="age"
                                label="Age"
                                name="age"
                                autoComplete="integer"
                                onChange={this.handleInput}
                                autoFocus
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                style={{backgroundColor: "darkred"}}
                            >
                                CreateAccount
                            </Button>
                            <p> </p>
                        </form>
                        {this.state.signupError ?  <div style={{color: "red"}}>Invalid data</div> : <div></div>}
                    </Grid>
                </div>
            </div>)
    }

}

export default SignUp;