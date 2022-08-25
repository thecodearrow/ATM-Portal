import React,{useState,useEffect} from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import TextField from '@mui/material/TextField';
import Cookies from 'universal-cookie';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

import Snackbar from '@mui/material/Snackbar';


export const ViewBalance = () => {
  const cookies=new Cookies();
  const [cardNumbers,setCardNumbers]=useState([]);
  const [selectedCardNumber, setSelectedCardNumber] = useState('');
  const [pin, setPin]=useState('');
  const [balanceAmount, setBalanceAmount] = useState(0);
  const [successOpen, setSuccessOpen] = useState(false);
  const [errorOpen, setErrorOpen] = useState(false);
  const [successMessage,setSuccessMessage]=useState("Success")
  const [errorMessage,setErrorMessage]=useState("Bad request")
  useEffect(() => {
    //get user id
    //fetch debit card numbers
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ "userID": cookies.get('user_id') })
  };
  fetch('http://localhost:8080/accounts', requestOptions)
      .then(response => response.json())
      .then(data => {
          if(data && data.status===true){
              //accounts fetch successful
              setCardNumbers(data.debitCardNumbers);
          }
         
      });

  },[]);
  
  const handleCardNumberSelectChange = (event: SelectChangeEvent) => {
    setSelectedCardNumber(event.target.value);
  };

  const checkBalance =()=>{
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ "debitCardNumber": selectedCardNumber,"pin":pin })
  };
  fetch('http://localhost:8080/view-balance', requestOptions)
      .then(response => response.json())
      .then(data => {
          if(data && data.status===true){
              ///success snackbar
              setBalanceAmount(data.balanceAmount);
              setSuccessMessage(data.message);
              setSuccessOpen(true); 
          }
          else{
            //error snackbar bad request
            setErrorMessage(data.message);
            setErrorOpen(true); 
            
          }
         
      })



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
    <Box sx={{ minWidth: 120 }}>
    <FormControl >
      <InputLabel id="demo-simple-select-label" required>Card Numbers</InputLabel>
      <Select
        style={{margin:10}}
        labelId="demo-simple-select-label"
        id="demo-simple-select"
        value={selectedCardNumber}
        label="Card Numbers"
        onChange={handleCardNumberSelectChange}
        
      >
        {cardNumbers.map((cardNumber,index)=>
        (<MenuItem key={index} value={cardNumber}>{cardNumber}</MenuItem>)
        )}
      </Select>
      <TextField style={{margin:10}} id="pin-number" label="Pin Number" variant="outlined" onChange={e => setPin(e.target.value)} required/>
      
      <Button style={{margin:10}} variant="contained" onClick={checkBalance}>View</Button>
    </FormControl>

    <Typography variant="h4" gutterBottom>
        Balance: Rs.{balanceAmount}
      </Typography>
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
  </Box>
  );
}
