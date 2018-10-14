package com.doublea.binyutton;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doublea.binyutton.backgroundTools.Request;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_main extends AppCompatActivity {

    EditText id;
    EditText message;
    Button help;
    TextView outputToStudent;
    Request currentRequest;
    Button administrator;

    private DatabaseReference administratorDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables
        id = findViewById(R.id.et_net_id);
        message = findViewById(R.id.et_message);
        help = findViewById(R.id.btn_help);
        outputToStudent = findViewById(R.id.tv_output_messages_to_student);
        administrator = findViewById(R.id.btn_adminstrator);
        administratorDatabaseRef = FirebaseDatabase.getInstance().getReference("Requests");
    }

    public void run_admin_log_in(View view){
        startActivity(new Intent(this,activity_sign_in.class));
    }

    /**
     * @param view
     */
    public void help_button_clicked(View view) {
        setEntryState(false);
        currentRequest = new Request(id.getText().toString(), message.getText().toString());
        if (currentRequest.getId().equals("")){
            clearTextBoxes();
            Toast.makeText(activity_main.this, "USER ID CAN NOT BE EMPTY",
                    Toast.LENGTH_SHORT).show();
            //Toast
            return;
        }

        administratorDatabaseRef = FirebaseDatabase.getInstance().getReference("Requests/" + currentRequest.getId());
        if (administratorDatabaseRef == null){
            setEntryState(true);
            clearTextBoxes();
            return;
        }
        administratorDatabaseRef.setValue(currentRequest);
        administratorDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("stage").getValue() == null){
                    setEntryState(true);
                    clearTextBoxes();
                    return;
                }
                currentRequest.setStage(Integer.parseInt(dataSnapshot.child("stage").getValue().toString()));
                updateRequestStatus(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(activity_main.this, databaseError.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clearTextBoxes() {
        id.setText("");
        message.setText("");
    }

    private void updateRequestStatus(DataSnapshot dataSnapshot) {
        switch (currentRequest.getStage()) {
            case 0:
                outputToStudent.setText("Request is being processesed");
                break;
            case 1:
                outputToStudent.setText("Help is coming");
                break;
            case 2:
                outputToStudent.setText("Help has arrived");
                break;
        }
    }

    private void setEntryState(boolean b) {
        id.setEnabled(b);
        message.setEnabled(b);
        help.setEnabled(b);
        administrator.setEnabled(b);
    }
}
