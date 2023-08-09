package com.example.cse323project;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment{

    private EditText editTextBurst;
    private EditText editTextArrival;
    private EditText editTextPriority;
    private EditText editTextQuantum;

    private ExampleDialogListener listener;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        editTextBurst = view.findViewById(R.id.edit_burst);
        editTextArrival = view.findViewById(R.id.edit_arrival);
        editTextPriority = view.findViewById(R.id.edit_priority);
        editTextQuantum = view.findViewById(R.id.edit_quantum);

        builder.setView(view)
                .setTitle("Add Process")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String burst = editTextBurst.getText().toString();
                        String arrival = editTextArrival.getText().toString();
                        String priority = editTextPriority.getText().toString();
                        String quantum = editTextQuantum.getText().toString();
                        if(!burst.equals("") && !arrival.equals("")) {
                            listener.applyTexts(burst, arrival,priority,quantum);
                        }
                        else{
                            Toast.makeText(((Dialog) dialogInterface).getContext(), "Please fill up all fields!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

        return builder.create();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must implement DialogBox Listener");
        }

    }

    public interface ExampleDialogListener
    {
        void applyTexts(String burst, String arrival, String priority, String quantum);
    }



}

