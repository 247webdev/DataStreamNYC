import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

import QueryForm from './QueryForm';
import QueryList from './QueryList';

class Dashboard extends Component {
  constructor() {
    super();

    this.state = {
      results: []
    };
  }


  runSearch = async (agency) => {
    try {
      const searchResponse = await axios.get(`http://data.cityofnewyork.us/resource/buex-bi6w.json?agency_name=${agency}`);
      this.setState({ results: searchResponse.data });
    } catch (error) {
      console.log(error);
    }
  }

  render() {
    return (
      <div className="dashboard">
        <div className="nav">
          <Link to="/" id="home-page-link">Home</Link> |&nbsp;
          <Link to="/admin" id="admin-view-link">Admin View</Link>
        </div>
        <h1 className="white"><u>API Dashboard</u></h1>
        <QueryForm runSearch={this.runSearch} />
        <QueryList queries={this.state.results} />
      </div>
    );
  }
}

export default Dashboard;