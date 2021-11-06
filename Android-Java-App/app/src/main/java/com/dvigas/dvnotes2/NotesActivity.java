package com.dvigas.dvnotes2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dvigas.dvnotes2.entity.Note;

public class NotesActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Button btnSave;

    private NoteDAO noteDAO;

    private Note noteObj = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        // Associate variables with xml
        title = findViewById(R.id.edtTitle);
        content = findViewById(R.id.edtContent);

        btnSave = (Button) findViewById(R.id.btnSave);

        noteDAO = new NoteDAO(this);

        // Try to get the variable noteObj
        Intent i = getIntent();
        if (i.hasExtra("noteObj")){
            noteObj = (Note) i.getSerializableExtra("noteObj");
            title.setText(noteObj.getTitle());
            content.setText(noteObj.getContent());
        }

        // Create method to save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteObj == null){
                    noteObj = new Note();  // create obj
                    noteObj.setTitle(title.getText().toString());  // link attribute w/ component
                    noteObj.setContent(content.getText().toString()); // link attribute w/ component
                    long id = noteDAO.insert(noteObj);
                    Toast.makeText(NotesActivity.this, "Note add with ID: " + id, Toast.LENGTH_SHORT).show();
                }
                else {
                    noteObj.setTitle(title.getText().toString());  // link attribute w/ component
                    noteObj.setContent(content.getText().toString()); // link attribute w/ component
                    noteDAO.update(noteObj);
                    Toast.makeText(NotesActivity.this, "The note has been updated successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}

