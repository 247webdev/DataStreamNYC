import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';

import HomePage from './components/HomePage';
import AdminView from './components/AdminView';
import UpdateUserForm from './components/UpdateUserForm';

class App extends Component {
  constructor() {
    super();

    this.state = {
      currentUser: {
        id: null,
        firstName: '',
        lastName: ''
      }
    };
  }

  render() {
    return (
      <Router>
        <Switch>
          <Route exact path="/" component={HomePage} />
          <Route exact path="/admin" component={AdminView} />
          <Route path="/update/:userId/:index" component={UpdateUserForm} />
        </Switch>
      </Router>
    );
  }
}

export default App;
