package com.example.cse323project;

public class FCFS {

    int pid[];
    int ar[];
    int bt[];
    int ct[];
    int ta[];
    int wt[];
    int numberOfProcess;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int n;
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

    String getGanttChart(){
        return ganttChart;
    }

    void setGanttChart(String gantt){
        ganttChart = gantt;
    }

    void setN(int N) {
        n = N;
    }

    int temp;

    public void getProcess() {
        pid = new int[n];
        bt = new int[n];
        ar = new int[n];
        ct = new int[n];
        ta = new int[n];
        wt = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = values[i][0];
            bt[i] = values[i][1];
            ar[i] = values[i][2];
        }
    }

    public void FirstComeFirstServeAlgorithm(){

    //sorting according to arrival times
        for(int i = 0 ; i <n; i++)
    {
        for(int  j=0;  j < n-(i+1) ; j++)
        {
            if( ar[j] > ar[j+1] )
            {
                temp = ar[j];
                ar[j] = ar[j+1];
                ar[j+1] = temp;
                temp = bt[j];
                bt[j] = bt[j+1];
                bt[j+1] = temp;
                temp = pid[j];
                pid[j] = pid[j+1];
                pid[j+1] = temp;
            }
        }
    }
// finding completion times
        for(int  i = 0 ; i < n; i++)
    {
        if( i == 0)
        {
            ct[i] = ar[i] + bt[i];
        }
        else
        {
            if( ar[i] > ct[i-1])
            {
                ct[i] = ar[i] + bt[i];
            }
            else
                ct[i] = ct[i-1] + bt[i];
        }
        ta[i] = ct[i] - ar[i] ;          // turnaround time= completion time- arrival time
        wt[i] = ta[i] - bt[i] ;          // waiting time= turnaround time- burst time
        averageWaitingTime += wt[i] ;               // total waiting time
        averageTurnAroundTime += ta[i] ;               // total turnaround time
    }

    averageTurnAroundTime = (float)averageTurnAroundTime/n;
    averageWaitingTime = (float)averageWaitingTime/n;


}

public void ganttChart(){

    String s1 = "";
    String s2 = "";
    String s3 = "";
    String s4 = "";
    int i, j;

    // print top bar
    s1 = s1+ " ";
    for(i=0; i<n; i++) {
        for(j=0; j<4; j++) s1 = s1 + "--";
        s1 = s1+ " ";
    }
    s1 = s1 + "\n|";

    // printing process id in the middle
    for(i=0; i<n; i++) {
        for(j=0; j<3 - 1; j++) s2 = s2 + " ";;
        s2 = s2 + "p";
        s2 = s2 + pid[i];
        for(j=0; j<3 - 1; j++) s2 = s2 + " ";
        s2 = s2 + "|";
    }
    s2 = s2 + "\n ";

    // printing bottom bar
    for(i=0; i<n; i++) {
        for(j=0; j<4; j++) s3 = s3 + "--";
        s3 = s3 + " ";
    }
    s3 = s3 + "\n";

    // printing the time line
    s4 = s4 + "0";
    for(i=0; i<n; i++) {
        for(j=0; j<3; j++) s4 = s4 + "  ";
        //if(ta[i] > 9) s4 = s4 + "\b"; // backspace : remove 1 space
        s4 = s4 + ta[i];

    }
    s4 = s4 + "\n";

    ganttChart = s1 + s2 + s3 + s4;

}


}
