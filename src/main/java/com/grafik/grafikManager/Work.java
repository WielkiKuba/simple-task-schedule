package com.grafik.grafikManager;

import jakarta.persistence.*;

import java.time.*;

@Table(name = "work")
@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_start",nullable = false)
    private LocalDateTime timeStart;

    @Column(name = "time_end",nullable = false)
    private LocalDateTime timeEnd;

    @ManyToOne
    @JoinColumn(name = "owner" ,nullable = false)
    private User owner;

    public LocalDate workDay(){
        if(timeStart!=null){
            return timeStart.toLocalDate();
        }else{
            return null;
        }
    }


    public Long getId(){return id;}
    public LocalDateTime getTimeStart(){return timeStart;}
    public LocalDateTime getTimeEnd(){return timeEnd;}
    public User getOwner(){return owner;}
//    public LocalDate getWorkDay(){return workDay();}


    public void setId(Long id){this.id = id;}
    public void setTimeStart(LocalDateTime time_start){this.timeStart = time_start;}
    public void setTimeEnd(LocalDateTime time_end){this.timeEnd = time_end;}
    public void setOwner(User owner){this.owner = owner;}
}
