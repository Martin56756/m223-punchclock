import React, { Component } from "react";
import { BrowserRouter as Router, Route, Link, Redirect, withRouter, Switch } from "react-router-dom";
import App from './App';
import Login from './Login';
import LogTime from "./LogTime";
import 'bootstrap/dist/css/bootstrap.css'

class PunchClockRouter extends Component {
    constructor(props) {
        super(props);
        this.state = {
            loginState: 'Login'
        }
    }

    render() {
        return (
            <Router>
                <div className="container col-12">
                    <ul className="nav flex-column">
                        <li>
                            <Link to="/" className="navbar-brand nav-link">PunchClock</Link>
                        </li>
                        <li>
                            <Link to="/login" className="nav-link">{this.state.loginState}</Link>
                        </li>
                        <li>
                            <Link to="/logTime" className="nav-link">Log Time</Link>
                        </li>
                    </ul>
                    <Switch>
                        <Route path="/login" component={Login} />
                        <Route exact path="/" component={App} />
                        <Route path="/logTime" component={LogTime}/>
                    </Switch>
                </div>
            </Router>
        );
    }
}

export default PunchClockRouter;