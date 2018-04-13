import React, { Component } from 'react';
import { Redirect, Link } from 'react-router-dom';
import '../App.css';

class NewUserForm extends Component {
  constructor() {
    super();

    this.state = {
      user: {},
      redirectToAdminPage: false
    };
  }

  handleChange = (event) => {
    const attributeToChange = event.target.name;
    const newValue = event.target.value;

    const updatedUser = { ...this.state.user };
    updatedUser[attributeToChange] = newValue;

    this.setState({ user: updatedUser });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    this.props.createUser(this.state.user);

    this.setState({ redirectToAdminPage: true });
  };

  render() {
    if (this.state.redirectToAdminPage) {
      return <Redirect to="/admin" />
    }

    return (
      <div className="forms">
        <h2>Create User</h2>
        <Link to="/admin">Cancel</Link>
        <form onSubmit={this.handleSubmit} id="new-user-form">
          <label htmlFor="email">Email:
            <input
              id="new-user-email"
              type="text"
              name="email"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="firstName">First Name:
            <input
              id="new-user-first-name"
              type="text"
              name="firstName"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="lastName">Last Name:
            <input
              id="new-user-last-name"
              type="text"
              name="lastName"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="password">Password:
            <input
              id="new-user-password"
              type="password"
              name="password"
              onChange={this.handleChange}
            />
          </label>
          <button id="new-user-submit" type="submit">Submit</button>
        </form>
      </div>
    );
  }
}

export default NewUserForm;