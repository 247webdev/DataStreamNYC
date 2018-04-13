import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

import User from './User';

const AdminView = (props) => {
  return (
    <div id="users-wrapper">
      <div>
        <Link to="/" id="home-page-link">Home</Link> |&nbsp;
          <Link to="/suggestionboard">Suggestion Board</Link> |&nbsp;
          <Link to="/apidashboard" id="api-page-link">API Dashboard</Link>
      </div>
      <h1><u>Current Users</u></h1>
      <Link to="/new" id="new-user-link">Create User</Link>
      <hr />
      <div className="list-group">
        {
          props.users.map((user, index) => {
            return (
              <User
                deleteUser={props.deleteUser}
                user={user}
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

export default AdminView;