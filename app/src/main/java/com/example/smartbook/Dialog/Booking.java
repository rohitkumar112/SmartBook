package com.example.smartbook.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smartbook.Interfaces.DIalogListener;
import com.example.smartbook.R;

import java.util.Objects;

public class Booking extends Dialog {
    TextView departure,arrival,total_fare,booking_date;
    String selectedDeparture,selectedArrival,selectedTotalFare,selectedDate;
    Button book,cancel;
    DIalogListener dialogListener;
    public Booking(@NonNull Context context,String selectedDeparture,String selectedArrival,String selectedDate,String selectedTotalFare,DIalogListener dialogListener) {
        super(context);
        this.selectedDeparture=selectedDeparture;
        this.selectedArrival=selectedArrival;
        this.selectedTotalFare=selectedTotalFare;
        this.dialogListener=dialogListener;
        this.selectedDate=selectedDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_booking);
        init();
        setValues();
        setListener();
    }

    private void setListener() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogListener!=null)
                {
                    dialogListener.onDismiss();
                dismiss();
                }
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogListener!=null)
                {
                    dialogListener.onDone();
                    dismiss();
                }
            }
        });
    }

    private void setValues() {
        departure.setText(selectedDeparture);
        arrival.setText(selectedArrival);
        total_fare.setText(selectedTotalFare);
        booking_date.setText(selectedDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init() {
        departure=findViewById(R.id.departure);
        booking_date=findViewById(R.id.booking_date);
        arrival=findViewById(R.id.arrival);
        total_fare=findViewById(R.id.total_fare);
        book=findViewById(R.id.book);
        cancel=findViewById(R.id.cancel);
        Rect displayRectangle = new Rect();
        AppCompatActivity activity = (AppCompatActivity) ((ContextWrapper) Objects.requireNonNull(getContext())).getBaseContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        int width = (int) (displayRectangle.width() * 0.90f);
        View view = findViewById(R.id.whole_layout);
        view.setMinimumWidth(width);
    }
}
