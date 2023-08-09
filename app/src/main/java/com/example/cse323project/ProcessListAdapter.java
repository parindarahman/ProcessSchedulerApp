package com.example.cse323project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProcessListAdapter extends ArrayAdapter<Process> {

    private static final String TAG = "ProcessListAdapter";

    private Context mContext;
    int mResource;

    public ProcessListAdapter(Context context, int resource, ArrayList<Process> objects) {
        super(context, resource,objects);
        this.mContext = context;
        this.mResource = resource;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String id = getItem(position).getId();
        String burstTime = getItem(position).getBurstTime();
        String arrivalTime = getItem(position).getArrivalTime();
        String priority = getItem(position).getPriority();
        String timeQuantum = getItem(position).getTimeQuantum();

        Process process = new Process(id,burstTime,arrivalTime,priority,timeQuantum);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource,parent,false);


        TextView idView = (TextView)convertView.findViewById(R.id.textView1);
        TextView burstView = (TextView)convertView.findViewById(R.id.textView2);
        TextView arrivalView = (TextView)convertView.findViewById(R.id.textView3);
        TextView priorityView = (TextView)convertView.findViewById(R.id.textView4);
        TextView quantumView = (TextView)convertView.findViewById(R.id.textView5);

        idView.setText(id);
        burstView.setText(burstTime);
        arrivalView.setText(arrivalTime);
        priorityView.setText(priority);
        quantumView.setText(timeQuantum);

        return convertView;

    }
}
