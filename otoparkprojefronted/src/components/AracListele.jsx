import React, { useEffect, useState } from "react";
import axios from "axios";
import {
    Card,
    CardContent,
    Typography,
    Grid,
    TextField,
    Select,
    MenuItem,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    Paper,
    Chip,
} from "@mui/material";

const AracListele = () => {
    const [araclar, setAraclar] = useState([]);
    const [arama, setArama] = useState("");
    const [durumFiltre, setDurumFiltre] = useState("");
    const [loading, setLoading] = useState(true);

    // Backend API çağrısı
    useEffect(() => {
        axios.get("http://localhost:8080/api/vehicles")
            .then((response) => {
                setAraclar(response.data);
                setLoading(false);
            })
            .catch((error) => {
                console.error("API bağlantı hatası:", error);
                setLoading(false);
            });
    }, []);

    // Durum etiketi
    const durumEtiketi = (durum) => {
        switch (durum) {
            case "giris":
                return <Chip label="Çıkış Bekleniyor" color="primary" />;
            case "çikis":
                return <Chip label="Ödeme Bekleniyor" color="warning" />;
            case "ödendi":
                return <Chip label="Ödeme Tamamlandı" color="success" />;
            default:
                return <Chip label="Bilinmiyor" />;
        }
    };

    // Filtreleme
    const filtrelenmisAraclar = araclar.filter((arac) =>
        arac.plateNo.toLowerCase().includes(arama.toLowerCase()) &&
        (durumFiltre === "" || arac.status === durumFiltre)
    );

    // Dashboard sayısı
    const toplamGiris = filtrelenmisAraclar.filter(a => a.status === "giris").length;
    const toplamCikis = filtrelenmisAraclar.filter(a => a.status === "çikis").length;
    const toplamOdeme = filtrelenmisAraclar.filter(a => a.status === "ödendi").length;

    if (loading) return <Typography>Yükleniyor...</Typography>;

    return (
        <Paper sx={{ padding: 3, marginTop: 4 }}>
            <Typography variant="h5" gutterBottom>
                📋 Araç Listesi
            </Typography>

            {/* Dashboard */}
            <Grid container spacing={2} mb={3}>
                <Grid item xs={12} md={4}>
                    <Card sx={{ backgroundColor: "#4caf50", color: "#fff" }}>
                        <CardContent>
                            <Typography variant="h6">Giriş Yapan Araçlar</Typography>
                            <Typography variant="h4">{toplamGiris}</Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} md={4}>
                    <Card sx={{ backgroundColor: "#ff9800", color: "#000" }}>
                        <CardContent>
                            <Typography variant="h6">Çıkış Yapan Araçlar</Typography>
                            <Typography variant="h4">{toplamCikis}</Typography>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={12} md={4}>
                    <Card sx={{ backgroundColor: "#2196f3", color: "#fff" }}>
                        <CardContent>
                            <Typography variant="h6">Ödeme Yapan Araçlar</Typography>
                            <Typography variant="h4">{toplamOdeme}</Typography>
                        </CardContent>
                    </Card>
                </Grid>
            </Grid>

            {/* Arama ve filtre */}
            <Grid container spacing={2} mb={3}>
                <Grid item xs={12} md={6}>
                    <TextField
                        label="Plaka ile ara"
                        variant="outlined"
                        fullWidth
                        value={arama}
                        onChange={(e) => setArama(e.target.value)}
                    />
                </Grid>
                <Grid item xs={12} md={6}>
                    <Select
                        fullWidth
                        value={durumFiltre}
                        onChange={(e) => setDurumFiltre(e.target.value)}
                        displayEmpty
                    >
                        <MenuItem value="">Tüm Durumlar</MenuItem>
                        <MenuItem value="giris">Giriş</MenuItem>
                        <MenuItem value="çikis">Çıkış</MenuItem>
                        <MenuItem value="ödendi">Ödendi</MenuItem>
                    </Select>
                </Grid>
            </Grid>

            {/* Tablo */}
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Plaka</TableCell>
                        <TableCell>Giriş Zamanı</TableCell>
                        <TableCell>Çıkış Zamanı</TableCell>
                        <TableCell>Ödeme Durumu</TableCell>
                        <TableCell>Durum</TableCell>
                        <TableCell>Toplam Ücret</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {filtrelenmisAraclar.length > 0 ? (
                        filtrelenmisAraclar.map((arac) => {
                            // Durum "çıkış" olan araçlar için özel düzenleme
                            const isExit = arac.status === "çıkış";

                            return (
                                <TableRow key={arac.id}>
                                    <TableCell>{arac.plateNo}</TableCell>
                                    <TableCell>{arac.entryTime || "—"}</TableCell>
                                    <TableCell>{arac.exitTime || "—"}</TableCell>
                                    <TableCell>
                                        {arac.status === "ödendi" ? "Ödeme Yapıldı" :
                                            arac.status === "giris" ? "Giriş Yapıldı" :
                                                arac.status === "çikis" ? "Çıkış Yapıldı" :
                                                    "Bilinmiyor"}
                                    </TableCell>

                                    <TableCell>
                                        {isExit ? "Ödeme Bekleniyor" : durumEtiketi(arac.status)}
                                    </TableCell>
                                    <TableCell>{arac.totalFee ? `${arac.totalFee}₺` : "—"}</TableCell>
                                </TableRow>
                            );
                        })
                    ) : (
                        <TableRow>
                            <TableCell colSpan={6} align="center">Sonuç bulunamadı.</TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>
        </Paper>
    );
};

export default AracListele;
