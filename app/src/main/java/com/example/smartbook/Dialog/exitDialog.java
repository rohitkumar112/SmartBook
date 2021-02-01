package com.example.smartbook.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.smartbook.Activities.LoginActivity;
import com.example.smartbook.Activities.MapsActivity;
import com.example.smartbook.Interfaces.DIalogListener;
import com.example.smartbook.R;

import static com.example.smartbook.Activities.LoginActivity.sharedPreferences;

public class exitDialog extends Dialog {
    Button logout,no;
    Context context;
    DIalogListener dIalogListener;
    public exitDialog(@NonNull Context context, DIalogListener dIalogListener) {
        super(context);
    this.context=context;
    this.dIalogListener=dIalogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);

    logout=findViewById(R.id.logout);
    no=findViewById(R.id.no);
    clickListener();
    }

    private void clickListener() {
    logout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sharedPreferences.edit().remove("phone_number").commit();
            if(dIalogListener!=null)
            {
                dIalogListener.onDone();
            }

            dismiss();
//            finish();
        }
    });
    no.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
        }
    });
    }
}
