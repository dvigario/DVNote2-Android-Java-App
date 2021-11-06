package com.dvigas.dvnotes2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.dvigas.dvnotes2.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class ListNotesActivity extends AppCompatActivity {

    private ListView listNotes;
    private NoteDAO noteDAO;
    private List<Note> lNotes;
    private List<Note> lNotesFilter = new ArrayList<>();
    Button btnAdmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);

        listNotes = findViewById(R.id.listNotes);
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        noteDAO = new NoteDAO(this);

        lNotes = noteDAO.getAllNotes();
        lNotesFilter.addAll(lNotes);


        // add the list in the ListView
        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, lNotesFilter);
        listNotes.setAdapter(adapter);

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ListNotesActivity.this, AdminActivity.class);
                startActivity(i);
            }
        });

        // Open the context menu
        registerForContextMenu(listNotes);

    }

    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.main_menu, menu);

        // getting the field in the search menu
        final SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNote(newText);
                return false;
            }
        });

        return true;


    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_context, menu);

    }

    public void searchNote (String title){
        lNotesFilter.clear();
        for (Note noteObj: lNotes){
            if (noteObj.getTitle().toLowerCase().contains(title.toLowerCase())){
                lNotesFilter.add(noteObj);
            }
        }
        listNotes.invalidateViews();
    }

    public void delete(MenuItem item){
        //getting the menu info and pass to adapter
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Note noteDelete = lNotesFilter.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage("Do you really want to delete the note?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lNotesFilter.remove(noteDelete);
                        lNotes.remove(noteDelete);
                        noteDAO.delete(noteDelete);
                        listNotes.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    public void registerNote(MenuItem item){
        Intent i = new Intent(this, NotesActivity.class);
        startActivity(i);
    }

    public void update(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Note noteUpdate = lNotesFilter.get(menuInfo.position);
        Intent i = new Intent(this, NotesActivity.class);
        i.putExtra("noteObj", noteUpdate);
        startActivity(i);

    }

    @Override
    public void onResume() {
        super.onResume();
        lNotes = noteDAO.getAllNotes();
        lNotesFilter.clear();
        lNotesFilter.addAll(lNotes);
        listNotes.invalidateViews();
    }

}
