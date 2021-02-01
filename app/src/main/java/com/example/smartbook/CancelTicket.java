package com.example.smartbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smartbook.Adapter.CancelAdapter;
import com.example.smartbook.Database.DBHELPER;
import com.example.smartbook.Model.BookingModel;

import java.util.ArrayList;
import java.util.List;

public class CancelTicket extends AppCompatActivity {
    RecyclerView recycler_view;
    CancelAdapter cancelAdapter;
    List<BookingModel> bookingModelList;
    DBHELPER db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_ticket);
        bookingModelList=new ArrayList<>();
        db=new DBHELPER(this,"cancel");
        bookingModelList=db.getAllBookings();
        recycler_view=findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        cancelAdapter = new CancelAdapter(this,bookingModelList,db);
        recycler_view.setAdapter(cancelAdapter);
    }
}