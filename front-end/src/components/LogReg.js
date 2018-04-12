import React from 'react';
import '../App.css';

import LoginForm from './LoginForm';
import RegisterForm from './RegisterForm';

const LogReg = (props) => {
  return (
    <div>
      <LoginForm />
      <RegisterForm />
    </div>
  );
}

export default LogReg;