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
               this.getAll();
           }
        });
    }

    createEntry() {

    }

    render() {
        return(
            <div className="row">

            </div>
        );
    }
}

export default LogTime;