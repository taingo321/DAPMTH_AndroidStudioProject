package com.example.testfb4.Products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testfb4.Products.MainActivity;
import com.example.testfb4.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class adddata extends AppCompatActivity {

    EditText add_name, add_price, add_ingre, add_purl;
    Button add_submit, add_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata);

        add_name = (EditText) findViewById(R.id.add_name);
        add_price = (EditText) findViewById(R.id.add_price);
        add_ingre = (EditText) findViewById(R.id.add_ingre);
        add_purl = (EditText) findViewById(R.id.add_purl);

        add_submit = (Button) findViewById(R.id.add_submit);
        add_back = (Button) findViewById(R.id.add_back);

        add_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processinsert();
            }
        });
    }

    private void processinsert() {
        Map<String,Object> map = new HashMap<>();
        map.put("name", add_name.getText().toString());
        map.put("price", add_price.getText().toString());
        map.put("ingre", add_ingre.getText().toString());
        map.put("purl", add_purl.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("products").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        add_name.setText("");
                        add_price.setText("");
                        add_ingre.setText("");
                        add_purl.setText("");
                        Toast.makeText(getApplicationContext(), "Thêm sản phẩm thành công", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Xảy ra lỗi khi thêm sản phẩm", Toast.LENGTH_LONG).show();
                    }
                });
    }
}