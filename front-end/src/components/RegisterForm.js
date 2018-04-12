import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';

class RegisterForm extends Component {
  constructor() {
    super();

    this.state = {
      error: '',
      firstError: '',
      lastError: '',
      passError: '',
      confError: '',
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

    const val = this.validate(this.state.user);

    if (val === false) {
      return;
    }

    const check = this.checkUsers(this.state.user);

    if (check) {
      const userToReg = {};
      userToReg['email'] = this.state.user.email;
      userToReg['firstName'] = this.state.user.firstName;
      userToReg['lastName'] = this.state.user.lastName;
      userToReg['password'] = this.state.user.password;

      this.props.createUser(userToReg);

      this.props.loginUser(userToReg);

      this.setState({ redirectToSuggestionPage: true });
    } else {
      this.setState({ error: 'User already exists ' });
    }
  };

  validate = (logUser) => {
    if (logUser.firstName.length < 2) {
      this.setState({ firstError: 'First name must be at least 2 characters' });
    }
    if (logUser.lastName.length < 2) {
      this.setState({ lastError: 'Last name must be at least 2 characters' });
    }
    if (logUser.password.length < 2) {
      this.setState({ passError: 'Password must be at least 2 characters' });
    }
    if (logUser.passwordConfirm.length < 2) {
      this.setState({ confError: 'Password and Confirmation do not match' });
    }
    if (this.state.firstError !== '' ||
      this.state.lastError !== '' ||
      this.state.passError !== '' ||
      this.state.confError !== '') {
        return false;
    }
    return true;
  };

  checkUsers = (logUser) => {
    const curUser = this.props.users.find((user) => {
      if (user.email === logUser.email) {
        return user;
      } else {
        return null;
      }
    });

    if (curUser == null) {
      return true;
    }
    return false;
  };

  render() {
    if (this.state.redirectToSuggestionPage) {
      return <Redirect to="/suggestionboard" />
    }

    return (
      <div className="forms">
        <h2>Register</h2>
        <form onSubmit={this.handleSubmit} id="register-form">
          <label htmlFor="email">Email:
            <input
              id="register-email"
              type="email"
              name="email"
              onChange={this.handleChange}
            />
          </label>
          {this.state.firstError === '' ? null : <p>{this.state.firstError}</p>}
          <label htmlFor="firstName">First Name:
            <input
              id="register-first-name"
              type="text"
              name="firstName"
              onChange={this.handleChange}
            />
          </label>
          {this.state.lastError === '' ? null : <p>{this.state.lastError}</p>}
          <label htmlFor="lastName">Last Name:
            <input
              id="register-last-name"
              type="text"
              name="lastName"
              onChange={this.handleChange}
            />
          </label>
          {this.state.passError === '' ? null : <p>{this.state.passError}</p>}
          <label htmlFor="password">Password:
            <input
              id="register-password"
              type="password"
              name="password"
              onChange={this.handleChange}
            />
          </label>
          {this.state.confError === '' ? null : <p>{this.state.confError}</p>}
          <label htmlFor="passwordConfirm">Confirm Password:
            <input
              id="register-password-confirm"
              type="password"
              name="passwordConfirm"
              onChange={this.handleChange}
            />
          </label>
          {this.state.error === '' ? null : <p>{this.state.error}</p>}
          <button id="register-submit" type="submit">Register</button>
        </form>
      </div>
    );
  }
}

export default RegisterForm;