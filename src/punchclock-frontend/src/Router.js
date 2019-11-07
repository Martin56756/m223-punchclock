import React, { Component } from "react";
import { BrowserRouter as Router, Route, Link, Redirect, withRouter, Switch } from "react-router-dom";
import App from './App';
import Login from './Login';
import LogTime from "./LogTime";
import UserManagement from "./UserManagement";
import 'bootstrap/dist/css/bootstrap.css'

class PunchClockRouter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loginState: 'Login'
        }
    }

    isLoggedIn() {
        if (localStorage.getItem('token') !== null) {
            return true;
        }
        return false;
    }

    render() {
        let logTimeLink;
        let userManagementLink;
        if (this.isLoggedIn()) {
            logTimeLink = <li><Link to="/logTime" className="nav-link">Log Time</Link></li>;
            userManagementLink = <li><Link to="/user-management" className="nav-link">User Management</Link></li>;
        }
        return (
            <Router>
                <div className="container col-12">
                    <div className="navbar fixed-top navbar-expand-sm">
                        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className="collapse navbar-collapse col" id="navbarToggler">
                            <ul className="navbar-nav">
                                <li>
                                    <Link to="/" className="navbar-brand nav-link">PunchClock</Link>
                                </li>
                                <li>
                                    <Link to="/login" className="nav-link">{this.state.loginState}</Link>
                                </li>
                                {logTimeLink}
                                {userManagementLink}
                            </ul>
                        </div>
                    </div>
                    <Switch>
                        <Route path="/login" component={Login} />
                        <Route exact path="/" component={App} />
                        <Route path="/logTime" component={LogTime}/>
                        <Route path="/user-management" component={UserManagement}/>
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default PunchClockRouter;