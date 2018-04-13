import React from 'react';
import '../App.css';

import Suggestion from './Suggestion';

const SuggestionList = (props) => {
    return (
      <div id="suggestions-wrapper">
        <h1><u>Current Suggestions</u></h1>
        <hr/>
        <div className="list-group">
          {
            props.suggestions.map((suggestion, index) => {
              return (
                <Suggestion
                  suggestion={suggestion}
                  key={index}
                  index={index}
                />
              )
            })
          }
        </div>
      </div>
    );
}

export default SuggestionList;