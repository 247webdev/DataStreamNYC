import React, { Component } from 'react';
import '../App.css';

class SuggestionForm extends Component {
  constructor() {
    super();

    this.state = {
      suggestion: {},
      redirectToSuggestionsPage: false
    };
  }

  handleChange = (event) => {
    const attributeToChange = event.target.name;
    const newValue = event.target.value;

    const updatedSuggestion = { ...this.state.suggestion };
    updatedSuggestion[attributeToChange] = newValue;
    
    this.setState({ suggestion: updatedSuggestion });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    this.props.createSuggestion(this.state.suggestion);

    this.setState({ redirectToSuggestionPage: true });
  };

  render() {
    if (this.state.redirectToSuggestionPage) {
      return <Redirect to="/suggestionboard" />
    }

    return (
      <div className="forms">
        <form onSubmit={this.handleSubmit} id="new-suggestion-form">
        <label htmlFor="title">Title:
            <input
              id="new-suggestion-title"
              type="text"
              name="title"
              onChange={this.handleChange}
            />
          </label>
          <label htmlFor="content">Content:
            <input
              id="new-suggestion-content"
              type="text"
              name="content"
              onChange={this.handleChange}
            />
          </label>
          <button id="new-suggestion-submit" type="submit">Suggest</button>
        </form>
      </div>
    );
  }
}

export default SuggestionForm;