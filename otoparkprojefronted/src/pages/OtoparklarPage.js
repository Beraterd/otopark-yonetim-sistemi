import React, { useState } from "react";
import { GoogleMap, LoadScript, Marker, InfoWindow } from "@react-google-maps/api";
import { Button, Typography, Box, LinearProgress } from "@mui/material";

const containerStyle = {
    width: "100%",
    height: "600px",
};

const center = { lat: 41.0082, lng: 28.9784 }; // İstanbul merkez

function OtoparklarPage() {
    const [selected, setSelected] = useState(null);

    const handleDirections = (lat, lng) => {
        window.open(`https://www.google.com/maps/dir/?api=1&destination=${lat},${lng}`);
    };

    const otoparklar = [
        { id: 1, name: "PENDİK", lat: 40.877, lng: 29.252, doluluk: 70 },
        { id: 2, name: "KARTAL", lat: 40.903, lng: 29.184, doluluk: 62 },
        { id: 3, name: "KADIKÖY", lat: 40.990, lng: 29.027, doluluk: 98 },
        { id: 4, name: "MALTEPE", lat: 40.935, lng: 29.131, doluluk: 44 },
        { id: 5, name: "BEŞİKTAŞ", lat: 41.043, lng: 29.008, doluluk: 80 },
        { id: 6, name: "FATİH", lat: 41.020, lng: 28.933, doluluk: 100 },
        { id: 7, name: "ATAŞEHİR", lat: 40.992, lng: 29.127, doluluk: 34 },
        { id: 8, name: "BEYOĞLU", lat: 41.036, lng: 28.976, doluluk: 77 },
        { id: 9, name: "SARIYER", lat: 41.169, lng: 29.049, doluluk: 43 },
        { id: 10, name: "ÜSKÜDAR", lat: 41.025, lng: 29.015, doluluk: 72 },
    ];

    return (
        <LoadScript googleMapsApiKey={process.env.REACT_APP_GOOGLE_MAPS_API_KEY}>
            <GoogleMap mapContainerStyle={containerStyle} center={center} zoom={11}>
                {otoparklar.map((otopark) => (
                    <Marker
                        key={otopark.id}
                        position={{ lat: otopark.lat, lng: otopark.lng }}
                        onClick={() => setSelected(otopark)}
                    />
                ))}

                {selected && (
                    <InfoWindow
                        position={{ lat: selected.lat, lng: selected.lng }}
                        onCloseClick={() => setSelected(null)}
                    >
                        <Box sx={{ minWidth: 200 }}>
                            <Typography variant="h6">{selected.name} Otoparkı</Typography>
                            <Typography variant="body2" sx={{ mb: 1 }}>
                                Doluluk Oranı: %{selected.doluluk}
                            </Typography>

                            {/* Doluluk barı */}
                            <LinearProgress
                                variant="determinate"
                                value={selected.doluluk}
                                sx={{
                                    height: 10,
                                    borderRadius: 5,
                                    mb: 2,
                                    "& .MuiLinearProgress-bar": {
                                        backgroundColor:
                                            selected.doluluk > 80
                                                ? "red"
                                                : selected.doluluk > 50
                                                    ? "orange"
                                                    : "green",
                                    },
                                }}
                            />

                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => handleDirections(selected.lat, selected.lng)}
                                fullWidth
                            >
                                Yol Tarifi
                            </Button>
                        </Box>
                    </InfoWindow>
                )}
            </GoogleMap>
        </LoadScript>
    );
}

export default OtoparklarPage;
