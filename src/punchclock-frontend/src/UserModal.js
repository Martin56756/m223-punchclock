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
        $.ajax({
            type: "GET",
            url: 'http://localhost:8081/users/' + userName,
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            success: function (data) {
                that.setState({
                    userName: data.userName,
                    passWord: data.passWord
                });
            }
        });
    }

    updateUser() {
        const that = this;
        $.ajax({
            type: "PUT",
            url: 'http://localhost:8081/users',
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            data: {userName: that.state.userName, passWord: that.state.passWord, role: that.getRoleByName(that.state.role)},
            success: function (data) {
                $("#userModal").modal("hide");
            },
            error: function (data) {
                $("#errorMessage").html(data);
            }
        });
    }

    getRoleByName(name) {
        const that = this;
        $.ajax({
            type: "GET",
            url: 'http://localhost:8081/roles/' + name,
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            success: function(data) {
                return data;
            }
        });
    }

    getRoles() {
        const that = this;
        $.ajax({
            type: "GET",
            url: 'http://localhost:8081/roles',
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    that.state.roles.push(data[i]);
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
                                    this.state.roles.map((role) =>
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
