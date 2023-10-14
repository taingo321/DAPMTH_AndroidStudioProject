package com.example.testfb4.Staffs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.testfb4.Products.model;
import com.example.testfb4.Products.myadapter;
import com.example.testfb4.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class staff_recfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recviewStaff;
    myadapter staff_adapter;

    public staff_recfragment() {

    }

    public static staff_recfragment newInstance(String param1, String param2) {
        staff_recfragment fragment = new staff_recfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff_recfragment, container, false);

        recviewStaff = (RecyclerView)view.findViewById(R.id.recviewStaff);
        recviewStaff.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("staffs"), model.class)
                        .build();

        staff_adapter = new myadapter(options);
        recviewStaff.setAdapter(staff_adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        staff_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        staff_adapter.stopListening();
    }
    public void processSearchQuery(String searchQuery) {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("staffs")
                                .orderByChild("name").startAt(searchQuery).endAt(searchQuery + "\uf8ff"), model.class)
                        .build();
        staff_adapter.updateOptions(options);
        staff_adapter.notifyDataSetChanged();
    }
}