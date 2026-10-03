import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
    Card, CardContent, Typography, TextField, Button, Stack, Snackbar, Alert, MenuItem, Select, FormControl, InputLabel
} from '@mui/material';

function ExitPage() {
    const [plateNo, setPlateNo] = useState('');
    const [exitTime, setExitTime] = useState('');
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [fee, setFee] = useState(null); // Toplam ücreti tutacak
    const [vehicles, setVehicles] = useState([]);
    const [loading, setLoading] = useState(true);


    useEffect(() => {
        const fetchEntryVehicles = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/vehicles/entry-vehicles');
                console.log(response.data); // Burada gelen veriyi kontrol edin
                setVehicles(response.data);
                setLoading(false);
            } catch (error) {
                console.error("Giriş yapan araçlar alınırken hata oluştu:", error);
                setLoading(false);
            }
        };

        fetchEntryVehicles();
    }, []);


    // Çıkış işlemi sonrası araç listesini tekrar çek
    const handleSubmit = async () => {
        if (!plateNo) {
            alert("Plaka zorunludur.");
            return;
        }

        let exitTimeToSend = exitTime;
        if (!exitTimeToSend) {
            const now = new Date();
            now.setHours(now.getHours() + 3);
            exitTimeToSend = now.toISOString().slice(0, 16);
        }

        try {
            const response = await axios.put('http://localhost:8080/api/vehicles/exit', null, {
                params: { plateNo, exitTime: exitTimeToSend }
            });

            setFee(response.data.totalFee);
            setSnackbarOpen(true);
            setPlateNo('');
            setExitTime('');

            // Güncellenmiş araçları tekrar çek
            const updatedVehicles = await axios.get('http://localhost:8080/api/vehicles/entry-vehicles');
            setVehicles(updatedVehicles.data);

        } catch (error) {
            // ❌ Hata mesajını kullanıcıya göster
            alert(error.response?.data?.message || error.message);
        }
    };






    if (loading) return <Typography>Yükleniyor...</Typography>;

    return (
        <Card sx={{ maxWidth: 600, margin: 'auto', mt: 4 }}>
            <CardContent>
                <Typography variant="h5" gutterBottom>
                    🅿️ Araç Çıkışı
                </Typography>

                <Stack spacing={2}>
                    <FormControl fullWidth>
                        <InputLabel>Plaka No</InputLabel>
                        <Select
                            value={plateNo}
                            onChange={(e) => setPlateNo(e.target.value)}
                            label="Plaka No"
                        >
                            {vehicles.length > 0 ? (
                                vehicles.map((vehicle) => (
                                    <MenuItem key={vehicle.id} value={vehicle.plateNo}>
                                        {vehicle.plateNo}
                                    </MenuItem>
                                ))
                            ) : (
                                <MenuItem disabled>Giriş yapan araç yok</MenuItem>
                            )}
                        </Select>
                    </FormControl>

                    <TextField
                        label="Çıkış Zamanı"
                        type="datetime-local"
                        InputLabelProps={{ shrink: true }}
                        value={exitTime}
                        onChange={(e) => setExitTime(e.target.value)}
                        fullWidth
                    />

                    <Button variant="contained" color="warning" onClick={handleSubmit}>
                        Çıkış Yap
                    </Button>
                </Stack>
            </CardContent>

            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={() => setSnackbarOpen(false)}
                anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
            >
                <Alert severity="info" onClose={() => setSnackbarOpen(false)}>
                    Çıkış kaydedildi. Ücret: {fee}₺
                </Alert>
            </Snackbar>
        </Card>
    );
}

export default ExitPage;
