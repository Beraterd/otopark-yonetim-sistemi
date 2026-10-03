import React, { useState } from 'react';
import axios from 'axios';
import {
    Card, CardContent, Typography, TextField, Button, Stack, Snackbar, Alert
} from '@mui/material';

const BorcSorgulaPage = () => {
    const [plateNo, setPlateNo] = useState('');
    const [borc, setBorc] = useState(null);
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    const handleSorgula = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/vehicles/debt', {
                params: { plateNo }
            });

            const debt = response.data.totalDebt;

            if (debt > 0) {
                setBorc(debt);
                setSnackbarOpen(true);
            } else {
                setBorc(0);
                setSnackbarOpen(true);
            }
        } catch (error) {
            if (error.response && error.response.status === 404) {
                alert('Araç bulunamadı. Lütfen plaka numarasını kontrol edin.');
            } else {
                alert('Borç sorgulama hatası: ' + error.message);
            }
        }
    };

    return (
        <Card sx={{ maxWidth: 600, margin: 'auto', mt: 4 }}>
            <CardContent>
                <Typography variant="h5" gutterBottom>💰 Borç Sorgulama</Typography>
                <Stack spacing={2}>
                    <TextField
                        label="Plaka No"
                        variant="outlined"
                        value={plateNo}
                        onChange={(e) => setPlateNo(e.target.value)} // Plaka numarasını alıyoruz
                        fullWidth
                    />
                    <Button variant="contained" color="info" onClick={handleSorgula}>
                        Borcu Sorgula
                    </Button>
                </Stack>

                {/* Borcu yazdırmak için Typography ekliyoruz */}
                {borc !== null && (
                    <Typography variant="h6" sx={{ mt: 3, textAlign: 'center' }}>
                        {borc > 0 ? `Toplam borç: ${borc}₺` : 'Borç bulunmamaktadır.'}
                    </Typography>
                )}
            </CardContent>

            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={() => setSnackbarOpen(false)}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert severity="info" onClose={() => setSnackbarOpen(false)}>
                    {borc > 0 ? `Toplam borç: ${borc}₺` : 'Borç bulunmamaktadır.'}
                </Alert>
            </Snackbar>
        </Card>
    );
};

export default BorcSorgulaPage;
