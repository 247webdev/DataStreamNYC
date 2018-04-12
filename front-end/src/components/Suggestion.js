import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

const Suggestion = (props) => {
  return (
    <div id={`suggestion-${props.suggestion.id}`} data-suggestion-display className="list-group-item">
      <div className="left">
        <div id={`suggestion-${props.suggestion.id}-title`} className="title">
          {props.suggestion.firstName}
        </div>
        <div id={`suggestion-${props.suggestion.id}-content`} className="content">
          {props.suggestion.lastName}
        </div>
      </div>
      <hr/>
    </div>
  );
}

export default Suggestion;