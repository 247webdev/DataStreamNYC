import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

import SuggestionForm from './SuggestionForm';
import SuggestionList from './SuggestionList';

class SuggestionBoard extends Component {
  constructor() {
    super();

    this.state = {
      suggestions: []
    };
  }

  async componentDidMount() {
    try {
      const suggestionsResponse = await axios.get(`${process.env.REACT_APP_USERSAPI}/suggestions`);
      const suggestionsFromDatabase = suggestionsResponse.data;
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
      const newSuggestionResponse = await axios.post(`${process.env.REACT_APP_USERSAPI}/users`, newSuggestion);
      const newSuggestionFromDatabase = newSuggestionResponse.data;

      const updatedSuggestionsList = [...this.state.suggestions];
      updatedSuggestionsList.push(newSuggestionFromDatabase);

      this.setState({ suggestions: updatedSuggestionsList });
    } catch (error) {
      console.log("Error creating new suggestion");
    }
  };

  render() {
    return (
      <div>
        <SuggestionForm />
        <SuggestionList />
      </div>
    );
  }
}

export default SuggestionBoard;