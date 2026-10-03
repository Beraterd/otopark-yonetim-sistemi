import axios from 'axios';

const BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api/vehicles';

export const addEntry = (plateNo, entryTime) => {
  return axios.post(`${BASE_URL}/entry`, null, {
    params: {
      plateNo,
      entryTime
    }
  });
};

export const addExit = (plateNo, exitTime) => {
  return axios.put(`${BASE_URL}/exit`, null, {
    params: {
      plateNo,
      exitTime: exitTime.trim()
    }
  });
};
