import React, { useState } from 'react';
import Snackbar from '@mui/material/Snackbar';
const UserRegisterPage = () => {
    const [userFirstName, setUserFirstName]=useState("");
    const [userLastName, setUserLasttName]=useState("");
    const [loginUserName, setLoginUserName] = useState("");
    const [loginPassword, setLoginPassword] = useState("");
    const [mobileNumber, setMobileNumber] = useState("");
    const [emailID, setEmailID] = useState("");
    const [successOpen, setSuccessOpen] = useState(false);
    const [errorOpen, setErrorOpen] = useState(false);
    const [successMessage,setSuccessMessage]=useState("Success")
    const [errorMessage,setErrorMessage]=useState("Bad request")
    const handleRegister =()=>{
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({"loginUserName":loginUserName, "loginPassword": loginPassword,"userFirstName":userFirstName,"userLastName":userLastName,"mobileNumber":mobileNumber,"emailID":emailID})
        };
        fetch('http://localhost:8080/user-register', requestOptions)
            .then(response => response.json())
            .then(data => {
                if(data && data.status===true){
                    //registration  successful
                    setSuccessMessage(data.message);
                    setSuccessOpen(true); 
                
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

    return (

        <div className="Home">
        <div className="login-box">
            <h2>New User Registration </h2>
            <form>
                <div className="user-box">
                    <input type="text" value={userFirstName} onChange={e => setUserFirstName(e.target.value)} required/>
                        <label>First Name</label>
                 </div>
                 <div className="user-box">
                    <input type="text" value={userLastName} onChange={e => setUserLasttName(e.target.value)} required/>
                        <label>Last Name</label>
                 </div>
                <div className="user-box">
                    <input type="text" value={loginUserName} onChange={e => setLoginUserName(e.target.value)} required/>
                        <label>Login Username</label>
                 </div>
                <div className="user-box">
                    <input type="password" value={loginPassword} onChange={e => setLoginPassword(e.target.value)} required />
                        <label>Login Password</label>
                </div>
                <div className="user-box">
                    <input type="tel" value={mobileNumber} onChange={e => setMobileNumber(e.target.value)} required/>
                        <label>Mobile Number</label>
                 </div>
                 <div className="user-box">
                    <input type="email" value={emailID} onChange={e => setEmailID(e.target.value)} required/>
                        <label>Email ID</label>
                 </div>
                
                <a href="#" onClick={handleRegister}>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    Register
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
    </div>);
};

export default UserRegisterPage;