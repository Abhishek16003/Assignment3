package com.code.abhishek.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper database;
    Button add,update,display,delete,save,view,storeint,storeext;
    EditText eroll,ename,esem,ephone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new DBHelper(this);
        add=(Button)findViewById(R.id.buttonadd);
        update=(Button)findViewById(R.id.buttonupdate);
        display=(Button)findViewById(R.id.buttondisplay);
        delete=(Button)findViewById(R.id.buttondelete);
        save=(Button)findViewById(R.id.buttonsave);
        view=(Button)findViewById(R.id.buttonview);
        storeint=(Button)findViewById(R.id.buttonintstore);
        storeext=(Button)findViewById(R.id.buttonext);
        eroll=(EditText)findViewById(R.id.editTextroll);
        ename=(EditText)findViewById(R.id.editTextname);
        esem=(EditText)findViewById(R.id.editTextsem);
        ephone=(EditText)findViewById(R.id.editTextphone);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eroll.getText().toString().isEmpty()||
                        ename.getText().toString().isEmpty()||
                        esem.getText().toString().isEmpty()||
                        ephone.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please fill all the details...",Toast.LENGTH_LONG).show();

                }else if(ephone.getText().toString().length()!=10){
                    Toast.makeText(MainActivity.this,"Please enter a valid 10 digit phone number",Toast.LENGTH_LONG).show();

                }
                else{
                String r=eroll.getText().toString();
                String n=ename.getText().toString();
                String s=esem.getText().toString();
                String p=ephone.getText().toString();
                SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("rollnumber",r);
                ed.putString("mname",n);
                ed.putString("msem",s);
                ed.putString("mphone",p);
                ed.commit();
                    Toast.makeText(MainActivity.this,"Data Saved successfully by sharedpreferences",Toast.LENGTH_LONG).show();
            }}
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SaveActivity.class));
            }
        });
        storeint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,InternalStorage.class);
                startActivity(i);
            }
        });
        storeext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ExternalStorage.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eroll.getText().toString().isEmpty()||
                        ename.getText().toString().isEmpty()||
                        esem.getText().toString().isEmpty()||
                        ephone.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this,"Please fill all the details...",Toast.LENGTH_LONG).show();

                }
                else if(ephone.getText().toString().length()!=10){
                    Toast.makeText(MainActivity.this,"Please enter a valid 10 digit phone number",Toast.LENGTH_LONG).show();

                }
                else{
                    Cursor b=database.disp1(eroll.getText().toString());
                    if(b.getCount()>0){
                        showm("Unable to insert","Student already exists...");
                        return;
                    }
                    boolean check=database.insert(eroll.getText().toString(),ename.getText().toString(),esem.getText().toString(),ephone.getText().toString());
                if (check==true){
                    Toast.makeText(MainActivity.this,"Data Successfully inserted....",Toast.LENGTH_LONG).show();
                    eroll.setText("");
                    ename.setText("");
                    esem.setText("");
                    ephone.setText("");}
                else{
                    Toast.makeText(MainActivity.this,"Data not inserted....",Toast.LENGTH_LONG).show();
                }}}
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result=database.disp();
                if(result.getCount()==0){
                    showm("Nothing","No Student Data exists....");
                    return;
                }
               StringBuffer buffer=new StringBuffer();
                while(result.moveToNext()){
                    buffer.append("Roll No :"+ result.getString(0)+"\n");
                    buffer.append("Name :"+ result.getString(1)+"\n");
                    buffer.append("Semester :"+ result.getString(2)+"\n");
                    buffer.append("Phone :"+ result.getString(3)+"\n\n");
                }
                showm("Student Data",buffer.toString());
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eroll.getText().toString().isEmpty()||
                        ename.getText().toString().isEmpty()||
                        esem.getText().toString().isEmpty()||
                        ephone.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please fill all the details...",Toast.LENGTH_LONG).show();

                }
                else if(ephone.getText().toString().length()!=10){
                    Toast.makeText(MainActivity.this,"Please enter a valid 10 digit phone number",Toast.LENGTH_LONG).show();

                }else{
                    Cursor b=database.disp1(eroll.getText().toString());
                    if(b.getCount()==0){
                        showm("Unable to update","Student does not exist.....");
                        return;
                    }

                boolean u1=database.update(eroll.getText().toString(),ename.getText().toString(),esem.getText().toString(),ephone.getText().toString());
                if(u1==true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
            }}
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(eroll.getText().toString().isEmpty()||
                        ename.getText().toString().isEmpty()||
                        esem.getText().toString().isEmpty()||
                        ephone.getText().toString().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please fill all the details...",Toast.LENGTH_LONG).show();

                }
                else{
                    Cursor b=database.disp2(eroll.getText().toString(),ename.getText().toString(),esem.getText().toString(),ephone.getText().toString());
                    if(b.getCount()==0){
                        showm("Unable to delete","Student does not exist. Please fill the details correctly");
                        return;
                    }
                Integer a=database.delete(eroll.getText().toString());
                if(a>0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
            }}
        });



    }
           public void showm(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();


    }
}
