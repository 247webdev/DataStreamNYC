import React, { Component } from 'react';

class QueryForm extends Component {
  constructor() {
    super();

    this.state = {
      query: ''
    };
  }

  handleChange = (event) => {
    const newValue = event.target.value.toUpperCase();

    let updatedQuery = { ...this.state.query };
    updatedQuery = newValue;

    this.setState({ query: updatedQuery });
  };

  handleSubmit = (event) => {
    event.preventDefault();

    this.props.runSearch(this.state.query);
  };

  render() {
    return (
      <div className="forms">
        <form onSubmit={this.handleSubmit}>
          <label htmlFor="agency">Agency Name:&nbsp;
            <input type="text" name="agency" onChange={this.handleChange} />
          </label>
          <button type="submit" className="btn btn-primary">Search</button>
        </form>
      </div>
    );
  }
}

export default QueryForm;