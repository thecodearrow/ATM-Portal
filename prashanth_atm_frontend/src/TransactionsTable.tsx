import React,{useState,useEffect} from 'react'
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import TextField from '@mui/material/TextField';
import Cookies from 'universal-cookie';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';

function createData(
    id: number,
    timestamp:string,
    transactionType: string,
    transactionAmount: number,
  ) {
    return { id,timestamp,transactionType,transactionAmount };
  }
  
  
export const TransactionsTable = () => {
    const cookies=new Cookies();
    const [cardNumbers,setCardNumbers]=useState([]);
    const [selectedCardNumber, setSelectedCardNumber] = useState('');
    const [pin, setPin]=useState('');
    const [tableRows,setTableRows]=useState([]);
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

    const viewTransactions =()=>{
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "debitCardNumber": selectedCardNumber,"pin":pin })
    };
    fetch('http://localhost:8080/view-transactions', requestOptions)
        .then(response => response.json())
        .then(data => {
            if(data && data.status===true){
                //view transactions success
                let rows=[];
                //console.log(data);
                for(let row of data.transactions){
                  rows.push(createData(row[0],row[1],row[2],row[3]));
                }
                setTableRows(rows);
            
            }
           
        });
  
    }

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
        <Button variant="contained" onClick={viewTransactions}>View Transactions</Button>
      </FormControl>
{tableRows.length > 0 && 
      <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Transaction ID</TableCell>
            <TableCell align="right">Timestamp</TableCell>
            <TableCell align="right">Transaction Type</TableCell>
            <TableCell align="right">Transaction Amount</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {tableRows.map((row) => (
            <TableRow
              key={row.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.id}
              </TableCell>
              <TableCell align="right">{row.timestamp}</TableCell>
              <TableCell align="right">{row.transactionType}</TableCell>
              <TableCell align="right">{row.transactionAmount}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  
          }
    </Box>
    
  )
}
