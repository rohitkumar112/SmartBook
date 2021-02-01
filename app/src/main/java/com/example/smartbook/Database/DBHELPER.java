package com.example.smartbook.Database;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.smartbook.Model.BookingModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.smartbook.Activities.LoginActivity.MyPREFERENCES;
import static com.example.smartbook.Activities.LoginActivity.sharedPreferences;

public class DBHELPER extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "smartBook.db";
    public static final String USER_PROIFLE = "user_profile";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    private static DBHELPER dbHelper;
    public static final String CNIC_NUMBER = "cnic_number";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";
    public static final String CONTACTS_COLUMN_PHONE = "phone_number";
    public Context context;
//    public static SQLiteDatabase db = BaseApplication.getInstance().getDbHelper().getReadableDatabase();//mDbHelper.getReadableDatabase();


    public DBHELPER(@Nullable Context context) {
        super(context, DATABASE_NAME,null,1);
    }
    public DBHELPER(Context context,String name)
    {
        this(context);


    }

//    public static DBHELPER getInstance(Context context) {
//        if (dbHelper == null) {
//            dbHelper = new DBHELPER(context);
//        }
//        if (_context == null) {
//            _context = context;
//        }
//        return dbHelper;
//    }

    public void onUpgrade(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_PROIFLE);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table cities" +
                "(city_id integer primary key AUTOINCREMENT, city_name text)");
        sqLiteDatabase.execSQL(
                "Create Table user_profile " +
                        "(id integer primary key AUTOINCREMENT, name text,phone text,email text, cnic_number text,address text,password text)"
        );

        sqLiteDatabase.execSQL("Create Table medium" +
                "(medium_id integer primary key AUTOINCREMENT, medium_type text)");
//        sqLiteDatabase.execSQL("create table bookings" +
//                "(booking_id integer primary key AUTOINCREMENT, medium_id text,city_id text,price text)");
        sqLiteDatabase.execSQL("Create Table bookings" +
                "(booking_id integer primary key AUTOINCREMENT, medium_id integer,departure text,arrival text,price text,booking_date text,is_deleted integer,phone_number text)");
        sqLiteDatabase.execSQL("Create Table city_mapping" +
                "(city_mapping_id integer primary key AUTOINCREMENT, departure text,arrival text,total_fare text,medium_id int)");
        setPreFilledValues(sqLiteDatabase);
    }

    private void setPreFilledValues(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("insert into cities(city_name) values('Karachi'),('Lahore'),('Islamabad')") ;
        sqLiteDatabase.execSQL("insert into medium(medium_type) values('Car'),('Bus')") ;
        sqLiteDatabase.execSQL("insert into city_mapping(departure,arrival,total_fare,medium_id) values('Karachi','Lahore','2000','1'),('Lahore','Karachi','2000','1'),('Karachi','Islamabad','5000','1'),('Islamabad','Karachi','5000','1'),('Islamabad','Lahore','1000','1'),('Lahore','Islamabad','1000','1')") ;
        sqLiteDatabase.execSQL("insert into city_mapping(departure,arrival,total_fare,medium_id) values('Karachi','Lahore','4000','2'),('Lahore','Karachi','4000','2'),('Karachi','Islamabad','6000','2'),('Islamabad','Karachi','6000','2'),('Islamabad','Lahore','1500','2'),('Lahore','Islamabad','1500','2')") ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    
    public boolean insertContact(String name, String phone, String email, String password, String address, String cnic_number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("address", address);
        contentValues.put("cnic_number",cnic_number);
        db.insert("user_profile", null, contentValues);
        return true;
    }
    public boolean checkIfUserValid(String phone_number,String password)
    {
        boolean result = false;
        SQLiteDatabase db = this.getReadableDatabase();
        if(phone_number!=null &&!phone_number.equals(" "))
        {
            String query = "select * from user_profile where phone='"+phone_number+"' and password='"+password+"'";
            Cursor c = db.rawQuery(query, null);
            if (c.getCount() > 0) {
                result = true;
            }
            c.close();
        }
        else
        {
            return false;
        }
        return result;
    }
    public void deleteBooking(String departure,String arrival,String dateofBooking,String fare)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="update Bookings set is_deleted=1 where departure='"+departure+"'" +
                " and arrival='"+arrival+"' and booking_date ='"+dateofBooking+"' and" +
                " price='"+fare+"'";
        db.execSQL(query);

    }
    public boolean checkIfUserExist(String phone_number)
    {
        boolean result = false;
        SQLiteDatabase db = this.getReadableDatabase();
        if(phone_number!=null &&!phone_number.equals(" "))
        {
        String query = "select * from user_profile where phone='"+phone_number+"'";
        Cursor c = db.rawQuery(query, null);
        if (c.getCount() > 0) {
            result = true;
        }
        c.close();
        }
        else
        {
            return false;
        }
        return result;
    }
    public  List<String> getAllCities()
    {
        List<String> cities=new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "select city_name from cities";
        Cursor c = db.rawQuery(query, null);
        if(c.getCount()>0 && c.moveToFirst()) {
            do {
                cities.add(c.getString(c.getColumnIndex("city_name")));
            }

            while(c.moveToNext());
        }
        c.close();
        return cities;
    }
    public  List<String> getAllMedium()
    {
        List<String> cities=new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "select medium_type from medium";
        Cursor c = db.rawQuery(query, null);
        if(c.getCount()>0 && c.moveToFirst()) {
            do {
                cities.add(c.getString(c.getColumnIndex("medium_type")));
            }

            while(c.moveToNext());
        }
        c.close();
        return cities;
    }
    public  List<BookingModel> getAllBookings()
    {
        List<BookingModel> bookingModelList=new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "select departure,arrival,price,booking_date from bookings where  ifnull(is_deleted,0)=0";
        Cursor c = db.rawQuery(query, null);
        if(c.getCount()>0 && c.moveToFirst()) {
            do {
                BookingModel bookingModel=new BookingModel();
                bookingModel.setArrival(c.getString(c.getColumnIndex("arrival")));
                bookingModel.setDeparture(c.getString(c.getColumnIndex("departure")));
                bookingModel.setDateOfBooking(c.getString(c.getColumnIndex("booking_date")));
                bookingModel.setPrice(c.getString(c.getColumnIndex("price")));
                bookingModelList.add(bookingModel);
            }

            while(c.moveToNext());
        }
        c.close();
        return bookingModelList;
    }
    public List<BookingModel> getAllCancelTickets()
    {
        List<BookingModel> bookingModelList=new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();
        String query = "select departure,arrival,price,booking_date from bookings where is_deleted=1";
        Cursor c = db.rawQuery(query, null);
        if(c.getCount()>0 && c.moveToFirst()) {
            do {
                BookingModel bookingModel=new BookingModel();
                bookingModel.setArrival(c.getString(c.getColumnIndex("arrival")));
                bookingModel.setDeparture(c.getString(c.getColumnIndex("departure")));
                bookingModel.setDateOfBooking(c.getString(c.getColumnIndex("booking_date")));
                bookingModel.setPrice(c.getString(c.getColumnIndex("price")));
                bookingModelList.add(bookingModel);
            }

            while(c.moveToNext());
        }
        c.close();
        return bookingModelList;
    }
    public boolean checUsernameAndPassword(String phone_number,String password)
    {
        boolean result = false;
        SQLiteDatabase db = this.getReadableDatabase();
        if(phone_number!=null &&password!=null)
        {
            String query = "select * from user_profile where phone='"+phone_number+"' and password = '"+password+"'";
            Cursor c = db.rawQuery(query, null);
            if (c.getCount() > 0) {
                result = true;
            }
            c.close();
        }
        else
        {
            return false;
        }
        return result;
    }
    public String getFare(String departure,String arrival,String medium)
    {
        String result = null;
        SQLiteDatabase db = this.getReadableDatabase();
        {
            String query = "select total_fare from city_mapping where departure='"+departure+"' and arrival = '"+arrival+"' and medium_id in ( select medium_id from medium where medium_type='"+medium+"')";
            Cursor c = db.rawQuery(query, null);
            if (c.getCount() > 0&& c.moveToFirst()) {
                result = c.getString(c.getColumnIndex("total_fare"));;
            }
            c.close();
        }

        return result;
    }
    public void saveBooking(String selectedDeparture, String selectedArrival, String selectedDate, String fare,String phone_number)
    {

        String imgSett = sharedPreferences.getString("phone_number", "");
        SQLiteDatabase db = this.getWritableDatabase();
        String query="insert into bookings(departure,arrival,booking_date,price,phone_number) values('"+selectedDeparture+"','"+selectedArrival+"','"+selectedDate+"','"+fare+"'" +
                "'"+phone_number+"')";
        db.execSQL(query);
    }

}
