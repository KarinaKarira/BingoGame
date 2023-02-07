package com.example.bingo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterPage extends AppCompatActivity {

    SQLiteDatabase db;
    Button registerBtn;
    EditText userName,password,email,mobileNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        mobileNo=findViewById(R.id.mobile);
        db=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE,null);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("INSERT INTO User VALUES('"+userName.getText().toString()+"','"+password.getText().toString()+"','"+email.getText().toString()+"','"+mobileNo.getText().toString()+"')'");
                showMessage("success","successfully inserted");
                clearText();
            }
        });
    }
    public void showMessage (String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){
        userName.setText("");
        password.setText("");
        email.setText("");
        mobileNo.setText("");
    }
}