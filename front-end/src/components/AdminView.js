import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

import User from './User';

class AdminView extends Component {
  constructor() {
    super();

    this.state = {
      users: []
    };
  }

  async componentDidMount() {
    try {
      const usersResponse = await axios.get(`${process.env.REACT_APP_USERSAPI}/users`);
      this.setState({
        users: usersResponse.data,
        usersResponse
      });
    } catch (error) {
      console.log(error);
    };
  };

  deleteUser = async (userId, index) => {
    try {
      await axios.delete(`${process.env.REACT_APP_USERSAPI}/users/${userId}`);

      const updatedUsersList = [...this.state.users];
      updatedUsersList.splice(index, 1);

      this.setState({ users: updatedUsersList });

    } catch (error) {
      console.log(`Error deleting User with ID: ${userId}`);
    };
  };

  render() {
    return (
      <div id="users-wrapper">
        <div>
          <Link to="/" id="home-page-link">Home</Link> |&nbsp;
          <Link to="/suggestionboard">Suggestion Board</Link> |&nbsp;
          <Link to="/apidashboard" id="api-page-link">API Dashboard</Link>
        </div>
        <h1><u>Current Users</u></h1>
        <hr/>
        <div className="list-group">
          {
            this.state.users.map((user, index) => {
              return (
                <User
                  deleteUser={this.deleteUser}
                  user={user}
                  key={index}
                  index={index}
                />
              )
            })
          }
        </div>
      </div>
    );
  }
}

export default AdminView;