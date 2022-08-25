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


export const ChangePin = () => {
  const cookies=new Cookies();
  const [cardNumbers,setCardNumbers]=useState([]);
  const [selectedCardNumber, setSelectedCardNumber] = useState('');
  const [oldPin, setOldPin]=useState('');
  const [newPin, setNewPin]=useState('');
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

  const changePin =()=>{
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ "debitCardNumber": selectedCardNumber,"oldPin":oldPin,"newPin":newPin })
  };
  fetch('http://localhost:8080/change-pin', requestOptions)
      .then(response => response.json())
      .then(data => {
          if(data && data.status===true){
              //view balance success
              setSuccessMessage(data.message);
              setSuccessOpen(true); 
          }
          else{
            //error snackbar bad request
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
      <TextField style={{margin:10}} id="old-pin-number" label="Old Pin Number" variant="outlined" onChange={e => setOldPin(e.target.value)} required/>
      <TextField style={{margin:10}} id="new-pin-number" label="New Pin Number" variant="outlined" onChange={e => setNewPin(e.target.value)} required/>
      
      <Button style={{margin:10}} variant="contained" onClick={changePin}>Change PIN</Button>
    </FormControl>
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
