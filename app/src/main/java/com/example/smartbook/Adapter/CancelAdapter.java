package com.example.smartbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbook.Database.DBHELPER;
import com.example.smartbook.Dialog.cancelTicketDialog;
import com.example.smartbook.Interfaces.DIalogListener;
import com.example.smartbook.Model.BookingModel;
import com.example.smartbook.R;

import java.util.List;

public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.viewholder>
{
    Context context;
    List<BookingModel> bookingModelList;
    DBHELPER db;
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.dialog_booking,parent,false);

        return new viewholder(view);
    }
    public CancelAdapter(Context context, List<BookingModel> bookingModelList,DBHELPER db)
    {
        this.context=context;
        this.bookingModelList=bookingModelList;
        this.db=db;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, final int position) {
        final String departure=bookingModelList.get(position).getDeparture();
        final String arrival=bookingModelList.get(position).getArrival();
        final String dateOfBooking=bookingModelList.get(position).getDateOfBooking();
        final String totalFare=bookingModelList.get(position).getPrice();
        holder.departure.setText(bookingModelList.get(position).getDeparture());
        holder.arrival.setText(bookingModelList.get(position).getArrival());
        holder.booking_date.setText(bookingModelList.get(position).getDateOfBooking());
        holder.total_fare.setText(bookingModelList.get(position).getPrice());
        holder.book.setVisibility(View.GONE);
        holder.cancel.setVisibility(View.VISIBLE);
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelTicketDialog canceldialog=new cancelTicketDialog(context, new DIalogListener() {
                    @Override
                    public void onDismiss() {
                        db.deleteBooking(departure,arrival,dateOfBooking,totalFare);
                    }

                    @Override
                    public void onDone() {

                    }
                });
                db.deleteBooking(departure,arrival,dateOfBooking,totalFare);
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return bookingModelList.size();
    }
    public static class viewholder extends RecyclerView.ViewHolder {
        TextView departure,arrival,total_fare,booking_date;
        LinearLayout whole_layout;
        Button book,cancel;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            whole_layout=itemView.findViewById(R.id.whole_layout);
            departure=itemView.findViewById(R.id.departure);
            arrival=itemView.findViewById(R.id.arrival);
            total_fare=itemView.findViewById(R.id.total_fare);
            booking_date=itemView.findViewById(R.id.booking_date);
            book=itemView.findViewById(R.id.book);
            cancel=itemView.findViewById(R.id.cancel);
        }
    }
//        extends RecyclerView.Adapter<CancelAdapter.> {
//    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
//        View view=layoutInflater.inflate(R.layout.student_info,parent,false);
//
//        return new viewholder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CancelAdapter.viewholder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
}
