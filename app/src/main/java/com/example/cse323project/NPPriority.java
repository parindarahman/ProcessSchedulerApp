package com.example.cse323project;

import android.nfc.Tag;
import android.util.Log;

public class NPPriority {

    int[] burstTime;
    int[] priority;
    int[] arrivalTime;
    int[] processId;
    int[] wt;
    int[] ta;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int m;
    int[][] values;
    String ganttChart;

    void setValues(int[][] valuesRecieved) {
        values = valuesRecieved;
    }

    float getAverageTurnAroundTime() {
        return averageTurnAroundTime;
    }

    float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    void setN(int N) {
        m = N;
    }

    String getGanttChart(){
        return ganttChart;
    }

    void setGanttChart(String gantt){
        ganttChart = gantt;
    }

    void getProcessData() {
        burstTime = new int[m];
        priority = new int[m];
        arrivalTime = new int[m];
        processId = new int[m];

        for (int i = 0; i < m; i++) {
            processId[i] = values[i][0];
            burstTime[i] = values[i][1];
            arrivalTime[i] = values[i][2];
            priority[i] = values[i][3];
        }

    }

    void sortAccordingArrivalTimeAndPriority(int[] at, int[] bt, int[] prt, int[] pid) {

        int temp;
        int stemp;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < m - i - 1; j++) {
                if (at[j] > at[j + 1]) {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    stemp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = stemp;

                }
                //sorting according to priority when arrival timings are same
                if (at[j] == at[j + 1]) {
                    if (prt[j] > prt[j + 1]) {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        //swapping process identity
                        stemp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = stemp;

                    }
                }
            }

        }
    }

    void priorityNonPreemptiveAlgorithm() {
        int[] finishTime = new int[m];
        int[] bt = burstTime.clone();
        int[] at = arrivalTime.clone();
        int[] prt = priority.clone();
        int[] pid = processId.clone();
        int[] waitingTime = new int[m];
        int[] turnAroundTime = new int[m];

        sortAccordingArrivalTimeAndPriority(at, bt, prt, pid);
        sortAccordingArrivalTimeAndPriority(arrivalTime,burstTime,priority,processId);

        //calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < m; i++) {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }
        float sum = 0;
        for (int n : waitingTime) {
            sum += n;
        }
        averageWaitingTime = sum / m;

        sum = 0;
        for (int n : turnAroundTime) {
            sum += n;
        }
        averageTurnAroundTime = sum / m;

        ta = turnAroundTime;
    }

    public void ganttChart(){

        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        int i, j;

        // print top bar
        s1 = s1+ " ";
        for(i=0; i<m; i++) {
            for(j=0; j<4; j++) s1 = s1 + "--";
            s1 = s1+ " ";
        }
        s1 = s1 + "\n|";

        // printing process id in the middle
        for(i=0; i<m; i++) {
            for(j=0; j<3 - 1; j++) s2 = s2 + " ";;
            s2 = s2 + "p";
            s2 = s2 + processId[i];
            for(j=0; j<3 - 1; j++) s2 = s2 + " ";
            s2 = s2 + "|";
        }
        s2 = s2 + "\n ";

        // printing bottom bar
        for(i=0; i<m; i++) {
            for(j=0; j<4; j++) s3 = s3 + "--";
            s3 = s3 + " ";
        }
        s3 = s3 + "\n";

        // printing the time line
        s4 = s4 + "0";
        for(i=0; i<m; i++) {
            for(j=0; j<3; j++) s4 = s4 + "  ";
            //if(ta[i] > 9) s4 = s4 + "\b"; // backspace : remove 1 space
            s4 = s4 + ta[i];

        }
        s4 = s4 + "\n";

        ganttChart = s1 + s2 + s3 + s4;

    }


}
