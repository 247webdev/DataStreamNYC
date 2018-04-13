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
      </div>
      <hr/>
    </div>
  );
}

export default Suggestion;