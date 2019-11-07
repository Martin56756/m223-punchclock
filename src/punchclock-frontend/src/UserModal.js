import React, { Component } from 'react';
import axios from 'axios';
import $ from 'jquery';

class UserModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userName: props.userName,
            passWord: '',
            role: '',
            roles: []
        }
        this.checkForLoginState();
        this.getUser(this.state.userName);
    }

    checkForLoginState() {
        if (localStorage.getItem('token') == null) {
            this.props.history.push("/login");
        }
    }

    getUser(userName) {
        const that = this;
        axios.get('http://localhost:8081/users/' + userName).then(function (response) {
           if (response.status == 200) {
               that.setState({
                   userName: response.body.userName,
                   passWord: response.body.passWord
               });
           }
        });
    }

    updateUser() {
        const that = this;
        axios.put('http://localhost:8081/users', {userName: that.state.userName, passWord: that.state.passWord, role: that.getRoleByName(that.state.role)}).then(function (response) {
            if (response.status == 200) {
                $("#userModal").modal("hide");
            }else {
                $("#errorMessage").innerHTML(response.body.message);
            }
        });
    }

    getRoleByName(name) {
        const that = this;
        axios.get('http://localhost:8081/roles/' + name).then(function (response) {
           if (response.status == 200) {
               return response.data;
           }
        });
    }

    getRoles() {
        const that = this;
        axios.get('http://localhost:8081/roles').then(function (response) {
           if (response.status == 200) {
               for (let i = 0; i < response.data.count; i++) {
                   that.state.roles.push(response.data[i]);
               }
           }
        });
    }

    render() {
        return(
            <div className="modal fade show" tabIndex={-1} role="dialog" id="userModal">
                <div className="modal-dialog" role={document}>
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Manage User</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden={"true"}>&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <label htmlFor="userName">Username</label>
                            <input type="text" id="userName" className="form-control" autoFocus={true} onChange={(event) => this.setState({userName:event.target.value})}/>
                            <label htmlFor="passWord">Password</label>
                            <input type="password" id="passWord" className="form-control" onChange={(event) => this.setState({passWord:event.target.value})}/>
                            <select id="roleSelect" onChange={(event) => this.setState({role: event.target.value})}>
                                {
                                    this.state.roles.map((role, i) =>
                                        <option name="role" selected={this.state.role == role.name}>role.name</option>
                                    )
                                }
                            </select>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-primary" data-dismiss="modal" onClick={() => this.updateUser()} id="saveBtn">Save</button>
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        </div>
                    <div className="row">
                        <p id="errorMessage">
                        </p>
                    </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default UserModal;
