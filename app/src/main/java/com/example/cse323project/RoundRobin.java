package com.example.cse323project;

import android.util.Log;

// Java program to implement Round Robin
// Scheduling with different arrival time
class RoundRobin {


    float averageTurnAroundTime;
    float averageWaitingTime;

    float getAverageTurnAroundTime() {
        return averageTurnAroundTime;
    }

    float getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public void RoundRobinAlgorithm(int[][] values)
    {
        int m = 3;

        int[] a = new int[m];
        int[] b = new int[m];
        //String[] p = new String[m];
        int[] p = new int[m];
        Log.e("Time Quantum", String.valueOf(values[0][4]));
        int n = 4;

        for(int i=0;i<m;i++) {
            a[i] = values[i][2];
            b[i] = values[i][1];
            //p[i] = "p" + values[i][0];
            p[i] = values[i][0];
        }

// result of average times
        int res = 0;
        int resc = 0;

// for sequence storage
        String seq = new String();

// copy the burst array and arrival array
// for not effecting the actual array
        int res_b[] = new int[b.length];
        int res_a[] = new int[a.length];

        for (int i = 0; i < res_b.length; i++) {
            res_b[i] = b[i];
            res_a[i] = a[i];
        }

// critical time of system
        int t = 0;

// for store the waiting time
        int w[] = new int[p.length];

// for store the Completion time
        int comp[] = new int[p.length];

        while (true) {
            boolean flag = true;
            for (int i = 0; i < p.length; i++) {

// these condition for if
// arrival is not on zero

// check that if there come before qtime
                if (res_a[i] <= t) {
                    if (res_a[i] <= n) {
                        if (res_b[i] > 0) {
                            flag = false;
                            if (res_b[i] > n) {

                                // make decrease the b time
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                seq += "->" + p[i];
                            }
                            else {

                                // for last time
                                t = t + res_b[i];

                                // store comp time
                                comp[i] = t - a[i];

                                // store wait time
                                w[i] = t - b[i] - a[i];
                                res_b[i] = 0;

                                // add sequence
                                seq += "->" + p[i];
                            }
                        }
                    }
                    else if (res_a[i] > n) {

                        // is any have less arrival time
                        // the coming process then execute them
                        for (int j = 0; j < p.length; j++) {

                            // compare
                            if (res_a[j] < res_a[i]) {
                                if (res_b[j] > 0) {
                                    flag = false;
                                    if (res_b[j] > n) {
                                        t = t + n;
                                        res_b[j] = res_b[j] - n;
                                        res_a[j] = res_a[j] + n;
                                        seq += "->" + p[j];
                                    }
                                    else {
                                        t = t + res_b[j];
                                        comp[j] = t - a[j];
                                        w[j] = t - b[j] - a[j];
                                        res_b[j] = 0;
                                        seq += "->" + p[j];
                                    }
                                }
                            }
                        }

                        // now the previous porcess according to
                        // ith is process
                        if (res_b[i] > 0) {
                            flag = false;

                            // Check for greaters
                            if (res_b[i] > n) {
                                t = t + n;
                                res_b[i] = res_b[i] - n;
                                res_a[i] = res_a[i] + n;
                                seq += "->" + p[i];
                            }
                            else {
                                t = t + res_b[i];
                                comp[i] = t - a[i];
                                w[i] = t - b[i] - a[i];
                                res_b[i] = 0;
                                seq += "->" + p[i];
                            }
                        }
                    }
                }

// if no process is come on thse critical
                else if (res_a[i] > t) {
                    t++;
                    i--;
                }
            }
// for exit the while loop
            if (flag) {
                break;
            }
        }

        for (int i = 0; i < p.length; i++) {
            res = res + w[i];
            resc = resc + comp[i];
        }

        averageWaitingTime = (float)res/m;
        averageTurnAroundTime = (float)resc/m;
    }

}
