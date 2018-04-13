import React from 'react';
import '../App.css';

const Suggestion = (props) => {
  return (
    <div id={`suggestion-${props.suggestion.id}`} data-suggestion-display className="query list-item">
      <div className="left wide">
        <div id={`suggestion-${props.suggestion.id}-title`} className="title bigger list-group-item list-group-item-action list-group-item-success">
          Title: {props.suggestion.title}
        </div>
        <div id={`suggestion-${props.suggestion.id}-content`} className="content bigger list-group-item list-group-item-action list-group-item-warning">
          Content: {props.suggestion.content}
        </div>
        <div id={`suggestion-${props.suggestion.id}-user-name`} className="userName bigger list-group-item list-group-item-action list-group-item-danger">
          User: {props.suggestion.userName}
        </div>
      </div>
      <div className="right">
        {props.userName === props.suggestion.userName ?
          <button
            className="btn btn-primary way-bigger"
            id={`delete-suggestion-${props.suggestion.id}`}
            onClick={() => { props.deleteSuggestion(props.suggestion.id, props.index) }}>
            Delete
        </button> : null}
      </div>
    </div>
  );
}

export default Suggestion;