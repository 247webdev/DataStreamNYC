import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import axios from 'axios';
import './App.css';

import HomePage from './components/HomePage';
import AdminView from './components/AdminView';
import NewUserForm from './components/NewUserForm';
import UpdateUserForm from './components/UpdateUserForm';
import Dashboard from './components/Dashboard';
// import LogReg from './components/LogReg';
import SuggestionBoard from './components/SuggestionBoard';

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
      const users = [];
      usersResponse.data.map((res) => {
        let user = {};

        user['id'] = res.id;
        user['email'] = res.email;
        user['firstName'] = res.firstName;
        user['lastName'] = res.lastName;

        users.push(user);
      });
      this.setState({ users });
    } catch (error) {
      console.log(error);
    };
  };

  createUser = async (newUser) => {
    try {
      const newUserResponse = await axios.post(`${process.env.REACT_APP_USERSAPI}/users`, newUser);
      const newUserFromDatabase = {};

      newUserFromDatabase['id'] = newUserResponse.data.id;
      newUserFromDatabase['email'] = newUserResponse.data.email;
      newUserFromDatabase['firstName'] = newUserResponse.data.firstName;
      newUserFromDatabase['lastName'] = newUserResponse.data.lastName;

      const updatedUsersList = [...this.state.users];
      updatedUsersList.push(newUserFromDatabase);

      this.setState({ users: updatedUsersList });
    } catch (error) {
      console.log("Error creating new User");
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

  // loginUser = (loginInfo) => {
  //   const curUser = { ...this.state.currentUser };

  //   curUser.id = loginInfo.id;
  //   curUser.firstName = loginInfo.firstName;
  //   curUser.lastName = loginInfo.lastName;

  //   this.setState({ currentUser: curUser });
  // }

  render() {
    const AdminViewComponent = () => (<AdminView
      users={this.state.users}
      deleteUser={this.deleteUser}
    />);

    const NewUserFormComponent = () => (<NewUserForm createUser={this.createUser} />);

    // const LogRegComponent = () => (<LogReg
    //   users={this.state.users}
    //   createUser={this.createUser}
    // />);

    // const SuggestionBoardComponent = () => (<SuggestionBoard currentUser={this.state.currentUser} />);

    return (
      <Router>
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route exact path="/admin" render={AdminViewComponent} />
          <Route exact path="/new" render={NewUserFormComponent} />
          <Route path="/update/:userId/:index" component={UpdateUserForm} />
          <Route exact path="/apidashboard" component={Dashboard} />
          {/* <Route exact path="/logreg" render={LogRegComponent} /> */}
          <Route exact path="/suggestionboard" component={SuggestionBoard} />
        </Switch>
      </Router>
    );
  }
}

export default App;
