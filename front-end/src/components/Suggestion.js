import React from 'react';
import '../App.css';

const Suggestion = (props) => {
  return (
    <div id={`suggestion-${props.suggestion.id}`} data-suggestion-display className="list-group-item">
      <div className="left">
        <div id={`suggestion-${props.suggestion.id}-title`} className="title">
          {props.suggestion.title}
        </div>
        <div id={`suggestion-${props.suggestion.id}-content`} className="content">
          {props.suggestion.content}
        </div>
        <div id={`suggestion-${props.suggestion.id}-user-name`} className="userName">
          User: {props.suggestion.userName}
        </div>
      </div>
      <div className="right">
        {props.userName === props.suggestion.userName ?
          <button
            id={`delete-suggestion-${props.suggestion.id}`}
            onClick={() => { props.deleteSuggestion(props.suggestion.id, props.index) }}>
            Delete
        </button> : null}
      </div>
      <hr />
    </div>
  );
}

export default Suggestion;