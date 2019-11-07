import React, { Component } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.css';
import $ from 'jquery';

class LogTime extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        }
        this.getAll();
    }

    getAll() {
        const that = this;
        $.ajax({
            type: "GET",
            url: 'http://localhost:8081/entries',
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            success: function (data) {
                for (let i = 0; i < data.length; i++) {
                    that.state.list.push(data[i]);
                }
            }
        });
    }

    deleteEntry(entryId) {
        const that = this;
        $.ajax({
            type: "DELETE",
            url: 'http://localhost:8081/entries/' + entryId,
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            success: function () {
                that.getAll();
            }
        });
    }

    createEntry() {
        const that = this;
        $.ajax({
            type: "POST",
            url: 'http://localhost:8081/entries',
            headers: {
                "Authorization": localStorage.getItem('token')
            },
            data: {checkIn: "2016-01-29T08:34:55", checkOut: "2016-01-29T17:34:55"},
            success: function () {
                that.getAll();
            }
        });
    }

    renderTableRows() {
        return this.state.list.map((item, index) => {
            const {id, checkIn, checkOut, category} = item;
            return (
                <tr key={id}>
                    <td>{checkIn}</td>
                    <td>{checkOut}</td>
                    <td>{category.type}</td>
                    <td><button type="button" className="btn btn-danger" onClick={() => this.deleteEntry({id})}>Delete</button></td>
                </tr>
            );
        });
    }

    render() {
        return(
            <div className="row">
                <div className="col-4">
                    <button type="button" className="btn btn-primary" onClick={() => this.createEntry()}>Create</button>
                    <button type="button" className="btn btn-primary" onClick={() => this.deleteEntry(1)}>Delete</button>
                </div>
                <div className="col-4">
                    <table className="table table-sm table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Check In</th>
                                <th scope="col">Check Out</th>
                                <th scope="col">Category</th>
                                <th scope="col">Delete</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.renderTableRows()}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default LogTime;