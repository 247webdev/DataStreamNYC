import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

const HomePage = (props) => {
  return (
    <div className="home">
      <div className="NYClogo">
        <div className="logoleft">
          <h1>NYC</h1>
          <div className="logoright">
            <p>Citywide</p>
            <p>Administrative</p>
            <p>Services</p>
          </div>
        </div>
        <h2>The City Record Online</h2>
      </div>
      <h3>Welcome!</h3>
      <Link to="/apidashboard">API Dashboard</Link> |&nbsp;
      <Link to="/suggestionboard">Suggestion Board</Link> |&nbsp;
      <Link to="/admin" id="admin-view-link">Admin View</Link>
      <div className="skyline"></div>
    </div>
  );
}

export default HomePage;