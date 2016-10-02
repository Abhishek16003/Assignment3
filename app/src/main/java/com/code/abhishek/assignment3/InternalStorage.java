package com.code.abhishek.assignment3;

import android.content.Context;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class InternalStorage extends AppCompatActivity {
    EditText ee;
    TextView tv;
    Button read1,write1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        ee=(EditText)findViewById(R.id.editTextenter);
        tv=(TextView)findViewById(R.id.textViewdisp);
        read1=(Button)findViewById(R.id.buttonread);
        write1=(Button)findViewById(R.id.buttonwrite);
        tv.setVisibility(View.GONE);
        read1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                String msg;
                FileInputStream is=openFileInput("hello.txt");
                InputStreamReader ir=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(ir);
                StringBuffer sb=new StringBuffer();
                while((msg=br.readLine())!=null){
                    sb.append(msg +"\n");
                }
                tv.setText(sb.toString());
                tv.setVisibility(View.VISIBLE);
            }catch(Exception e){
                  e.printStackTrace();
                }}
        });
        write1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=ee.getText().toString();
                if(ee.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the text",Toast.LENGTH_LONG).show();}
                else{
                    //File file=getFilesDir();
                    File file=new File(getApplicationContext().getFilesDir(),"hello.txt");

                try {

                    FileOutputStream fo=openFileOutput("hello.txt", Context.MODE_PRIVATE);
                    fo.write(msg.getBytes());
                    fo.close();
                    Toast.makeText(getApplicationContext(),"Information saved "+file+" /hello.txt" ,Toast.LENGTH_LONG).show();
                    ee.setText("");
                }catch(Exception e){
                 e.printStackTrace();
                }}
            }
        });





    }
}
