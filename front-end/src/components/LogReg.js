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
      <RegisterForm />
    </div>
  );
}

export default LogReg;