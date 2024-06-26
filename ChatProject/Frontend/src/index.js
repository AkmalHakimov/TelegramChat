import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom'; // Import BrowserRouter if you're using it
import './index.css';
import 'mdb-react-ui-kit/dist/css/mdb.min.css';
import App from './App';

const root = createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <App />
  </BrowserRouter>
);  
