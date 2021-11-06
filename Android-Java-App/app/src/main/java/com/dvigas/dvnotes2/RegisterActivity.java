package com.dvigas.dvnotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dvigas.dvnotes2.entity.Note;
import com.dvigas.dvnotes2.entity.User;

public class RegisterActivity extends AppCompatActivity {


    private EditText RUsername;
    private EditText RPassword;
    private EditText RePassword;
    private Button btnRUser;

    private NoteDAO noteDAO;

    private User userObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RUsername = findViewById(R.id.edtRUsername);
        RPassword = findViewById(R.id.edtRPassword);
        RePassword = findViewById(R.id.edtRePassword);

        btnRUser = (Button) findViewById(R.id.btnRUser);

        noteDAO = new NoteDAO(this);

        btnRUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = RUsername.getText().toString();
                String password = RPassword.getText().toString();
                String rePassword = RePassword.getText().toString();

                if (password.equals(rePassword)){
                    User userObj = new User();  // create obj
                    userObj.setUsername(username);  // link attribute w/ component
                    userObj.setPassword(password); // link attribute w/ component
                    long id = noteDAO.insertUser(userObj);
                    Toast.makeText(RegisterActivity.this, "Note add with ID: " + id, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Check username and password" , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}


