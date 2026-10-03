import React, { useState } from 'react';
import axios from 'axios';
import {
    Card, CardContent, Typography, TextField, Button, Stack, Snackbar, Alert, Grid, Box
} from '@mui/material';

const OdemePage = () => {
    const formatDateTime = (dtString) => {
        if (!dtString) return '—';
        const dt = new Date(dtString);
        return dt.toLocaleString('tr-TR', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
        });
    };
    const [plateNo, setPlateNo] = useState('');
    const [debtInfo, setDebtInfo] = useState([]);  // Borç bilgilerini tutan state
    const [snackbarOpen, setSnackbarOpen] = useState(false);

    // Borçları sorgulama fonksiyonu
    const handleDebtCheck = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/vehicles/debt', {
                params: { plateNo }
            });

            // Yanıtı kontrol et
            console.log(response.data); // Backend'den gelen yanıtı konsola yazdırıyoruz

            // Backend'den gelen borç bilgilerini debtDetails olarak set ediyoruz
            setDebtInfo(response.data.debtDetails || []);  // Eğer debtDetails varsa onu set et
        } catch (error) {
            alert('Borç sorgulama hatası: ' + error.message);
        }
    };

    // Ödeme işlemi fonksiyonu
    const handlePayment = async (debtId) => {
        try {
            await axios.post('http://localhost:8080/api/vehicles/pay', null, {
                params: { plateNo, debtId }
            });
            setSnackbarOpen(true);
            // Borçlar güncellendikten sonra tekrar sorgu yapıyoruz
            handleDebtCheck();
        } catch (error) {
            alert('Ödeme hatası: ' + error.message);
        }
    };

    return (
        <Card sx={{ maxWidth: 600, margin: 'auto', mt: 4 }}>
            <CardContent>
                <Typography variant="h5" gutterBottom>💳 Ödeme Yap</Typography>
                <Stack spacing={2}>
                    <TextField
                        label="Plaka No"
                        variant="outlined"
                        value={plateNo}
                        onChange={(e) => setPlateNo(e.target.value)}
                        fullWidth
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleDebtCheck}
                    >
                        Borçları Görüntüle
                    </Button>
                </Stack>

                {/* Borçlar listelendiğinde gösterilecek alan */}
                {debtInfo.length > 0 && (
                    <Box mt={3}>
                        <Typography variant="h6">Borçlar</Typography>
                        <Stack spacing={2} mt={2}>
                            {debtInfo.map((debt) => (
                                <Card sx={{ padding: 2 }} key={debt.id}>
                                    <Typography variant="body1">
                                        <strong>Plaka No:</strong> {debt.plateNo || 'Bilinmiyor'}
                                    </Typography>
                                    <Typography variant="body2">
                                        <strong>Borç:</strong> {debt.debtAmount} TL
                                    </Typography>
                                    <Typography variant="body2">{`Giriş Zamanı: ${formatDateTime(debt.entryTime)}`}</Typography>

                                    <Typography variant="body2">{`Çıkış Zamanı: ${formatDateTime(debt.exitTime)}`}</Typography>

                                    <Button
                                        variant="outlined"
                                        color="secondary"
                                        sx={{ mt: 1 }}
                                        onClick={() => handlePayment(debt.id)}
                                    >
                                        Öde
                                    </Button>
                                </Card>
                            ))}
                        </Stack>
                    </Box>
                )}

            </CardContent>

            {/* Ödeme sonrası gelen başarılı mesaj */}
            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={() => setSnackbarOpen(false)}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert severity="success" onClose={() => setSnackbarOpen(false)}>
                    Ödeme başarıyla tamamlandı!
                </Alert>
            </Snackbar>
        </Card>
    );
};

export default OdemePage;
