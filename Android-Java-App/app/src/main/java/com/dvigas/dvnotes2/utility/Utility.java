package com.dvigas.dvnotes2.utility;

public class Utility {
    // Constants fields note table
    public static final String TABLE_TBNOTE = "tbnote";
    public static final String  FIELD_ID= "Id";
    public static final String FIELD_TITLE = "Title";
    public static final String FIELD_CONTENT = "Content";

    public static final String CREATE_TABLE_TBNOTE = "CREATE TABLE " +
            ""+TABLE_TBNOTE +" ("+FIELD_ID+" " +
            "INTEGER, "+FIELD_TITLE+" TEXT, "+FIELD_CONTENT+" TEXT)";
}
