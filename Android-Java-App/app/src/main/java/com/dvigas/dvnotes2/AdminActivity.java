package com.dvigas.dvnotes2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dvigas.dvnotes2.entity.Note;
import com.dvigas.dvnotes2.entity.User;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ListView listUser;
    private NoteDAO noteDAO;
    private List<User> lUsers;
    private List<User> lUsersFilter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        listUser = findViewById(R.id.ListView1);
        noteDAO = new NoteDAO(this);

        lUsers = noteDAO.getAllUsers();
        lUsersFilter.addAll(lUsers);

        // add the list in the ListView
        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, lUsersFilter);
        listUser.setAdapter(adapter);

        // Open the context menu
        registerForContextMenu(listUser);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_admin, menu);

    }

    public void deleteUser(MenuItem item){
        //getting the menu info and pass to adapter
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final User userDelete = lUsersFilter.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Attention")
                .setMessage("Do you really want to delete the note?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lUsersFilter.remove(userDelete);
                        lUsers.remove(userDelete);
                        noteDAO.deleteUser(userDelete);
                        listUser.invalidateViews();
                    }
                }).create();
        dialog.show();

    }

    @Override
    public void onResume() {
        super.onResume();
        lUsers = noteDAO.getAllUsers();
        lUsersFilter.clear();
        lUsersFilter.addAll(lUsers);
        listUser.invalidateViews();
    }
}
