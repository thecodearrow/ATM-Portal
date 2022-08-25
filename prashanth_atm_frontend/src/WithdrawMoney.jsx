import React,{useState,useEffect} from 'react'
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import TextField from '@mui/material/TextField';
import Cookies from 'universal-cookie';
import Button from '@mui/material/Button';
import Snackbar from '@mui/material/Snackbar';

export const WithdrawMoney = () => {
    const cookies=new Cookies();
    const [cardNumbers,setCardNumbers]=useState([]);
    const [selectedCardNumber, setSelectedCardNumber] = useState('');
    const [pin, setPin]=useState('');
    const [withDrawAmount, setWithDrawAmount] = useState(0);
    const [open1, setOpen1] = useState(false);
    const [open2, setOpen2] = useState(false);
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
                //account creation success
                setCardNumbers(data.debitCardNumbers);
            }
            
           
        });
  
    },[]);
    
    const handleCardNumberSelectChange = (event: SelectChangeEvent) => {
      setSelectedCardNumber(event.target.value);
    };
  
    const withdrawMoney =()=>{
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "debitCardNumber": selectedCardNumber,"pin":pin,"amount":withDrawAmount})
    };
    fetch('http://localhost:8080/withdraw-money', requestOptions)
        .then(response => response.json())
        .then(data => {
            if(data && data.status===true){
                //withdraw success
                setSuccessOpen(true);
                setSuccessMessage(data.message+"Updated Balance: Rs."+data.balanceAmount);
            }
            else{
                setErrorOpen(true);
                setErrorMessage(data.message);
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
        <TextField style={{margin:10}} id="pin-number" label="Pin Number" variant="outlined" onChange={e => setPin(e.target.value)} required/>
        <TextField style={{margin:10}} id="amount" label="Amount" variant="outlined" onChange={e => setWithDrawAmount(e.target.value)} required/>
        
        <Button variant="contained" onClick={withdrawMoney}>Withdraw</Button>
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
