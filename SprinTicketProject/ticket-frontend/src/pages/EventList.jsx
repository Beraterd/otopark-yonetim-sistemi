import React, { useEffect, useState } from 'react';
import axios from 'axios';
import EventDetails from './EventDetails';

const EventList = () => {
  const [events, setEvents] = useState([]);
  const [selectedEventId, setSelectedEventId] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/events/upcoming')
      .then(res => setEvents(res.data))
      .catch(err => console.error("API ERROR:", err));
  }, []);

  return (
    <div>
      <h2>Yaklaşan Etkinlikler</h2>
      <ul>
        {events.map(event => (
          <li key={event.id}>
            <button onClick={() => setSelectedEventId(event.id)}>
              {event.name} - {new Date(event.plannedStartTime).toLocaleString()}
            </button>
          </li>
        ))}
      </ul>

      {/* ✅ Buradaki parantez, JSX bloğu TAMAMEN kapatılmış olmalı */}
      {selectedEventId && (
        <div style={{ marginTop: '20px' }}>
          <EventDetails eventId={selectedEventId} />
        </div>
      )}
    </div>
  );
};

export default EventList;
