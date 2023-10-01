package com.example.testfb4.Products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testfb4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    myadapter adapter;
    FloatingActionButton fbAdd;
    private recfragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragment = new recfragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, fragment).commit();


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), model.class)
                        .build();
        adapter = new myadapter(options);*/
        fbAdd = (FloatingActionButton)findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), adddata.class));
            }
        });

        /*
        adapter.setOnItemClickListener(new myadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (isSearching) {
                    // Nếu đang trong chế độ tìm kiếm, lấy dữ liệu từ newDataList
                    model selectedModel = newDataList.get(position);
                    // Hiển thị thông tin cá nhân của đối tượng đó
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("name", selectedModel.getName());
                    intent.putExtra("course", selectedModel.getCourse());
                    intent.putExtra("email", selectedModel.getEmail());
                    intent.putExtra("purl", selectedModel.getPurl());
                    startActivity(intent);
                } else {
                    // Nếu không trong chế độ tìm kiếm, lấy dữ liệu từ adapter
                    model selectedModel = adapter.getItem(position);
                    // Hiển thị thông tin cá nhân của đối tượng đó
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("name", selectedModel.getName());
                    intent.putExtra("course", selectedModel.getCourse());
                    intent.putExtra("email", selectedModel.getEmail());
                    intent.putExtra("purl", selectedModel.getPurl());
                    startActivity(intent);
                }
            }
        });



        adapter.updateData(newDataList); */


    }
    /*
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }*/


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
        if (fragment != null) {
            fragment.processSearchQuery(s); // Gọi phương thức processSearchQuery trên fragment để cập nhật dữ liệu của adapter
        }
    }
    /*
    @Override
    protected void onResume() {
        super.onResume();
        // Nếu không đang trong tìm kiếm, cập nhật dữ liệu mới
        if (!isSearching) {
            // Tạo một danh sách mới chứa dữ liệu cần cập nhật (dựa vào nguồn dữ liệu mới)
            List<model> newDataList = new ArrayList<>();
            // Thêm dữ liệu mới vào danh sách
            // ...

            // Sau khi hoàn thành việc thêm dữ liệu mới vào newDataList, gọi phương thức updateData() của Adapter để cập nhật RecyclerView
            adapter.updateData(newDataList);
        }
    } */
}