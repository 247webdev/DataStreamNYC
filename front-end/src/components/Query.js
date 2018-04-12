import React from 'react';
import '../App.css';

const Query = (props) => {
  return (
    <div className="query">
      <p className="a">Agency: {props.query.agency_name}</p>
      <p className="i">ID: {props.query.request_id}</p>
      <p className="t">Title: {props.query.short_title}</p>
      <p>Descripton: {props.query.additional_description_1}</p>
    </div>
  );
}

export default Query;