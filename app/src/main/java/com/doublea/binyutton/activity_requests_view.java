package com.doublea.binyutton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.doublea.binyutton.backgroundTools.Request;
import com.doublea.binyutton.backgroundTools.completionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_requests_view extends AppCompatActivity implements fragment_request.OnFragmentInteractionListener {
    private FirebaseAuth Auth;
    private LinearLayout requestContainer;
    private DatabaseReference requestsRefrence;
    private static final String TAG = "dbg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests_view);
        Auth = FirebaseAuth.getInstance();
        requestContainer = (LinearLayout) findViewById(R.id.ll_request_List);

        //Magic happens
        requestsRefrence = FirebaseDatabase.getInstance().getReference("Requests");

        requestsRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                inflateFragments(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void inflateFragments(DataSnapshot dataSnapshot) {
        //Background set up
        FragmentManager fragmentManager = getSupportFragmentManager(); //Set up fragment manage
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction(); //Set up new transaction


        for (DataSnapshot aChild: dataSnapshot.getChildren()){
            fragmentTransaction.add(R.id.ll_request_List,fragment_request.newInstance(new Request(aChild.child("id").getValue().toString()
                    , aChild.child("message").getValue().toString(),
                    Integer.parseInt(aChild.child("stage").getValue().toString()))));
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void sign_out_clicked(View view) {
        Auth.signOut();
        startActivity(new Intent(this,activity_sign_in.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
