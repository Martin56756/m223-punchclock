import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class Login extends Component {

    constructor(props) {
        super(props);
        this.state = {
            userName: '',
            passWord: ''
        }
    }

    logIn() {
        const axios = require('axios');
        const that = this;
        axios.get('/login', {username: that.state.userName, password: that.state.passWord}).then(function (response) {
            if (response.status == 200) {
                localStorage = response.header.authorization;
                that.props.history.push('/logTime');
            }else {
                document.getElementById("errorMessage").innerHTML(response.body.message);
            }
        })
    }

    register() {
        const axios = require('axios');
        const that = this;
        axios.post('/users/sign-up', {userName: that.state.userName, passWord: that.state.passWord}).then(function (response) {
            if (response.status == 200) {
                that.render();
            }else {
                document.getElementById("errorMessage").innerHTML(response.body.message);
            }
        })
    }

    render() {
        return (
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <div className="row">
                        <label htmlFor="userName">Username</label>
                        <input type="text" id="userName" className="form-control" autoFocus={true}/>
                        <label htmlFor="passWord">Password</label>
                        <input type="password" id="passWord" className="form-control"/>
                        <button type="button" className="btn btn-primary" id="loginBtn" onClick={this.logIn()}>Login</button>
                        <button type="button" className="btn btn-secondary" onClick={this.register()} id="registerBtn">Register</button>
                    </div>
                    <div className="row">
                        <p id="errorMessage">
                        </p>
                    </div>
                </div>
                <div className="col-4"></div>
            </div>
        );
    }
}

export default Login;