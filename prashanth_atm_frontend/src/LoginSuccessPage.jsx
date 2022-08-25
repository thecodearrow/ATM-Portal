import React, { useEffect, useState,SyntheticEvent } from 'react';
import Cookies from "universal-cookie";
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import { Link } from "react-router-dom";
import AccountOperations from './AccountOperations';
import {TransactionsTable} from "./TransactionsTable.tsx";
interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
  }
  
  function TabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;
  
    return (
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`simple-tabpanel-${index}`}
        aria-labelledby={`simple-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box sx={{ p: 3 }}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    );
  }
  
  function a11yProps(index: number) {
    return {
      id: `simple-tab-${index}`,
      'aria-controls': `simple-tabpanel-${index}`,
    };
  }

const LoginSuccessPage = () => {
    const [userToken,setUserToken]=React.useState(null);
    const [userID,setUserID]=React.useState(null);
    const [value, setValue] = useState(0);
  const handleChange = (event: SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };
    useEffect(() => {
        //get user id
        const cookies=new Cookies();
        let id=cookies.get('user_id');
        setUserID(id);
      },[]);
      
    return (
        userID!=null &&
       <Box sx={{ width: '100%' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
          <Tab label="Account" {...a11yProps(0)} />
          <Tab label="Transactions" {...a11yProps(1)} />
          <Tab label="Sign out" {...a11yProps(2)} />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <AccountOperations />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <TransactionsTable />
      </TabPanel>
      <TabPanel value={value} index={2}>
      <Link to="/">LOG OUT</Link>
      </TabPanel>
    </Box>);
};

export default LoginSuccessPage;