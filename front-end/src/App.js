import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import axios from 'axios';
import './App.css';

import HomePage from './components/HomePage';
import AdminView from './components/AdminView';
import UpdateUserForm from './components/UpdateUserForm';
import Dashboard from './components/Dashboard';
import LogReg from './components/LogReg';

class App extends Component {
  constructor() {
    super();

    this.state = {
      users: [],
      currentUser: {
        id: null,
        firstName: '',
        lastName: ''
      }
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
    const AdminViewComponent = () => (<AdminView
      users={this.state.users}
      deleteUser={this.deleteUser}
    />);

    const LogRegComponent = () => (<LogReg
      users={this.state.users}
    />);

    return (
      <Router>
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route exact path="/admin" render={AdminViewComponent} />
          <Route path="/update/:userId/:index" component={UpdateUserForm} />
          <Route exact path="/apidashboard" component={Dashboard} />
          <Route exact path="/logreg" render={LogRegComponent} />
        </Switch>
      </Router>
    );
  }
}

export default App;
