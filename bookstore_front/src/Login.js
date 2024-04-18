import React from "react";
import Button from "@mui/material/Button"
import TextField from "@mui/material/TextField";
import Container from "@mui/material/Container";
import axiosInstance from "./axios";
import Grid from "@mui/material/Grid";
import history from './history';
import {useNavigate} from "react-router-dom";


class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            password: "",
            loginSuccess: {
                id: 0,
                role: "",
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
            email: this.state.email,
            password: this.state.password
        }

        axiosInstance.post("/login", credentials)
            .then(
                res => {
                    const val = res.data;
                    this.setState({
                        loginSuccess: val
                    });
                    console.log("Success");
                    console.log(this.state.loginSuccess);
                    this.setState({
                        loginError: false
                    });

                    //if (this.state.loginSuccess.role === "CLIENT") {
                        localStorage.setItem("role", this.state.loginSuccess.role)
                        history.push("/home");
                        window.location.reload();
                    //}
                }
            )
            .catch(error => {
                console.log(error)
                this.setState({
                    loginError: true
                });
            })
    }

    onCreateFunction = () => {
        history.push("/sign-up");
        window.location.reload();
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
                <div className={'titleContainer'}>
                    <div><h1 style={{fontSize: "3rem", padding: "10px"}} align="right">Welcome to Book Dragons'
                        Corner</h1></div>
                </div>
                <div align="right">
                    <Grid maxWidth="sm" style={{padding: "20px"}}>
                        <form onSubmit={this.onSubmitFunction}>
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
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                style={{backgroundColor: "darkred"}}
                            >
                                Sign In
                            </Button>
                           <p> </p>
                        </form>
                        <Button
                            onClick={this.onCreateFunction}
                            type="button"
                            fullWidth
                            variant="contained"
                            color="primary"
                            style={{backgroundColor: "darkred"}}
                        >
                            Create Account
                        </Button>
                        {this.state.loginError ?  <div style={{color: "red"}}>Invalid Credentials</div> : <div></div>}
                    </Grid>
                </div>
            </div>)
    }

}

export default Login;