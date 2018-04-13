import React from 'react';
import '../App.css';

const Query = (props) => {
  return (
    <div className="query list-item">
      <p className="a list-group-item list-group-item-action list-group-item-danger">Agency: {props.query.agency_name}</p>
      <p className="i list-group-item list-group-item-action list-group-item-warning">ID: {props.query.request_id}</p>
      <p className="t list-group-item list-group-item-action list-group-item-success">Title: {props.query.short_title}</p>
      <p>Descripton: {props.query.additional_description_1}</p>
    </div>
  );
}

export default Query;