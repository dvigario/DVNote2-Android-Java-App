package com.dvigas.dvnotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStartApp;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the method to call other activity

        btnStartApp = (Button) findViewById(R.id.btnStartApp);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        final NoteDAO noteDAO = new NoteDAO(this);

        long count = noteDAO.countUser();
        if (count >= 2){
            btnRegister.setVisibility(View.GONE);
        }
        else{
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(i);
                }
            });

        }

        btnStartApp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
