import React, { useEffect, useState } from 'react';
import axios from 'axios';

const FreeSeats = ({ eventId }) => {
  const [seats, setSeats] = useState([]);

  useEffect(() => {
    if (!eventId) return;
    axios.get(`http://localhost:8080/api/events/${eventId}/seats/free`)
      .then(res => setSeats(res.data))
      .catch(err => console.error('Seat API error:', err));
  }, [eventId]);

  return (
    <div>
      <h3>Boş Koltuklar</h3>
      {seats.length === 0 ? (
        <p>Boş koltuk yok veya yükleniyor...</p>
      ) : (
        <ul>
          {seats.map(seat => (
            <li key={seat.idSeat}>
              Sıra: {seat.row} - Koltuk: {seat.seatNumber}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default FreeSeats;
