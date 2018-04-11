import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

const User = (props) => {
  return (
    <div id={`user-${props.user.id}`} data-user-display className="list-group-item">
      <div className="left">
        <div id={`user-${props.user.id}-first-name`} className="first">
          {props.user.firstName}
        </div>
        <div id={`user-${props.user.id}-last-name`} className="last">
          {props.user.lastName}
        </div>
        <div id={`user-${props.user.id}-email`} className="email">
          {props.user.email}
        </div>
      </div>
      <div className="right">
        <Link
          to={`/update/${props.user.id}/${props.index}`}
          id="update-user">
          Update
        </Link>
        <br/>
        <button
          id={`delete-user-${props.user.id}`}
          onClick={() => { props.deleteUser(props.user.id, props.index) }}>
          Delete
        </button>
      </div>
      <hr/>
    </div>
  );
}

export default User;