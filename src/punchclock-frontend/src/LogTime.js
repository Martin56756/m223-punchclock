import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';

class LogTime extends Component {
    constructor(props) {
        super(props);
        this.state = {
            list: []
        }
    }

    getAll() {
        const axios = require("axios");
        const that = this;
        axios.get('/entries').then(function (response) {
            if (response.status == 200) {
                for (var i = 0; i < response.data.count; i++) {
                    that.state.list.appendItem(response.data[i]);
                }
            }else {

            }
        });
    }

    deleteEntry(entryId) {
        const axios = require("axios");
        const that = this;
        axios.delete('/entries/' + entryId).then(function (response) {
           if (response.status == 200) {
               that.getAll();
           }
        });
    }

    createEntry() {
        const axios = require("axios");
        const that = this;
        axios.post('/entries', {checkIn: "2016-01-29T08:34:55Z", checkOut: "2016-01-29T17:34:55Z"}).then(function (response) {
            if (response.status == 200) {
                that.getAll();
            }
        })
    }

    render() {
        return(
            <div className="row">
                <button type="button" className="btn btn-primary" onClick={() => this.createEntry()}>Create</button>
                <button type="button" className="btn btn-primary" onClick={() => this.deleteEntry(1)}>Delete</button>
                <button type="button" className="btn btn-primary" onClick={() => this.getAll()}>Get All</button>
            </div>
        );
    }
}

export default LogTime;