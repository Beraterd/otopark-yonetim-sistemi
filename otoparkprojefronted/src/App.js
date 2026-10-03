import React from "react";
import { BrowserRouter, Routes, Route, Link } from "react-router-dom";
import { Container, AppBar, Toolbar, Button, Box } from "@mui/material";

import EntryPage from "./pages/EntryPage";
import ExitPage from "./pages/ExitPage";
import AracListele from "./components/AracListele";
import BorcSorgulaPage from "./pages/BorcSorgulaPage";
import OdemePage from "./pages/OdemePage";
import OtoparklarPage from "./pages/OtoparklarPage";

function App() {
  return (
    <BrowserRouter>
      {/* Navbar */}
      <AppBar
        position="static"
        sx={{
          background: "linear-gradient(90deg, #673ab7 0%, #9c27b0 100%)",
          boxShadow: "0 3px 10px rgba(0,0,0,0.25)",
        }}
      >
        <Toolbar sx={{ justifyContent: "center" }}>
          <Box sx={{ display: "flex", gap: 2 }}>
            {[
              { path: "/giris-cikis", label: "Giriş - Çıkış" },
              { path: "/araclar", label: "Araçları Listele" },
              { path: "/borc-sorgula", label: "Borç Sorgulama" },
              { path: "/odeme", label: "Ödeme Yap" },
              { path: "/otoparklar", label: "Otoparklar" },
            ].map((item) => (
              <Button
                key={item.path}
                component={Link}
                to={item.path}
                sx={{
                  fontSize: "0.95rem",
                  fontWeight: "bold",
                  px: 3,
                  py: 1,
                  borderRadius: "50px", // Oval görünüm
                  backgroundColor: "rgba(255,255,255,0.15)",
                  color: "white",
                  backdropFilter: "blur(6px)",
                  "&:hover": {
                    backgroundColor: "rgba(255,255,255,0.3)",
                    transform: "scale(1.05)",
                  },
                  transition: "all 0.3s ease",
                }}
              >
                {item.label}
              </Button>
            ))}
          </Box>
        </Toolbar>
      </AppBar>

      {/* Sayfa içerikleri */}
      <Container maxWidth="lg" sx={{ mt: 4 }}>
        <Routes>
          <Route
            path="/giris-cikis"
            element={
              <>
                <EntryPage />
                <Box mt={4}>
                  <ExitPage />
                </Box>
              </>
            }
          />
          <Route path="/araclar" element={<AracListele />} />
          <Route path="/borc-sorgula" element={<BorcSorgulaPage />} />
          <Route path="/odeme" element={<OdemePage />} />
          <Route path="/otoparklar" element={<OtoparklarPage />} />

          {/* Varsayılan sayfa */}
          <Route path="*" element={<AracListele />} />
        </Routes>
      </Container>
    </BrowserRouter>
  );
}

export default App;
