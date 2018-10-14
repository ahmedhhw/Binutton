package com.doublea.binyutton;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doublea.binyutton.backgroundTools.Request;
import com.doublea.binyutton.backgroundTools.completionListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_request.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragment_request#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_request extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "ID";
    private static final String ARG_MESSAGE = "MESSAGE";
    private TextView acceptHyperText;
    private TextView netIDTextView;
    private static final String TAG = "dbg";
    private TextView messageTextView;

    // TODO: Rename and change types of parameters
    private String currentID;
    private String currentMessage;
    private OnFragmentInteractionListener mListener;

    public fragment_request() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment fragment_request.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_request newInstance(Request request) {
        fragment_request fragment = new fragment_request();
        Bundle args = new Bundle();
        args.putString(ARG_ID, request.getId());
        args.putString(ARG_MESSAGE, request.getMessage());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentID = getArguments().getString(ARG_ID);
            currentMessage = getArguments().getString(ARG_MESSAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        acceptHyperText = (TextView) view.findViewById(R.id.tv_accept);
        netIDTextView =  (TextView) view.findViewById(R.id.tv_net_id_fragment);
        messageTextView = (TextView) view.findViewById(R.id.tv_message);
//        Log.d(TAG, "Hypertext: " + acceptHyperText.getText().toString());
//        Log.d(TAG, "ID: " + netIDTextView.getText().toString());
//        Log.d(TAG, "Message: " + messageTextView.getText().toString());
//        Log.d(TAG, "currentID: " + currentID);
//        Log.d(TAG, "currentMessage: " + currentMessage);
        netIDTextView.setText(currentID);
        messageTextView.setText(currentMessage);
        acceptHyperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), activity_arrival.class);
                in.putExtra("id",currentID);
                startActivity(in);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
