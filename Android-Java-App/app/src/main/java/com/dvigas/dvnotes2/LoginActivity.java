package com.dvigas.dvnotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dvigas.dvnotes2.entity.User;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPassword;
    Button btnLogin;

    User userObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtRPassword);

        btnLogin = (Button)findViewById(R.id.btnLogin);

        final NoteDAO noteDAO = new NoteDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                String password1 = noteDAO.searchPass(username);
                if (password.equals(password1)){
                    Intent i = new Intent(LoginActivity.this, ListNotesActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Incorrect username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
