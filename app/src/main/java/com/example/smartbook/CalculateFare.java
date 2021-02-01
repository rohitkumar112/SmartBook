package com.example.smartbook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbook.Activities.MapsActivity;
import com.example.smartbook.Database.DBHELPER;
import com.example.smartbook.Dialog.Booking;
import com.example.smartbook.Interfaces.DIalogListener;
import com.example.smartbook.Utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class CalculateFare extends AppCompatActivity {
    Button submit,calculateFare;
    Spinner medium,departure,arrival;
    String selectedMedium="",selectedDeparture="",selectedArrival="",selectedDate="";
    List<String> medium_list,departure_list,arrival_list;
    List<String> temp_list,medium_temp_list;
    DBHELPER db;
    EditText select_date;
    Button logout;
    TextView name,phone_number;
    TextView departure_error,arrival_error,medium_error,date_error;
    ArrayAdapter<String> medium_adapter,departure_adapter,arrival_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_fare);
        select_date=findViewById(R.id.select_date);
        departure_error=findViewById(R.id.departure_error);
        arrival_error=findViewById(R.id.arrival_error);
        medium_error=findViewById(R.id.medium_error);
        date_error=findViewById(R.id.date_error);
        medium_list=new ArrayList<>();
        departure_list=new ArrayList<>();
        arrival_list=new ArrayList<>();
        temp_list=new ArrayList<>();
        calculateFare=findViewById(R.id.calculate_fare);
        medium_temp_list=new ArrayList<>();
        db=new DBHELPER(this,"ss");
        departure_list.add(0,"please select");
        arrival_list.add(0,"please select");
        medium_list.add(0,"please select");
        temp_list=db.getAllCities();
        medium_temp_list=db.getAllMedium();
        for(int i=0;i<temp_list.size();i++)
        {
            arrival_list.add(temp_list.get(i));
            departure_list.add(temp_list.get(i));
        }
        for(int i=0;i<medium_temp_list.size();i++)
        {
            medium_list.add(medium_temp_list.get(i));
        }
        medium_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medium_list);
        departure_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departure_list);
        arrival_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrival_list);
        departure=findViewById(R.id.departure);
        arrival=findViewById(R.id.arrival);
        medium=findViewById(R.id.median);
        medium.setAdapter(medium_adapter);
        arrival.setAdapter(arrival_adapter);
        departure.setAdapter(departure_adapter);
        onClickListener();
    }
    private void onClickListener() {
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(CalculateFare.this, CancelTicket.class);
//
//                startActivity(intent);
//            }
//        });
        medium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    selectedMedium="";
                }
                else
                {
                    selectedMedium= (String) medium.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        departure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    selectedDeparture="";
                }
                else
                {
                    selectedDeparture=(String)departure.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        arrival.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    selectedArrival="";
                }
                else
                {
                    selectedArrival=(String)arrival.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        calculateFare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()&&!selectedDeparture.equals(selectedArrival))
                {

                    final String fare=db.getFare(selectedDeparture,selectedArrival,selectedMedium);
                    Booking booking_dialog=new Booking(CalculateFare.this, selectedDeparture, selectedArrival, selectedDate,fare, new DIalogListener() {
                        @Override
                        public void onDismiss() {
                        }

                        @Override
                        public void onDone() {
                            db.saveBooking(selectedDeparture, selectedArrival, selectedDate,fare);
                            Toast.makeText(CalculateFare.this,"Successfully Booked",Toast.LENGTH_SHORT).show();
                        }
                    });
                    booking_dialog.show();
                }
                else
                {
                    Toast.makeText(CalculateFare.this,"departure should be different from arrival",Toast.LENGTH_LONG).show();
                }
            }
        });
        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year=2020;
                final int month=1;
                final int date=5;
                final DatePickerDialog mdiDialog =new DatePickerDialog(CalculateFare.this,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear=monthOfYear+1;
                        selectedDate=year+"-"+monthOfYear+"-"+dayOfMonth;
                        select_date.setText(selectedDate);

                    }


                }, year, month, date);
                mdiDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                mdiDialog.show();
            }
        });
    }
    private Boolean isValid() {
        Boolean istrue=true;
        if(!isArrivalSelected())
        {
            istrue=false;
        }
        if(!departureSelected())
        {
            istrue=false;
        }
        if(!mediumSelected())
        {
            istrue=false;
        }
        if(!isDateSelected())
        {
            istrue=false;
        }
        return istrue;
    }

    private boolean isDateSelected() {
        if(selectedDate.equals(""))
        {
            Utility.seterror(date_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(date_error,View.GONE);
            return true;
        }
    }

    private boolean mediumSelected() {
        if(selectedMedium.equals(""))
        {
            Utility.seterror(medium_error,View.VISIBLE);
            return false;
        }
        else
        {        Utility.seterror(medium_error,View.GONE);
            return true;
        }
    }

    private boolean departureSelected() {
        if(selectedDeparture.equals(""))
        {
            Utility.seterror(departure_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(departure_error,View.GONE);
            return true;
        }
    }

    private boolean isArrivalSelected() {
        if(selectedArrival.equals(""))
        {
            Utility.seterror(arrival_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(arrival_error,View.GONE);
            return true;
        }
    }
}