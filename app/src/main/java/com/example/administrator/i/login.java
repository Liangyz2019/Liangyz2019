package com.example.administrator.i;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class login extends AppCompatActivity {
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        login=findViewById(R.id.log_in);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(com.example.administrator.i.login.this,"登陆成功",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(com.example.administrator.i.login.this,transfer.class);
                startActivity(intent);
            }
        });
    }
}

