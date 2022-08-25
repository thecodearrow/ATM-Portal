import React, { useState,useEffect } from 'react';
import {useNavigate} from 'react-router';
import Cookies from "universal-cookie";
import Snackbar from '@mui/material/Snackbar';

const PVBankHomePage = () => {
    const [loginUserName, setLoginUserName] = useState("");
    const [loginPassword, setLoginPassword] = useState("");
    const cookies = new Cookies();
    let navigate = useNavigate();
    const [successOpen, setSuccessOpen] = useState(false);
    const [errorOpen, setErrorOpen] = useState(false);
    const [successMessage,setSuccessMessage]=useState("Success")
    const [errorMessage,setErrorMessage]=useState("Bad request")
    useEffect(()=>{
        //clear set user cookie!
        cookies.remove('user_id');
        cookies.remove('user_token');

    },[])
    const setCookie=(userID)=>{
        
        //TODO add a JWT token
        cookies.set('user_token', 'SECRET', { path: '/' });
        cookies.set('user_id', userID, { path: '/' });

    }
    const handleSubmit =()=>{
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "loginUserName": loginUserName,"loginPassword":loginPassword })
        };
        fetch('http://localhost:8080/user-login', requestOptions)
            .then(response => response.json())
            .then(data => {
                if(data && data.status===true){
                    //login successful
                    setCookie(data.userID);
                    setSuccessMessage(data.message);
                    setSuccessOpen(true); 
                    navigate("../success",{replace:false});
                }
                else{
                    setErrorMessage(data.message);
                    setErrorOpen(true); 
                }
               
            });
    }
    const handleSuccessClose = (event?: React.SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
          return;
        }
    
        setSuccessOpen(false);
      };
      const handleErrorClose = (event?: React.SyntheticEvent | Event, reason?: string) => {
        if (reason === 'clickaway') {
          return;
        }
    
        setErrorOpen(false);
      };
    
    return  (
      <div className="Home">
        <div className="login-box">
            <h2>Welcome to PV Bank </h2>
            <form>
                <div className="user-box">
                    <input type="text" value={loginUserName} onChange={e => setLoginUserName(e.target.value)} required/>
                        <label>Username</label>
                 </div>
                <div className="user-box">
                    <input type="password" value={loginPassword} onChange={e => setLoginPassword(e.target.value)} required />
                        <label>Password</label>
                </div>
                <a href="#" onClick={handleSubmit}>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    Submit
                </a>
            </form>
        </div>
        <Snackbar
        open={successOpen}
        autoHideDuration={3000}
        onClose={handleSuccessClose}
        message={successMessage}
      />
      <Snackbar
        open={errorOpen}
        autoHideDuration={3000}
        onClose={handleErrorClose}
        message={errorMessage}
      />
    </div>
    );

}

export default PVBankHomePage;