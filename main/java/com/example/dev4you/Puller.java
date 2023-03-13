package com.example.dev4you;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Puller extends AppCompatActivity {
    ListView myListView;

    ArrayList<String> myArrayList;
    List<DataHolder> dataholderArrayList;
    ArrayAdapter<String> arrayAdapter;
    DataHolder dataHolder;

    ProgressBar progressBarpuller;

    TextView puller;
    DatabaseReference mref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puller);

        dataHolder = new DataHolder();

        puller = findViewById(R.id.puller);
        myListView = findViewById(R.id.listviewpuller);
        progressBarpuller = findViewById(R.id.progressBarpuller);

        myArrayList = new ArrayList<>();
        dataholderArrayList = new ArrayList<>();

        mref = FirebaseDatabase.getInstance().getReference().child("Our Data");

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
//                        String data = ds.getValue().toString();
                        dataHolder = ds.getValue(DataHolder.class);
                        String data = "Name: " + dataHolder.getName() + ".\n"
                                + "Contact Number: " + dataHolder.getNumber() + ".\n"
                                + "PNR Number: " + dataHolder.getPnr_no() + ".\n"
                                + "Ticket Number: " + dataHolder.getTic_no() + ".\n"
                                + "Luggage Weight: " + dataHolder.getLuggage_weight() + ".\n";
                        myArrayList.add(data);
                        progressBarpuller.setVisibility(View.GONE);
                    }

                    arrayAdapter = new ArrayAdapter<>(Puller.this, android.R.layout.simple_list_item_1,myArrayList);
                    myListView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(Puller.this, "Data not exists", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}