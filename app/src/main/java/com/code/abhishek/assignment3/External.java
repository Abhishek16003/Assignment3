package com.code.abhishek.assignment3;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class External extends AppCompatActivity {
     TextView tp;
    Button h,j,k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        tp=(TextView)findViewById(R.id.textViewdispext);
        h=(Button)findViewById(R.id.buttonrext);
        j=(Button)findViewById(R.id.buttonreadpublic);
        k=(Button)findViewById(R.id.buttonreadprivate);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File f= Environment.getExternalStorageDirectory();
                File d=new File(f.getAbsolutePath()+"/Assignment3File");
                File fi=new File(d,"helloworld.txt");
                String msg;
                FileInputStream is=null;
                try{
                    is=new FileInputStream(fi);
                    InputStreamReader ir=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(ir);
                    StringBuffer sb=new StringBuffer();
                    while((msg=br.readLine())!=null){
                        sb.append(msg + "\n");
                    }
                    tp.setText(sb.toString());
                    tp.setVisibility(View.VISIBLE);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
        j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                File ab=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                File m1=new File(ab,"imp.txt");
                String msg;
                FileInputStream is=null;
                try{
                    is=new FileInputStream(m1);
                    InputStreamReader ir=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(ir);
                    StringBuffer sb=new StringBuffer();
                    while((msg=br.readLine())!=null){
                        sb.append(msg + "\n");
                    }
                    tp.setText(sb.toString());
                    tp.setVisibility(View.VISIBLE);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            }
        });
        k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File ab=getExternalFilesDir("ABC");
                File m1=new File(ab,"xyz.txt");
                String msg;
                FileInputStream is=null;
                try{
                    is=new FileInputStream(m1);
                    InputStreamReader ir=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(ir);
                    StringBuffer sb=new StringBuffer();
                    while((msg=br.readLine())!=null){
                        sb.append(msg + "\n");
                    }
                    tp.setText(sb.toString());
                    tp.setVisibility(View.VISIBLE);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if(is!=null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });


    }
}
