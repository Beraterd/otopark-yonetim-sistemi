// src/pages/EventDetails.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';

const EventDetails = ({ eventId }) => {
  const [freeSeats, setFreeSeats] = useState([]);
  const [ownerName, setOwnerName] = useState("");
  const [selectedSeatId, setSelectedSeatId] = useState(null);
  const [message, setMessage] = useState("");

  useEffect(() => {
    if (!eventId) return;
    axios.get(`http://localhost:8080/api/events/${eventId}/seats/free`)
      .then(res => setFreeSeats(res.data))
      .catch(err => console.error("Seats error:", err));
  }, [eventId]);

  const handleBuyTicket = async () => {
    if (!ownerName || !selectedSeatId) {
      setMessage("Lütfen adınızı girin ve bir koltuk seçin.");
      return;
    }

    try {
      // 1. Seat lock
      await axios.post(`http://localhost:8080/api/events/${eventId}/seats/${selectedSeatId}/lock`);
      // 2. Create ticket
      const res = await axios.post(`http://localhost:8080/api/tickets`, null, {
        params: {
          eventId,
          seatId: selectedSeatId,
          ownerName
        }
      });
      setMessage(`✅ Bilet başarıyla oluşturuldu. ID: ${res.data.id}`);
    } catch (err) {
      setMessage(`❌ ${err.response?.data || 'Hata oluştu'}`);
    }
  };

  return (
    <div>
      <h3>Etkinlik Koltukları</h3>
      <ul>
  {Array.isArray(freeSeats) && freeSeats.length > 0 ? (
    freeSeats.map(seat => (
      <li key={seat.idSeat}>
        <input
          type="radio"
          name="seat"
          value={seat.idSeat}
          onChange={() => setSelectedSeatId(seat.idSeat)}
        />
        Sıra: {seat.row} - Koltuk: {seat.seatNumber}
      </li>
    ))
  ) : (
    <p>Boş koltuk yok veya yükleniyor...</p>
  )}
</ul>


      <input
        type="text"
        placeholder="Adınız"
        value={ownerName}
        onChange={(e) => setOwnerName(e.target.value)}
      />
      <button onClick={handleBuyTicket}>Bilet Al</button>

      {message && <p>{message}</p>}
    </div>
  );
};

export default EventDetails;
