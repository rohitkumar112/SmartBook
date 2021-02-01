package com.example.smartbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.smartbook.Adapter.CancelAdapter;
import com.example.smartbook.Database.DBHELPER;
import com.example.smartbook.Model.BookingModel;

import java.util.ArrayList;
import java.util.List;

public class cancelTicketHistory extends AppCompatActivity {
    RecyclerView recycler_view;
    CancelAdapter cancelAdapter;
    List<BookingModel> bookingModelList;
    String cameFrom;
    TextView cancel_ticket;
    DBHELPER db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_ticket_history);
        cancel_ticket=findViewById(R.id.cancel_ticket);
        cancel_ticket.setText("Cancel Ticket History");
        bookingModelList=new ArrayList<>();
        db=new DBHELPER(this,"cancel");
        bookingModelList=db.getAllCancelTickets();
        recycler_view=findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        cameFrom="cancelhistory";
        cancelAdapter = new CancelAdapter(this,bookingModelList,db,cameFrom);
        recycler_view.setAdapter(cancelAdapter);
    }
}