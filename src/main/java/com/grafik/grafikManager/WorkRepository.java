package com.grafik.grafikManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkRepository extends JpaRepository<Work,Long> {
    List<Work> getByOwner(User owner);
    List<Work> getByTimeStart(LocalDateTime timeStart);
    List<Work> getByTimeEnd(LocalDateTime timeEnd);
    List<Work> findByTimeStartBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
    List<Work> findByOwnerAndTimeStartBetween(User owner, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
