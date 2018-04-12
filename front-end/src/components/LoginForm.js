import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import axios from 'axios';

class LoginForm extends Component {
  constructor() {
    super();

    this.state = {
      error: '',
      user: {},
      redirectToSuggestionPage: false
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

    const check = this.checkUsers(this.state.user);
    if (check) {
      this.props.loginUser(this.state.user);
      this.setState({ redirectToSuggestionPage: true });
    } else {
      this.setState({ error: 'Invalid login credentials' });
    }
  };

  checkUsers = async (logUser) => {
    try {
      const userResponse = await axios.get(`${process.env.REACT_APP_USERSAPI}/users/find/${logUser.email}`);
      if (userResponse == null) {
        return false;
      } else {
        const updatedUser = {};

        updatedUser['id'] = userResponse.data.id;
        updatedUser['firstName'] = userResponse.data.firstName;
        updatedUser['lastName'] = userResponse.data.lastName;

        this.setState({ user: updatedUser });
        return true;
      }
    } catch (error) {
      console.log("Error creating new User");
      return false;
    };
  }

  render() {
    if (this.state.redirectToSuggestionPage) {
      return <Redirect to="/suggestionboard" />
    }

    return (
      <div className="forms">
        <h2>Login</h2>
        <form onSubmit={this.handleSubmit} id="login-form">
        <label htmlFor="email">Email:
            <input
              id="login-email"
              type="text"
              name="email"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="password">Password:
            <input
              id="login-password"
              type="password"
              name="password"
              onChange={this.handleChange}
            />
          </label>
          { this.state.error === '' ? null : <p>{this.state.error}</p> }
          <button id="login-submit" type="submit">Login</button>
        </form>
      </div>
    );
  }
}

export default LoginForm;