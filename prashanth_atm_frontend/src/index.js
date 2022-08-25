import * as React from 'react';
import ReactDOM from 'react-dom/client';
import { StyledEngineProvider } from '@mui/material/styles';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import PVBankHomePage from './PVBankHomePage';
import LoginSuccessPage from './LoginSuccessPage';
import UserRegisterPage from './UserRegisterPage';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
    <StyledEngineProvider injectFirst>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="login" element={<PVBankHomePage />} />
      <Route path="register" element={<UserRegisterPage />} />
      <Route path="success" element={<LoginSuccessPage />} />
    </Routes>
    </StyledEngineProvider>
  </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
