import React, { Component } from 'react';
import axios from 'axios';
import $ from 'jquery';
import UserModal from './UserModal';

class UserManagement extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: []
        }
        this.getUsers();
    }

    isLoggedIn() {
        const token = localStorage.getItem('token');
        if (token !== null) {
            axios.defaults.headers.common['authorization'] = token;
            return true;
        }
        return false;
    }

    getUsers() {
        const that = this;
        $.ajax({
            type: "GET",
            url: 'http://localhost:8081/users',
            headers: {
               "Authorization": localStorage.getItem('token')
            },
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    that.state.users.push(data[i]);
                }
            },
            error: function (data) {
                $("#errorMessage").html(data.message);
            }
        });

    }

    createUser() {
        const that = this;

    }

    showUser(userName) {
        const that = this;
        $("#modalContainer").html(
        <UserModal userName={userName}/>
        );
    }

    deleteUser(userName) {
        const that = this;
        axios.delete('http://localhost:8081/users/' + userName).then(function (response) {
            if (response.status == 200) {
                this.getUsers();
            }else {
                document.getElementById("errorMessage").innerHTML(response.body.message);
            }
        });
    }

    renderTableData() {
        return this.state.users.map((user, index) => {
            const {userName, passWord, role} = user
            return (
                <tr key={userName}>
                    <td>{userName}</td>
                    <td><button type="button" className="btn btn-primary" onClick={() => this.showUser({userName})}>Edit</button></td>
                    <td><button type="button" className="btn btn-danger" onClick={() => this.deleteUser({userName})}>Delete</button></td>
                </tr>
            )
        })
    }

    render() {
        return(
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <button type="button" className="btn btn-primary" onClick={() => this.createUser()}>Create</button>
                    <table className="table table-sm table-bordered table-dark" id="userTable">
                        <thead>
                            <tr>
                                <th scope="col">Username</th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.renderTableData()}
                        </tbody>
                    </table>
                </div>
                <div className="col-4" id="modalContainer">

                </div>
            </div>
        );
    }
}

export default UserManagement;