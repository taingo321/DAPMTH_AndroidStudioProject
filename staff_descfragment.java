package com.example.testfb4.Staffs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testfb4.R;


public class staff_descfragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    String staff_name, phone, email, address, role, staff_avatar;

    public staff_descfragment() {

    }

    public staff_descfragment(String staff_name, String phone, String email, String address, String role, String staff_avatar) {
        this.staff_name = staff_name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.staff_avatar = staff_avatar;
    }


    public static staff_descfragment newInstance(String param1, String param2) {
        staff_descfragment fragment = new staff_descfragment();
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

        View view = inflater.inflate(R.layout.fragment_staff_descfragment, container, false);

        ImageView staffimageholder = view.findViewById(R.id.staffimageholder);
        TextView staffnameholder = view.findViewById(R.id.staffnameholder);
        TextView phoneholder = view.findViewById(R.id.phoneholder);
        TextView emailholder = view.findViewById(R.id.emailholder);
        TextView addressholder = view.findViewById(R.id.addressholder);
        TextView roleholder = view.findViewById(R.id.roleholder);

        staffnameholder.setText(staff_name);
        phoneholder.setText(phone);
        emailholder.setText(email);
        addressholder.setText(address);
        roleholder.setText(role);
        Glide.with(getContext()).load(staff_avatar).into(staffimageholder);

        return view;
    }
}