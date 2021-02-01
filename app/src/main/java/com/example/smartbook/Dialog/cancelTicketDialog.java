package com.example.smartbook.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.smartbook.Interfaces.DIalogListener;
import com.example.smartbook.R;

public class cancelTicketDialog extends Dialog {
    Button cancel_ticket,no;
    DIalogListener dialogInterface;
    public cancelTicketDialog(@NonNull Context context, DIalogListener dialogInterface) {
        super(context);
        this.dialogInterface=dialogInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel_dialog);
        cancel_ticket=findViewById(R.id.cancel_ticket);
        no=findViewById(R.id.no);
        cancel_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dialogInterface!=null)
                {
                    dialogInterface.onDone();
                    dismiss();
                }
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
