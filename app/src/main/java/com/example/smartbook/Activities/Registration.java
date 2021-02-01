package com.example.smartbook.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbook.Database.DBHELPER;
import com.example.smartbook.R;
import com.example.smartbook.Utils.Utility;

import static com.example.smartbook.Activities.LoginActivity.MyPREFERENCES;
import static com.example.smartbook.Activities.LoginActivity.sharedPreferences;

public class Registration extends AppCompatActivity implements TextWatcher {
    EditText name,email,last_name,phone_number,cnic_number,password;
    TextView name_error,first_name_error,last_name_error,phone_error,cnic_error,password_error;
    DBHELPER dbhelper;

    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        name=findViewById(R.id.name);
        email=findViewById(R.id.first_name);
        dbhelper = new DBHELPER(this);
        last_name=findViewById(R.id.last_name);
        phone_number=findViewById(R.id.phone_number);
        cnic_number=findViewById(R.id.cnic_number);
        password=findViewById(R.id.password);
        name_error=findViewById(R.id.name_error);
        first_name_error=findViewById(R.id.first_name_error);
        last_name_error=findViewById(R.id.last_name_error);
        phone_error=findViewById(R.id.phone_number_error);
        cnic_error=findViewById(R.id.cnic_number_error);
        password_error=findViewById(R.id.password_error);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isvalid())
                {
                    Boolean is_check;
                    is_check= dbhelper.checkIfUserExist(phone_number.getText().toString());
                    if(!is_check)
                    {
                    dbhelper.insertContact(name.getText().toString(),phone_number.getText().toString(),email.getText().toString(),password.getText().toString(),last_name.getText().toString(),cnic_number.getText().toString());
                    Toast.makeText(Registration.this,"successfully inserted",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(Registration.this,MapsActivity.class);
                        startActivity(intent);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("phone_number", phone_number.getText().toString());
                        editor.putString("password", password.getText().toString());
                        editor.putString("name",name.getText().toString());
                        editor.commit();
                    }
                    else
                    {
                        Toast.makeText(Registration.this,"User already registered with this Number",Toast.LENGTH_SHORT).show();
                    }
                    }
            }
        });

    }

    private boolean isvalid() {
        Boolean ischeck=true;
        if(!firstname_valid())
        {
            ischeck=false;
        }
        if(!lastname_valid())
        {
            ischeck=false;
        }
        if(!name_valid())
        {
            ischeck=false;
        }
        if(!cnicnumber_valid())
        {
            ischeck=false;
        }
        if(!phonenumber_valid())
        {
            ischeck=false;
        }
        if(!password_valid())
        {
            ischeck=false;
        }
        return ischeck;
    }
    private boolean name_valid()
    {
        if(TextUtils.isEmpty(name.getText().toString().trim()))
        {
            name_error.setText("Please Enter UserName");
            Utility.seterror(name_error,View.VISIBLE);
        return false;
        }
        if(name.getText().length()>0&&name.getText().length()<=3)
        {
            name_error.setText("Name length should be greater than three");
            Utility.seterror(name_error,View.VISIBLE);
            return  false;
        }
        else
        {
            Utility.seterror(name_error,View.GONE);
            return  true;
        }

    }
    private boolean firstname_valid()
    {
        Boolean isvalid=true;
        if(email.getText().length()>0&&email.getText().length()<=3)
        {
            first_name_error.setText("Email should be greater than  three");
            Utility.seterror(first_name_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(first_name_error,View.GONE);
            return true;
        }
    }
    private boolean lastname_valid()
    {
        Boolean isvalid=true;
        if(last_name.getText().length()>0&&last_name.getText().length()<=3)
        {
            last_name_error.setText("Address should be greater than  three");
            Utility.seterror(last_name_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(last_name_error,View.GONE);
            return true;
        }
    }
    private boolean phonenumber_valid()
    {
        Boolean isvalid=true;
        if(TextUtils.isEmpty(phone_number.getText().toString().trim()))
        {
            phone_error.setText("Phone Number Cannot Be Empty");
            Utility.seterror(phone_error,View.VISIBLE);
            return false;
        }
        if(phone_number.getText().length()>0&&phone_number.getText().length()<11)
        {
            phone_error.setText("Phone Number Should Be equal to 11");
            Utility.seterror(phone_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(phone_error,View.GONE);
            return true;
        }

    }

    private boolean cnicnumber_valid()
    {
        Boolean isvalid=true;
        if(cnic_number.getText().length()>0&&cnic_number.getText().length()<13)
        {
            cnic_error.setText("cnic length should be 13 digit");
            Utility.seterror(cnic_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(cnic_error,View.GONE);
            return true;
        }
    }
    private boolean password_valid()
    {
        Boolean isvalid=true;
        if(TextUtils.isEmpty(password.getText().toString().trim()))
        {
            password_error.setText("password cannot be empty");
            Utility.seterror(password_error,View.VISIBLE);
            return false;
        }
        if(password.getText().length()>0&&password.getText().length()<=6)
        {
            password_error.setText("password should be more than 6 characters");
            Utility.seterror(password_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(password_error,View.GONE);
            return true;
        }

    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
