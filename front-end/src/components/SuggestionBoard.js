import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import SuggestionForm from './SuggestionForm';
import SuggestionList from './SuggestionList';

class SuggestionBoard extends Component {
  constructor() {
    super();

    this.state = {
      update: '',
      userName: 'anonymous',
      suggestions: []
    };
  }

  async componentDidMount() {
    try {
      const suggestionsResponse = await axios.get(`${process.env.REACT_APP_USERSAPI}/suggestions`);
      this.setState({
        suggestions: suggestionsResponse.data,
        suggestionsResponse
      });
    } catch (error) {
      console.log(error);
    };
  };

  createSuggestion = async (newSuggestion) => {
    try {
      newSuggestion['userId'] = 1;
      const newSuggestionResponse = await axios.post(`${process.env.REACT_APP_USERSAPI}/suggestions`, newSuggestion);
      const newSuggestionFromDatabase = newSuggestionResponse.data;

      const updatedSuggestionsList = [...this.state.suggestions];
      updatedSuggestionsList.push(newSuggestionFromDatabase);

      this.setState({ suggestions: updatedSuggestionsList });
    } catch (error) {
      console.log("Error creating new suggestion");
    }
  };

  deleteSuggestion = async (suggestionId, index) => {
    try {
      await axios.delete(`${process.env.REACT_APP_USERSAPI}/suggestions/${suggestionId}`);

      const updatedSuggestionsList = [...this.state.suggestions];
      updatedSuggestionsList.splice(index, 1);

      this.setState({ suggestions: updatedSuggestionsList });

    } catch (error) {
      console.log(`Error deleting Suggestion with ID: ${suggestionId}`);
    };
  };

  handleChange = (event) => {
    this.setState({ update: event.target.value });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    this.setState({ userName: this.state.update });
  };

  render() {
    return (
      <div>
        <Link to="/">Home</Link> |&nbsp;
        <Link to="/apidashboard">API Dashboard</Link> |&nbsp;
        <Link to="/admin" id="admin-view-link">Admin View</Link>

        <form onSubmit={this.handleSubmit} id="user-name-form">
          <label htmlFor="userName">
            <input
              id="new-suggestion-user-name"
              type="text"
              name="userName"
              onChange={this.handleChange}
            />
            <button id="user-name-submit" type="submit">Submit</button>
          </label>
        </form>

        <SuggestionForm
          createSuggestion={this.createSuggestion}
          userName={this.state.userName}
        />
        <SuggestionList
          deleteSuggestion={this.deleteSuggestion}
          suggestions={this.state.suggestions}
          userName={this.state.userName}
        />
      </div>
    );
  }
}

export default SuggestionBoard;