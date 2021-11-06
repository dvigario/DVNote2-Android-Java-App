package com.dvigas.dvnotes2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dvigas.dvnotes2.utility.Utility;

    public class ConnectionSQLiteHelper extends SQLiteOpenHelper {

    private static final String name = "dvnote.db";
    private static final int version = 1;

    public ConnectionSQLiteHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(Utility.CREATE_TABLE_TBNOTE);
        db.execSQL("CREATE TABLE tbnote(id integer primary key autoincrement, " +
                "title TEXT, content TEXT)");

        db.execSQL("CREATE TABLE tbuser(id integer primary key autoincrement, " +
                "username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbnote");
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS tbuser");
        onCreate(db);

    }


    // param: application context, DB name = dv_note, null, DB version = 1
}
