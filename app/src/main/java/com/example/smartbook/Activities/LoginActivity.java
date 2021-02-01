package com.example.smartbook.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.smartbook.Database.DBHELPER;
import com.example.smartbook.FastNetworkingModels.LoginModel;
import com.example.smartbook.NavigationDrawer;
import com.example.smartbook.R;
import com.example.smartbook.Utils.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.smartbook.Utils.Utility.Password;
import static com.example.smartbook.Utils.Utility.PhoneNumber;

public class LoginActivity extends AppCompatActivity {
    EditText user_name,password;
    Button login ,register;
    TextView password_error,username_error;

    public static String MyPREFERENCES="login";
    private FirebaseAuth mAuth;
    LinearLayout progress_layout;
    EditText phone_number;
    DBHELPER dbhelper;
    ProgressBar progressBar;
    Boolean isUserValid=false;
    String WholeGeneratedCode;
    Button verifyuser;
   public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        verifyuser=findViewById(R.id.verifyuser);
        phone_number=findViewById(R.id.phone_number);
        progress_layout=findViewById(R.id.progress_layout);
        progressBar=findViewById(R.id.progressbar);
        username_error=findViewById(R.id.username_error);
        password_error=findViewById(R.id.password_error);
        user_name=findViewById(R.id.user_name);


        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String pageNumber=sharedPreferences.getString("phone_number", "-1");
        if(!pageNumber.equals("-1"))
        {
            Intent intent=new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        mAuth = FirebaseAuth.getInstance();
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        password_error=findViewById(R.id.password_error);
        register=findViewById(R.id.register);
        dbhelper = new DBHELPER(LoginActivity.this);
        verifyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                verfiyCode(WholeGeneratedCode);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(isvalid())
                {
                    boolean is_available=dbhelper.checUsernameAndPassword(user_name.getText().toString(),password.getText().toString());
                    if(is_available)
                    {
                        Toast.makeText(LoginActivity.this,"Login Successfull", LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
                        startActivity(intent);
                    }
                }
//                if(isvalid())
//                {
////                    http://localhost:91/api/registration
//                    AndroidNetworking.get("http://192.168.0.102:808/api/registration")
//                            .addQueryParameter("Phone_number", phone_number.getText().toString())
//                            .addQueryParameter("password", password.getText().toString())
////                            .addBodyParameter(PhoneNumber,user_name.getText().toString())
////                            .addBodyParameter(Password, password.getText().toString())
//                            .setTag("test")
//                            .setPriority(Priority.LOW)
//                            .build()
//                            .getAsObject(LoginModel.class, new ParsedRequestListener<LoginModel>() {
//                                @Override
//                                public void onResponse(LoginModel response) {
//                                    if(response.getMessage().equals("not successfull"))
//                                    {
//                                        Toast.makeText(LoginActivity.this,"no not working",LENGTH_SHORT).show();
//                                    }
//                                    else
//                                    {
//                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                                        editor.putString("phone_number", phone_number.getText().toString());
//                                        editor.putString("password", password.getText().toString());
//                                        editor.commit();
//                                        Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();
//                                        Intent intent=new Intent(LoginActivity.this, NavigationDrawer.class);
//                                        startActivity(intent);
//                                        //storing the username and password
//                                        //Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onError(ANError anError) {
//                                    Toast.makeText(LoginActivity.this,anError.getMessage(),LENGTH_SHORT).show();
//                                    System.out.println(anError.getMessage());
//                                }
//
//
//                            });
////                    isUserValid=checkIffUserExist(phone_number.getText().toString(),password.getText().toString()):
////
////                    isUserValid= dbhelper.checkIfUserValid(phone_number.getText().toString(),password.getText().toString());
////                    if(isUserValid)
////                    {
////                        Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
////                        startActivity(intent);
////                    }
////                    else
////                    {
////                        Toast.makeText(LoginActivity.this,"user not exist", LENGTH_SHORT).show();
////                    }
//                }
                //this code will be on the registration page
//                verifyUser(phone_number.getText().toString());

//                if(isvalid())
//                {
////                    verifyUser(user_name.getText().toString());
//                }
//                {

//                            .getAsJSONObject(new JSONObjectRequestListener() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    // do anything with response
//                    com.androidnetworking.error.ANError: java.net.ConnectException: Failed to connect to localhost/
//                                if(response.equals("successfull"))
//                                {
//                                    Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
//                                    startActivity(intent);
//                                }
//                                else
//                                {
//                                    username_error.setText("phonenumber or password is incorrect");
//                                    password_error.setText("phonenumber or password is incorrect");
//                                    Utility.seterror(username_error,View.VISIBLE);
//                                    Utility.seterror(password_error,View.VISIBLE);
//                                }
//                                }
//                                @Override
//                                public void onError(ANError error) {
//
//                                    // handle error
//                                Toast.makeText(LoginActivity.this, (CharSequence) error, LENGTH_SHORT).show();
//                                }
//                            });
//                {
//                    progress_layout.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.VISIBLE);
//                    String email=user_name.getText().toString();
//                    String spassword=password.getText().toString();
////                    signUpNewUserWithFirebase(email,spassword);
////                    signUpOldUserWithFirebase(email,spassword);
//                }
                    //temp gone//just for practice will be available tommorow
//                {
//                    AndroidNetworking.get("http://192.168.1.112:8044/api/Registration")
//                            .addQueryParameter("Phone_number", user_name.getText().toString())
//                            .addQueryParameter("password", password.getText().toString())
////                            .addBodyParameter(PhoneNumber,user_name.getText().toString())
////                            .addBodyParameter(Password, password.getText().toString())
//
//                            .setTag("test")
//                            .setPriority(Priority.LOW)
//                            .build()
//                            .getAsObject(LoginModel.class, new ParsedRequestListener<LoginModel>() {
//                                @Override
//                                public void onResponse(LoginModel response) {
//                                    if(response.getMessage().equals("not successfull"))
//                                    {
//                                        Toast.makeText(LoginActivity.this,"no not working",LENGTH_SHORT).show();
//                                    }
//                                    else
//                                    {
//                                        Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();
//                                        Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
//                                        startActivity(intent);
//                                        //storing the username and password
//                                        //Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onError(ANError anError) {
//                                    Toast.makeText(LoginActivity.this,anError.getMessage(),LENGTH_SHORT).show();
//                                    System.out.println(anError.getMessage());
//                                }
//
//
//                            });
//
//
//                }
//                }
//                {
//                    boolean is_available=mydb.checUsernameAndPassword(user_name.getText().toString(),password.getText().toString());
//                    if(is_available)
//                    {
//                        Toast.makeText(LoginActivity.this,"Login Successfull", LENGTH_SHORT).show();
//                        Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//                        Toast.makeText(LoginActivity.this,"login unsuccessfull", LENGTH_SHORT).show();
//                    }
//                }

//                {
//                    AndroidNetworking.get("http://192.168.1.125:8090/api/login")
//                            .addQueryParameter("name",sname)
//                            .addQueryParameter("pass",spass)
//
//                            .addQueryParameter("syncDateTime",null)
//                            .setTag("test")
//                            .setPriority(
//
//                                    RenderScript.Priority.LOW)
//                            .build()
//
//                            .getAsObject(verifylogin.class, new ParsedRequestListener<verifylogin>() {
//                                @Override
//                                public void onResponse(verifylogin response) {
//                                    if(response.getMessage().equals("unsuccessfull"))
//                                    {
//                                        Toast.makeText(LoginActivity.this,"no not working",LENGTH_SHORT).show();
//                                    }
//                                    else
//                                    {
//                                        Toast.makeText(getApplicationContext(), "Successfull Login", Toast.LENGTH_SHORT).show();
//                                        Intent intent=new Intent(MainActivity.this,dashboard.class);
//                                        startActivity(intent);
//                                        //storing the username and password
//                                        //Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//
//                                @Override
//                                public void onError(ANError anError) {
//                                    Toast.makeText(MainActivity.this,anError.getMessage(),LENGTH_SHORT).show();
//                                    System.out.println(anError.getMessage());
//                                }
//
//
//                            });
//                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,Registration.class);
                startActivity(intent);

            }
        });

    }

//    private Boolean checkIffUserExist(String phoneNumber, String password) {
//        final Boolean[] isvalid = {false};
//
//
//
//                }
//                }
//    }
//this should not be commented
//    private void signUpOldUserWithFirebase(String email, String password) {
//    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//         if(task.isSuccessful())
//         {
//             progress_layout.setVisibility(View.GONE);
//             progressBar.setVisibility(View.GONE);
//             FirebaseUser user = mAuth.getCurrentUser();
//             Toast.makeText(LoginActivity.this, "Authentication Successfull.",
//                     Toast.LENGTH_SHORT).show();
////             updateUI(user);
//         }
//         else
//         {
//             progress_layout.setVisibility(View.GONE);
//             progressBar.setVisibility(View.GONE);
//             Toast.makeText(LoginActivity.this, "Authentication failed.",
//                     Toast.LENGTH_SHORT).show();
//         }
//        }
//    });
//    }

//    private void signUpNewUserWithFirebase(String email,String password) {
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful())
//                {
//                    Toast.makeText(LoginActivity.this,"User Added Succesfully", LENGTH_SHORT).show();
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this,"some error", LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

//    private void verifyUser(String phoneNumber) {
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+92"+phoneNumber)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(LoginActivity.this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallBacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBacks =new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            WholeGeneratedCode =s;
//        }
//
//        @Override
//        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//            String code=phoneAuthCredential.getSmsCode();
//            verfiyCode(code);
//
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e)
//        {
//            Toast.makeText(LoginActivity.this,e.getMessage().toString(), LENGTH_SHORT).show();
//        }
//    };
//
//    public void verfiyCode(String code) {
//        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(code,password.getText().toString());
//        signInCredential(phoneAuthCredential);
//    }

//    private void signInCredential(PhoneAuthCredential phoneAuthCredential) {
//    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
//    firebaseAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//        @Override
//        public void onComplete(@NonNull Task<AuthResult> task) {
//            if(task.isSuccessful())
//            {
//                Toast.makeText(LoginActivity.this,"successfull", LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(LoginActivity.this,"unsuccesffull", LENGTH_SHORT).show();
//            }
//        }
//    });
//    }

    private boolean isvalid() {
        boolean isvalid=true;
        if(!validate_username())
        {
            isvalid=false;
        }
        if(!validate_password())
        {
            isvalid=false;
        }
        return isvalid;


    }

    private Boolean validate_username() {

        if(phone_number.getText().length()<=10)
        {
            Utility.seterror(username_error,View.VISIBLE);
           return false;
        }
        else
        {
            Utility.seterror(username_error,View.GONE);
        return  true;
        }

    }

    private Boolean validate_password() {
        Boolean isvalid=true;
        if(TextUtils.isEmpty(password.getText().toString().trim()))
        {
            Utility.seterror(password_error,View.VISIBLE);
            return false;
        }
        if(password.getText().length()>0&&password.getText().length()<=3)
        {
            Utility.seterror(password_error,View.VISIBLE);
            return false;
        }
        else
        {
            Utility.seterror(password_error,View.GONE);
            return true;
        }

    }

}
