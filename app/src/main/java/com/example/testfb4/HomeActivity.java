package com.example.testfb4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.testfb4.Products.MainActivity;

public class HomeActivity extends AppCompatActivity {

    ImageView ivProduct;
    ImageView ivStaff;
    ImageView ivStorage;
    ImageView ivIncome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ivProduct = findViewById(R.id.ivProduct);
        ivStaff = findViewById(R.id.ivStaff);
        ivStorage = findViewById(R.id.ivStorage);
        ivIncome = findViewById(R.id.ivIncome);
        ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        /*
        ivStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TeaMainActivity.class);
                startActivity(intent);
            }
        });
        ivStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, StorageMainActivity.class);
                startActivity(intent);
            }
        });
        ivIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, IncomeMainActivity.class);
                startActivity(intent);
            }
        });
    } */
    }
}