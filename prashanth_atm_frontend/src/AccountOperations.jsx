import React,{useState} from 'react'
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import {AccountCreate} from './AccountCreate';
import {DepositMoney} from './DepositMoney';
import {WithdrawMoney} from './WithdrawMoney';
import {ChangePin} from './ChangePin';
import {ViewBalance} from './ViewBalance';
import {UserProfile} from './UserProfile';

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

function AccountOperations() {
    const [value, setValue] = useState(0);
    const handleChange = (event: SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };
  return (
    <Box sx={{ width: '100%' }}>
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
          <Tab label="Create Account" {...a11yProps(0)} />
          <Tab label="View Balance" {...a11yProps(1)} />
          <Tab label="Change Pin" {...a11yProps(2)} />
          <Tab label="Deposit Money" {...a11yProps(3)} />
          <Tab label="Withdraw Money" {...a11yProps(4)} />
          <Tab label="User Profile" {...a11yProps(5)} />
        </Tabs>
      </Box>
      <TabPanel value={value} index={0}>
        <AccountCreate />
      </TabPanel>
      <TabPanel value={value} index={1}>
        <ViewBalance />
      </TabPanel>
      <TabPanel value={value} index={2}>
        <ChangePin />
      </TabPanel>
      <TabPanel value={value} index={3}>
        <DepositMoney />
      </TabPanel>
      <TabPanel value={value} index={4}>
        <WithdrawMoney />
      </TabPanel>
      <TabPanel value={value} index={5}>
        <UserProfile />
      </TabPanel>
    </Box>
  )
}



export default AccountOperations;
