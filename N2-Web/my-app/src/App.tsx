import React from 'react';
import './global.css';
import Routes from './routes';
import { ToastProvider, useToasts } from 'react-toast-notifications';

function App() {
  return (
    <ToastProvider>
      <Routes />
    </ToastProvider>
  );
}

export default App;
