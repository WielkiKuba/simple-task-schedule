package com.grafik.grafikManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    @Autowired
    public WorkService(WorkRepository workRepository){this.workRepository = workRepository;}

    public List<Work> getByOwner(User owner){return workRepository.getByOwner(owner);}
    public List<Work> getByTimeStart(LocalDateTime timeStart){return workRepository.getByTimeStart(timeStart);}
    public List<Work> getByTimeEnd(LocalDateTime timeEnd){return workRepository.getByTimeStart(timeEnd);}
    public Optional<Work> getById(Long id){return workRepository.findById(id);}
    public List<Work> getAll(){return workRepository.findAll();}

    public List<Work> getAllInDay(LocalDate workDay) {
        LocalDateTime startOfDay = workDay.atStartOfDay();
        LocalDateTime endOfDay = workDay.plusDays(1).atStartOfDay().minusNanos(1);
        return workRepository.findByTimeStartBetween(startOfDay, endOfDay);
    }

    public void addWork(Work work){
        workRepository.save(work);
    }
    public void addWork(LocalDateTime timeStart,LocalDateTime timeEnd,User owner){
        Work workTemp = new Work();
        workTemp.setOwner(owner);
        workTemp.setTimeStart(timeStart);
        workTemp.setTimeEnd(timeEnd);
        addWork(workTemp);
    }

    public void delWork(Work work){
        workRepository.delete(work);
    }
}
