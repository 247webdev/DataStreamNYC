import React from 'react';
import '../App.css';

import LoginForm from './LoginForm';
import RegisterForm from './RegisterForm';

const LogReg = (props) => {
  return (
    <div>
      <LoginForm
        users={props.users}
        loginUser={props.loginUser}
      />
      <RegisterForm
        users={props.users}
        createUser={props.createUser}
        loginUser={props.loginUser}
      />
    </div>
  );
}

export default LogReg;