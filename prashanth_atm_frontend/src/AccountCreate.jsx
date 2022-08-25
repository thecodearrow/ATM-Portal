import React, { useEffect } from 'react';
import Button from '@mui/material/Button';
import Cookies from 'universal-cookie';
import swal from 'sweetalert';



export const AccountCreate = () => {
    const cookies=new Cookies();
    const createNewAccount=()=>{
        //create new account
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ "userID": cookies.get('user_id') })
        };
        fetch('http://localhost:8080/create-account', requestOptions)
            .then(response => response.json())
            .then(data => {
                if(data && data.status===true){
                    //account creation success
                    let accountInfo="Account Number: "+data.accountNumber+"\nIFSC Code: "+data.ifscCode+"\nDebit Card Number: "+data.debitCardNumber+"\nPIN: "+data.pin;
                    swal("Account Details", accountInfo, "success");
                }
               
            });

    };
    
  return (
    <Button variant="contained" onClick={createNewAccount}>Create New Account</Button>
  )
}
