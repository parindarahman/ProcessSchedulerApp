package com.example.cse323project;

import java.io.Serializable;

public class Process implements Serializable {
        private String id;
        private String burstTime;
        private String arrivalTime;
        private String priority;
        private String timeQuantum;

    public Process(String id, String burstTime, String arrivalTime, String priority, String timeQuantum) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.timeQuantum = timeQuantum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(String burstTime) {
        this.burstTime = burstTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum) {
        this.timeQuantum = timeQuantum;
    }
}
