package com.example.cse323project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    //Variable declarations
    TextView waitText;
    TextView gantt1;
    TextView turnText;
    TextView compText;
    TextView algorithm;
    ArrayList<Process> processList;
    ImageButton backButton;
    int[][] values = new int[100][5];
    float averageWaitingTime;
    float averageTurnaroundTime;
    float averageCompletionTime;
    String ganttChart;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Retrieving ArrayList sent by MainActivity
        processList = new ArrayList<Process>();
        processList = (ArrayList<Process>) getIntent().getSerializableExtra("list");

        for(int i=1;i< processList.size();i++) {
            try {
                if(processList.get(i).getId() != null) {
                    values[i-1][0] = Integer.valueOf(processList.get(i).getId());
                }
                if(processList.get(i).getBurstTime() != null) {
                    values[i-1][1] = Integer.valueOf(processList.get(i).getBurstTime());
                }
                if(processList.get(i).getArrivalTime() != null) {
                    values[i-1][2] = Integer.valueOf(processList.get(i).getArrivalTime());
                }
                if(processList.get(i).getTimeQuantum() != null) {
                    Log.e("TQ",processList.get(i).getTimeQuantum());
                    values[i-1][4] = Integer.valueOf(processList.get(i).getTimeQuantum());
                }
                if(processList.get(i).getPriority() != null) {
                    values[i-1][3] = Integer.valueOf(processList.get(i).getPriority());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        backButton = (ImageButton)findViewById(R.id.imageButton);
        algorithm = (TextView)findViewById(R.id.textWaitingTime2);
        waitText = (TextView)findViewById(R.id.textWaitingTime);
        turnText = (TextView)findViewById(R.id.textView3);
        compText = (TextView)findViewById(R.id.textView8);
        gantt1 = (TextView)findViewById(R.id.textWaitingTime3);

        switch(getIntent().getStringExtra("algo")){

            case "First Come First Serve":
                FCFS();
                break;
            case "Shortest Job First(Preemptive)":
                PSJF();
                break;
            case "Shortest Job First(Non Preemptive)":
                NPSJF();
                break;
            case "Priority(Preemptive)":
                PP();
                break;
            case "Priority(Non Preemptive)":
                PNP();
                break;
            case "Round Robin":
                RR();
                break;
        }

        waitText.setText(String.format("%f",averageWaitingTime));
        turnText.setText(String.format("%f",averageTurnaroundTime));
        compText.setText(String.format("%f",averageCompletionTime));
        gantt1.setText(ganttChart);
        algorithm.setText(getIntent().getStringExtra("algo"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void FCFS(){

        FCFS obj = new FCFS();
        obj.setValues(values);
        obj.setN(processList.size()-1);
        obj.getProcess();
        obj.FirstComeFirstServeAlgorithm();
        averageWaitingTime = obj.getAverageWaitingTime();
        averageTurnaroundTime = obj.getAverageTurnAroundTime();
        obj.ganttChart();
        ganttChart = obj.getGanttChart();


    }


    public void NPSJF(){

        NPSJF obj = new NPSJF();
        obj.setValues(values);
        obj.setN(processList.size()-1);
        obj.getProcess();
        obj.NonPreemptiveSJF();
        averageWaitingTime = obj.getAverageWaitingTime();
        averageTurnaroundTime = obj.getAverageTurnAroundTime();
        obj.ganttChart();
        ganttChart = obj.getGanttChart();

    }

    public void PP(){

    }


    public void PSJF(){

        PSJF obj = new PSJF();
        obj.setValues(values);
        obj.setN(processList.size()-1);
        obj.getProcess();
        obj.PreemptiveShortestJobFirst();
        averageWaitingTime = obj.getAverageWaitingTime();
        averageTurnaroundTime = obj.getAverageTurnAroundTime();

    }

    public void PNP(){

        NPPriority obj = new NPPriority();
        obj.setValues(values);
        obj.setN(processList.size()-1);
        obj.getProcessData();
        obj.priorityNonPreemptiveAlgorithm();
        averageWaitingTime = obj.getAverageWaitingTime();
        averageTurnaroundTime = obj.getAverageTurnAroundTime();
        obj.ganttChart();
        ganttChart = obj.getGanttChart();

    }

    public void RR(){

        RoundRobin obj = new RoundRobin();
        obj.RoundRobinAlgorithm(values);
        averageWaitingTime = obj.getAverageWaitingTime();
        averageTurnaroundTime = obj.getAverageTurnAroundTime();

    }




//    public void PSJ(){
//
//        int n = processList.size() - 1;
//        int pid[] = new int[n]; // it takes pid of process
//        int at[] = new int[n]; // at means arrival time
//        int bt[] = new int[n]; // bt means burst time
//        int ct[] = new int[n]; // ct means complete time
//        int ta[] = new int[n];// ta means turn around time
//        int wt[] = new int[n];  // wt means waiting time
//        int f[] = new int[n];  // f means it is flag it checks process is completed or not
//        int k[]= new int[n];   // it is also stores brust time
//        int i, st=0, tot=0;
//        float avgwt=0, avgta=0;
//
//        for (i=0;i<n;i++)
//        {
//            pid[i] = values[i][0];
//            bt[i] = values[i][1];
//            at[i] = values[i][2];
//
//            k[i]= bt[i];
//            f[i]= 0;
//        }
//
//        while(true){
//            int min=99,c=n;
//            if (tot==n)
//                break;
//
//            for( i=0;i<n;i++)
//            {
//                if ((at[i]<=st) && (f[i]==0) && (bt[i]<min))
//                {
//                    min=bt[i];
//                    c=i;
//                }
//            }
//
//            if (c==n)
//                st++;
//            else
//            {
//                bt[c]--;
//                st++;
//                if (bt[c]==0)
//                {
//                    ct[c]= st;
//                    f[c]=1;
//                    tot++;
//                }
//            }
//        }
//
//        for(i=0;i<n;i++)
//        {
//            ta[i] = ct[i] - at[i];
//            wt[i] = ta[i] - k[i];
//            avgwt+= wt[i];
//            avgta+= ta[i];
//        }
//
//
//        avgta=(float)(avgta/n);
//        avgwt=(float)(avgwt/n);
//
//
//    }

//    public void npSJFAlgorithm(){
//
//        int n = processList.size();
//        int pid[] = new int[n];
//        int at[] = new int[n]; // at means arrival time
//        int bt[] = new int[n]; // bt means burst time
//        int ct[] = new int[n]; // ct means complete time
//        int ta[] = new int[n]; // ta means turn around time
//        int wt[] = new int[n];  //wt means waiting time
//        int f[] = new int[n];  // f means it is flag it checks process is completed or not
//        int st=0, tot=0;
//        float avgwt=0, avgta=0;
//
//        for(int i=0;i<n;i++)
//        {
//            pid[i] =values[i][0];
//            bt[i] = values[i][1];
//            at[i] = values[i][2];
//            f[i] = 0;
//        }
//
//        boolean a = true;
//        while(true)
//        {
//            int c=n, min=999;
//            if (tot == n) // total no of process = completed process loop will be terminated
//                break;
//            for (int i=0; i<n; i++)
//            {
//                /*
//                 * If i'th process arrival time <= system time and its flag=0 and burst<min
//                 * That process will be executed first
//                 */
//                if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
//                {
//                    min=bt[i];
//                    c=i;
//                }
//            }
//            /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
//            if (c==n)
//                st++;
//            else
//            {
//                ct[c]=st+bt[c];
//                st+=bt[c];
//                ta[c]=ct[c]-at[c];
//                wt[c]=ta[c]-bt[c];
//                f[c]=1;
//                tot++;
//            }
//        }
//        for(int i=0;i<n;i++)
//        {
//            avgwaittime+= wt[i];
//            avgta+= ta[i];
//        }
//
//        avgwaittime /= n;
//    }
//

//    public void fcfsAlgorithm(){
//
//        int n = processList.size();
//
//        int pid[] = new int[n];   // process ids
//        int ar[] = new int[n];    // arrival times
//        int bt[] = new int[n];    // burst or execution times
//        int ct[] = new int[n];    // completion times
//        int ta[] = new int[n];    // turn around times
//        int wt[] = new int[n];    // waiting times
//
//        int temp;
//
//        for(int i = 0; i < n; i++)
//        {
//            pid[i] = values[i][0];
//            bt[i] = values[i][1];
//            ar[i] = values[i][2];
//        }
//
////sorting according to arrival times
//        for(int i = 0 ; i <n; i++)
//        {
//            for(int  j=0;  j < n-(i+1) ; j++)
//            {
//                if( ar[j] > ar[j+1] )
//                {
//                    temp = ar[j];
//                    ar[j] = ar[j+1];
//                    ar[j+1] = temp;
//                    temp = bt[j];
//                    bt[j] = bt[j+1];
//                    bt[j+1] = temp;
//                    temp = pid[j];
//                    pid[j] = pid[j+1];
//                    pid[j+1] = temp;
//                }
//            }
//        }
//// finding completion times
//        for(int  i = 0 ; i < n; i++)
//        {
//            if( i == 0)
//            {
//                ct[i] = ar[i] + bt[i];
//            }
//            else
//            {
//                if( ar[i] > ct[i-1])
//                {
//                    ct[i] = ar[i] + bt[i];
//                }
//                else
//                    ct[i] = ct[i-1] + bt[i];
//            }
//            ta[i] = ct[i] - ar[i] ;          // turnaround time= completion time- arrival time
//            wt[i] = ta[i] - bt[i] ;          // waiting time= turnaround time- burst time
//            avgwaittime += wt[i] ;               // total waiting time
//            avgturnaroundtime += ta[i] ;               // total turnaround time
//        }
//
//    }
    }