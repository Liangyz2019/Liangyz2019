package com.example.administrator.i;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class transfer extends AppCompatActivity {
    private Button nearby,navigation,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer);
        nearby=findViewById(R.id.nearby);
        navigation=findViewById(R.id.navigation);
        search=findViewById((R.id.search));
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(transfer.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}



