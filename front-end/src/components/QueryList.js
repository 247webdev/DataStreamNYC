import React from 'react';
import '../App.css';

import Query from './Query';

const QueryList = (props) => {
  return (
    <div id="query-wrapper list-group">
      <h3 className="white">Results</h3>
      {
        props.queries.map((query, index) => {
          return (
              <Query
                query={query}
                key={index}
              />
          )
        })
      }
    </div>
  );
}

export default QueryList;