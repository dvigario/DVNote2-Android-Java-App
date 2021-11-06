package com.dvigas.dvnotes2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.dvigas.dvnotes2.entity.Note;
import com.dvigas.dvnotes2.entity.User;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO {

    private ConnectionSQLiteHelper connectionDB;
    private SQLiteDatabase dbase;

    public NoteDAO(Context context){
        connectionDB = new ConnectionSQLiteHelper(context);
        dbase = connectionDB.getWritableDatabase();
        dbase = connectionDB.getReadableDatabase();
    }

    public long insert (Note noteObj){
        ContentValues values = new ContentValues();
        values.put("title", noteObj.getTitle());
        values.put("content", noteObj.getContent());
        return dbase.insert("tbnote", null, values);
    }

    public List<Note> getAllNotes(){
        List<Note> lNotes = new ArrayList<>();
        Cursor cursor = dbase.query("tbNote", new String[]{"id", "title", "content"},
                null, null, null, null, null);
                // this above is like a select * from
        while (cursor.moveToNext()){
            Note noteObj = new Note();
            noteObj.setIdnote(cursor.getInt(0));
            noteObj.setTitle(cursor.getString(1));
            noteObj.setContent(cursor.getString(2));

            lNotes.add(noteObj);  // add in the list
        }
        return lNotes;
    }

    public void delete(Note noteObj){
        dbase.delete("tbNote", "id = ?", new String[]{noteObj.getIdnote().toString()});
    }

    public void update(Note noteObj){
        ContentValues values = new ContentValues();
        values.put("title", noteObj.getTitle());
        values.put("content", noteObj.getContent());
        dbase.update("tbNote", values, "id = ?",
                new String[]{noteObj.getIdnote().toString()});
    }

    // =========  User part =======

    public long insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        System.out.println("UserName: " + user);
        return dbase.insert("tbuser", null, values);
    }

    public String searchPass(String user){
        User userObj = new User();
        dbase = connectionDB.getReadableDatabase();
        String query = "select username, password from tbuser";
        Cursor cursor = dbase.query("tbuser", new String[]{"id", "username", "password"},
                null, null, null, null, null);
        // this above is like a select * from
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()){
            do {
                a = cursor.getString(1);

                if (a.equals(user)){
                    b = cursor.getString(2);
                    break;
                }

            }
            while (cursor.moveToNext());
        }

        return b;
    }

    public long countUser(){
        long count = DatabaseUtils.queryNumEntries(dbase, "tbuser");
        dbase.close();
        return count;
    }

    public void deleteUser(User userObj){
        dbase.delete("tbuser", "id = ?", new String[]{userObj.getId().toString()});
    }

    public List<User> getAllUsers(){
        List<User> lUsers = new ArrayList<>();
        Cursor cursor = dbase.query("tbUser", new String[]{"id", "username", "password"},
                null, null, null, null, null);
        // this above is like a select * from
        while (cursor.moveToNext()){
            User userObj = new User();
            userObj.setId(cursor.getInt(0));
            userObj.setUsername(cursor.getString(1));
            userObj.setPassword(cursor.getString(2));

            lUsers.add(userObj);  // add in the list
        }
        return lUsers;
    }
}



