import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css';

const HomePage = (props) => {
  return (
    <div className="home">
      <div className="homemid">
        <div className="hometop">
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
          <nav className="navbar navbar-expand-lg navbar-light bg-light homenav">
            <Link className="nav-link" to="/apidashboard">API Dashboard</Link> |&nbsp;
            <Link className="nav-link" to="/suggestionboard" id="suggestions-link">Suggestion Board</Link> |&nbsp;
            <Link className="nav-link" to="/admin" id="admin-view-link">Admin View</Link>
          </nav>
        </div>
        <h2>Welcome!</h2>
      </div>
      <div className="skyline"></div>
    </div>
  );
}

export default HomePage;