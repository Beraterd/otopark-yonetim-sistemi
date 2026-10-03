import React, { useState } from 'react';
import { addEntry } from '../services/api';

import {
  Card,
  CardContent,
  Typography,
  TextField,
  Button,
  Stack,
  Snackbar,
  Alert
} from '@mui/material';

const EntryPage = () => {
  const [plateNo, setPlateNo] = useState('');
  const [entryTime, setEntryTime] = useState('');
  const [snackbar, setSnackbar] = useState({
    open: false,
    message: '',
    severity: 'info', // "success", "error", "warning", "info"
  });

  const handleSubmit = async () => {
    if (!plateNo) {
      setSnackbar({
        open: true,
        message: 'Lütfen plaka bilgisini girin!',
        severity: 'warning',
      });
      return;
    }

    try {
      const formattedTime = entryTime.length === 16 ? entryTime + ":00" : entryTime;
      await addEntry(plateNo, formattedTime);
      setSnackbar({
        open: true,
        message: 'Araç girişi başarıyla kaydedildi!',
        severity: 'success',
      });
      setPlateNo('');
      setEntryTime('');
    } catch (error) {
      setSnackbar({
        open: true,
        message: 'Hata oluştu: ' + (error.response?.data?.message || error.message),
        severity: 'error',
      });
    }
  };

  return (
    <Card sx={{ maxWidth: 600, margin: 'auto', mt: 4 }}>
      <CardContent>
        <Typography variant="h5" gutterBottom>
          🚗 Araç Girişi
        </Typography>

        <Stack spacing={2}>
          <TextField
            label="Plaka No"
            variant="outlined"
            value={plateNo}
            onChange={(e) => setPlateNo(e.target.value)}
            fullWidth
          />
          <TextField
            label="Giriş Zamanı"
            type="datetime-local"
            InputLabelProps={{ shrink: true }}
            value={entryTime}
            onChange={(e) => setEntryTime(e.target.value)}
            fullWidth
          />
          <Button variant="contained" color="success" onClick={handleSubmit}>
            Giriş Yap
          </Button>
        </Stack>
      </CardContent>

      <Snackbar
        open={snackbar.open}
        autoHideDuration={3000}
        onClose={() => setSnackbar({ ...snackbar, open: false })}
        anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
      >
        <Alert
          severity={snackbar.severity}
          onClose={() => setSnackbar({ ...snackbar, open: false })}
          sx={{ width: '100%' }}
        >
          {snackbar.message}
        </Alert>
      </Snackbar>
    </Card>
  );
};

export default EntryPage;
