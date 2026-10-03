package com.berat.ticket.repository;

import com.berat.ticket.entity.SeatLock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatLockRepository extends JpaRepository<SeatLock, Long> {
}
