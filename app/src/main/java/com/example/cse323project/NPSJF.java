package com.example.cse323project;

public class NPSJF {

    int pid[];
    int at[];
    int bt[];
    int ct[];
    int ta[];
    int wt[];
    int f[];  // f means it is flag it checks process is completed or not
    int numberOfProcess;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int n;
    int st=0, tot=0;
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
        n = N;
    }

    String getGanttChart(){
        return ganttChart;
    }

    void setGanttChart(String gantt){
        ganttChart = gantt;
    }

    int temp;

    public void getProcess() {

        pid = new int[n];
        bt = new int[n];
        at = new int[n];
        ct = new int[n];
        ta = new int[n];
        wt = new int[n];
        f = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = values[i][0];
            bt[i] = values[i][1];
            at[i] = values[i][2];
            f[i] = 0;
        }
    }

    public void NonPreemptiveSJF(){

        boolean a = true;
        while(true)
        {
            int c=n, min=999;
            if (tot == n) // total no of process = completed process loop will be terminated
                break;
            for (int i=0; i<n; i++)
            {
                /*
                 * If i'th process arrival time <= system time and its flag=0 and burst<min
                 * That process will be executed first
                 */
                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }
            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
            if (c==n)
                st++;
            else
            {
                ct[c]=st+bt[c];
                st+=bt[c];
                ta[c]=ct[c]-at[c];
                wt[c]=ta[c]-bt[c];
                f[c]=1;
                tot++;
            }
        }
        for(int i=0;i<n;i++)
        {
            averageWaitingTime+= wt[i];
            averageTurnAroundTime+= ta[i];
        }

        averageWaitingTime /= n;


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
