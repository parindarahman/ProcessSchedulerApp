package com.example.cse323project;

public class PSJF {

    int pid[];
    int at[];
    int bt[];
    int ct[];
    int ta[];
    int wt[];
    int f[];  // f means it is flag it checks process is completed or not
    int k[];  // it is also stores brust time
    int numberOfProcess;
    float averageTurnAroundTime;
    float averageWaitingTime;
    int n;
    int st=0, tot=0;
    int[][] values;

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

    int temp;

    public void getProcess() {

        pid = new int[n];
        bt = new int[n];
        at = new int[n];
        ct = new int[n];
        ta = new int[n];
        wt = new int[n];
        k = new int[n];
        f = new int[n];

        for (int i = 0; i < n; i++) {
            pid[i] = values[i][0];
            bt[i] = values[i][1];
            at[i] = values[i][2];

            k[i]= bt[i];
            f[i]= 0;
        }
    }

    public void PreemptiveShortestJobFirst(){

        while(true){
            int min=99,c=n;
            if (tot==n)
                break;

            for(int i=0;i<n;i++)
            {
                if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
                {
                    min=bt[i];
                    c=i;
                }
            }

            if (c==n)
                st++;
            else
            {
                bt[c]--;
                st++;
                if (bt[c]==0)
                {
                    ct[c]= st;
                    f[c]=1;
                    tot++;
                }
            }
        }

        for(int i=0;i<n;i++)
        {
            ta[i] = ct[i] - at[i];
            wt[i] = ta[i] - k[i];
            averageWaitingTime+= wt[i];
            averageTurnAroundTime+= ta[i];
        }

        averageWaitingTime=(float)(averageWaitingTime/n);
        averageTurnAroundTime=(float)(averageTurnAroundTime/n);


    }

}
