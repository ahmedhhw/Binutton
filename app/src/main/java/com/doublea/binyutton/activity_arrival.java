package com.doublea.binyutton;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_arrival extends AppCompatActivity {

    private String id;
    private DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival);
        id = getIntent().getStringExtra("id");
        studentRef = FirebaseDatabase.getInstance().getReference("Requests/" + id + "/stage");
        studentRef.setValue("1");
    }

    public void promptArrival(View view) {
        studentRef.setValue("2");
        studentRef = FirebaseDatabase.getInstance().getReference("Requests/" + id);
        studentRef.removeValue();
        startActivity(new Intent(this, activity_requests_view.class));
    }
}
