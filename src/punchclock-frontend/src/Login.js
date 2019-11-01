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
        axios.get('/users/sign-up', {username: that.state.userName, password: that.state.passWord}).then(function (response) {
            if (response.status == 200) {
                that.props.history.push('/logTime');
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
                    <label for="userName">Username</label>
                    <input type="text" id="userName" className="form-control" autofocus/>
                    <label for="passWord">Password</label>
                    <input type="password" id="passWord" className="form-control"/>
                    <input type="submit" className="btn btn-primary" id="loginBtn" value="Login"/>
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