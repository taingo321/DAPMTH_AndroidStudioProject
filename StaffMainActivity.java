package com.example.testfb4.Staffs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;

import com.example.testfb4.Products.adddata;
import com.example.testfb4.Products.myadapter;
import com.example.testfb4.Products.recfragment;
import com.example.testfb4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StaffMainActivity extends AppCompatActivity {

    RecyclerView recyclerView_Staff;
    myadapter staff_adapter;
    FloatingActionButton fbAddStaff;
    private recfragment staff_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        staff_fragment = new recfragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.staffwrapper, staff_fragment).commit();

        recyclerView_Staff = findViewById(R.id.recyclerView_Staff);
        recyclerView_Staff.setLayoutManager(new LinearLayoutManager(this));

        fbAddStaff = (FloatingActionButton)findViewById(R.id.fbAddStaff);
        fbAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adddata_staff.class));
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchview=(SearchView)item.getActionView();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processearch(String s) {
        if (staff_fragment != null) {
            staff_fragment.processSearchQuery(s); // Gọi phương thức processSearchQuery trên fragment để cập nhật dữ liệu của adapter
        }
    }
}