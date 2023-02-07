package com.example.bingo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    EditText userName,password;
    Button submitBtn;
    TextView registerText;
    SQLiteDatabase db;
    String userNameFromBackend,passwordFromBackend;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        submitBtn=findViewById(R.id.loginButton);
        registerText=findViewById(R.id.registerText);

        db=openOrCreateDatabase("UserDB", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS User(userID INT PRIMARY KEY,userName VARCHAR,password VARCHAR,email VARCHAR,mobilNo VARCHAR)");
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUserName=userName.getText().toString();
                String enteredPassword=password.getText().toString();
                Cursor c=db.rawQuery("SELECT userName,password FROM User WHERE userName='"+enteredUserName+"'",null);
                if(c.moveToFirst()){
                    userNameFromBackend=c.getString((1));
                    passwordFromBackend=c.getString((2));
                }
                else{
                    showMessage("Error","Invalid details");
                    clearText();
                }
                if(enteredUserName.equals(userNameFromBackend) && enteredPassword.equals(passwordFromBackend))
                {
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_LONG).show();
//                    Intent intent=new Intent(MainActivity.this,BingoGamePage.class);
//                    startActivity(intent);
                    startActivity(new Intent(
                            MainActivity.this,BingoGamePage.class
                    ));
                }
                else{
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_LONG).show();
                }
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, RegisterPage.class);
                startActivity(intent);
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
    }
}