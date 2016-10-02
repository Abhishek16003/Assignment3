package com.code.abhishek.assignment3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SaveActivity extends AppCompatActivity {
     TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        String roll=sp.getString("rollnumber","no roll number found");
        String name=sp.getString("mname","no name found");
        String sem=sp.getString("msem","no semester found");
        String phone=sp.getString("mphone","no phone number found");
        t1=(TextView)findViewById(R.id.textViewroll);
        t2=(TextView)findViewById(R.id.textViewname);
        t3=(TextView)findViewById(R.id.textViewsem);
        t4=(TextView)findViewById(R.id.textViewphone);
        t1.setText(roll);
        t2.setText(name);
        t3.setText(sem);
        t4.setText(phone);
    }
}
