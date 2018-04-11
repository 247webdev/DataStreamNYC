import React, { Component } from 'react';
import { Redirect, Link } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

class UpdateUserForm extends Component {
  constructor() {
    super();

    this.state = {
      user: {},
      redirectToAdminPage: false
    };
  }

  updateUser = async (userId, index, updatedUser) => {
    try {
      await axios.patch(`${process.env.REACT_APP_USERSAPI}/users/${userId}`, updatedUser);
    } catch (error) {
      console.log(`Error updating User with ID: ${userId}`);
    };
  };

  handleChange = (event) => {
    const attributeToChange = event.target.name;
    const newValue = event.target.value;

    const updatedUser = { ...this.state.user };
    updatedUser[attributeToChange] = newValue;

    this.setState({ user: updatedUser });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    const par = this.props.match.params;
    this.updateUser(par.userId, par.index, this.state.user);

    this.setState({ redirectToAdminPage: true });
  };

  render() {
    if (this.state.redirectToAdminPage) {
      return <Redirect to="/admin" />
    }

    return (
      <div className="forms">
        <h2>Update User</h2>
        <Link to="/admin">Cancel</Link>
        <form onSubmit={this.handleSubmit} id="update-user-form">
          <label htmlFor="email">Email:
            <input
              id="update-user-email"
              type="text"
              name="email"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="firstName">First Name:
            <input
              id="update-user-first-name"
              type="text"
              name="firstName"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="lastName">Last Name:
            <input
              id="update-user-last-name"
              type="text"
              name="lastName"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="password">Password:
            <input
              id="update-user-password"
              type="text"
              name="password"
              onChange={this.handleChange}
            />
          </label>
          <button id="update-user-submit" type="submit">Submit</button>
        </form>
      </div>
    );
  }
}

export default UpdateUserForm;