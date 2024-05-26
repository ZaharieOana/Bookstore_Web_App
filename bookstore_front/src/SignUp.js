import React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Grid from "@mui/material/Grid";
import axiosInstance from "./axios";
import history from './history';

class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            email: "",
            phone: "",
            password: "",
            age: 0,
            errors: {},
            signupSuccess: {
                id: 0,
                role: "CLIENT",
            }
        };
    }

    handleInput = event => {
        const { value, name } = event.target;
        this.setState({
            [name]: value
        });
    };

    validate = () => {
        const errors = {};
        const { name, email, phone, password, age } = this.state;

        if (name.length < 3 || name.length > 50) {
            errors.name = "Name size must be between 3 and 50";
        }

        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!email.match(emailPattern)) {
            errors.email = "Email is not valid";
        }

        const phonePattern = /^\d{10}$/;
        if (!phone.match(phonePattern)) {
            errors.phone = "Phone must be 10 digits";
        }

        if (password.length < 8 || password.length > 50) {
            errors.password = "Password size must be between 8 and 50";
        }

        if (!Number.isInteger(Number(age))) {
            errors.age = "Age must be a number";
        } else if (age <= 16) {
            errors.age = "You need to be at least 16";
        }

        this.setState({ errors });
        return Object.keys(errors).length === 0;
    };

    onSubmitFunction = event => {
        event.preventDefault();

        if (this.validate()) {
            let credentials = {
                name: this.state.name,
                email: this.state.email,
                phone: this.state.phone,
                password: this.state.password,
                age: this.state.age,
            };

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
                    console.log(error);
                    this.setState({
                        signupError: true
                    });
                });
        }
    };

    render() {
        const { errors } = this.state;

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
                    <Grid maxWidth="sm" style={{ padding: "20px" }}>
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
                                error={!!errors.name}
                                helperText={errors.name}
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
                                error={!!errors.email}
                                helperText={errors.email}
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="phone"
                                label="Phone"
                                name="phone"
                                autoComplete="string"
                                onChange={this.handleInput}
                                error={!!errors.phone}
                                helperText={errors.phone}
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
                                error={!!errors.password}
                                helperText={errors.password}
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
                                error={!!errors.age}
                                helperText={errors.age}
                            />
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                style={{ backgroundColor: "darkred" }}
                            >
                                CreateAccount
                            </Button>
                            <p> </p>
                        </form>
                        {this.state.signupError ?  <div style={{color: "red"}}>Invalid data</div> : <div></div>}
                    </Grid>
                </div>
            </div>
        );
    }
}

export default SignUp;
