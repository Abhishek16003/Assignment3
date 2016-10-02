package com.code.abhishek.assignment3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class ExternalStorage extends AppCompatActivity {
     Button b1,b3,b4,next;

    EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        b1=(Button)findViewById(R.id.buttonwriteext);
        b3=(Button)findViewById(R.id.buttonwritepublic);
        b4=(Button)findViewById(R.id.buttonwriteprivate);
        next=(Button)findViewById(R.id.buttonnext);
        et1=(EditText)findViewById(R.id.editTextext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ExternalStorage.this,External.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s;
                s= Environment.getExternalStorageState();
                if(Environment.MEDIA_MOUNTED.equals(s)){
                    File f=Environment.getExternalStorageDirectory();
                    File d=new File(f.getAbsolutePath()+"/Assignment3File");
                    if(!d.exists()){
                        d.mkdir();
                    }
                    File fi=new File(d,"helloworld.txt");
                    String info=et1.getText().toString();
                    try{
                        FileOutputStream fos=new FileOutputStream(fi);
                        fos.write(info.getBytes());
                        fos.close();
                        et1.setText("");
                        Toast.makeText(getApplicationContext(),"Information saved to the file",Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"SD Card not found...",Toast.LENGTH_LONG).show();
                }
            }
        });

         b3.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
             String data=et1.getText().toString();
             File ab=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
             File m1=new File(ab,"imp.txt");

                 try{
                     FileOutputStream fos=new FileOutputStream(m1);
                     fos.write(data.getBytes());
                     fos.close();
                     et1.setText("");
                     Toast.makeText(getApplicationContext(),"Information saved to the file",Toast.LENGTH_LONG).show();
                 }catch(Exception e){
                     e.printStackTrace();
                 }


             }
         });
         b4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String data=et1.getText().toString();
                 File ab=getExternalFilesDir("ABC");
                 File m1=new File(ab,"xyz.txt");

                 try{
                     FileOutputStream fos=new FileOutputStream(m1);
                     fos.write(data.getBytes());
                     fos.close();
                     et1.setText("");
                     Toast.makeText(getApplicationContext(),"Information saved to the file",Toast.LENGTH_LONG).show();
                 }catch(Exception e){
                     e.printStackTrace();
                 }

             }
         });


    }
}
