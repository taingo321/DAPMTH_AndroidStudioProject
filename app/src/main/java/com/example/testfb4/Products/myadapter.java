package com.example.testfb4.Products;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testfb4.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myviewholder holder,final int position, @NonNull final model model){
        String limitedName = limitTitleLength(model.getName(), 20);
        holder.nameText.setText(limitedName);
        holder.priceText.setText(model.getPrice());
        holder.ingreText.setText(model.getIngre());
        Glide.with(holder.img1.getContext()).load(model.getPurl()).into(holder.img1);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        activity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.wrapper, new descfragment(model.getName(), model.getPrice(), model.getIngre(), model.getPurl()))
                                .addToBackStack(null).commit();
                    }
                });
                holder.edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img1.getContext())
                                .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                                .setExpanded(true,1100)
                                .create();
                        View myview = dialogPlus.getHolderView();
                        EditText purl = myview.findViewById(R.id.uimgurl);
                        EditText name = myview.findViewById(R.id.uname);
                        EditText price = myview.findViewById(R.id.uprice);
                        EditText ingre = myview.findViewById(R.id.uingre);

                        Button submit = myview.findViewById(R.id.usubmit);

                        purl.setText(model.getPurl());
                        name.setText(model.getName());
                        price.setText(model.getPrice());
                        ingre.setText(model.getIngre());

                        dialogPlus.show();

                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Map<String,Object> map = new HashMap<>();
                                map.put("purl",purl.getText().toString());
                                map.put("name",name.getText().toString());
                                map.put("ingre",ingre.getText().toString());
                                map.put("price",price.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("products")
                                        .child(getRef(position).getKey()).updateChildren(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialogPlus.dismiss();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                dialogPlus.dismiss();
                                            }
                                        });
                            }
                        });

                    }
                });
                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.img1.getContext());
                        builder.setTitle("Xóa sản phẩm");
                        builder.setMessage("Bạn có muốn xóa sản phẩm");

                        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                FirebaseDatabase.getInstance().getReference().child("products")
                                        .child(getRef(position).getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        });

                        builder.show();

                    }
                });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img1;
        ImageView edit,delete;
        TextView nameText,priceText,ingreText;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            img1=(ImageView)itemView.findViewById(R.id.img1);
            nameText=(TextView)itemView.findViewById(R.id.nameText);
            priceText=(TextView)itemView.findViewById(R.id.priceText);
            ingreText=(TextView)itemView.findViewById(R.id.ingreText);

            edit=(ImageView)itemView.findViewById(R.id.edit);
            delete=(ImageView)itemView.findViewById(R.id.delete);
        }
    }

    public String limitTitleLength(String fullTitle, int maxLength) {
        if (fullTitle == null) {
            return "";
        }

        if (fullTitle.length() <= maxLength) {
            return fullTitle;
        } else {
            return fullTitle.substring(0, maxLength - 3) + "...";
        }
    }

    /*
    public void updateData(List<model> newDataList) {
        // Xóa dữ liệu cũ trong Adapter
        this.getSnapshots().clear();

        // Thêm dữ liệu mới vào Adapter
        this.getSnapshots().addAll(newDataList);

        // Thông báo rằng dữ liệu đã thay đổi và cần cập nhật giao diện
        notifyDataSetChanged();
    }

    public void filterData(String searchText) {
        Query searchQuery = FirebaseDatabase.getInstance().getReference().child("students")
                .orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(searchQuery, model.class)
                        .build();

        this.updateOptions(options);
    }*/
}
