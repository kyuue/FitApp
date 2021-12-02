package com.example.fitapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
                CREATE TABLE IF NOT EXISTS egitmenler ( id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), surname VARCHAR(20), tel VARCHAR(11), about VARCHAR(250), price INTEGER, photo BLOB );
                """)

        db.execSQL("""
                            CREATE TABLE IF NOT EXISTS besinler ( id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), type CHAR(1) REFERENCES BesinType(Type), unit CHAR(1) REFERENCES UnitType(Type), cal INTEGER );
                    """)

        db.execSQL("""
            CREATE TABLE IF NOT EXISTS BesinType (
              Type    CHAR(1)       PRIMARY KEY NOT NULL,
              Seq     INTEGER
            );
            
        """)

        db.execSQL("""
            INSERT INTO BesinType(Type, Seq) VALUES ('Y',1);

        """)

        db.execSQL("""
            INSERT INTO BesinType(Type, Seq) VALUES ('I',2);

        """)

        db.execSQL("""
            CREATE TABLE IF NOT EXISTS UnitType (
              Type    CHAR(1)       PRIMARY KEY NOT NULL,
              Seq     INTEGER
            );
        """)

        db.execSQL("""
            INSERT INTO UnitType(Type, Seq) VALUES ('U',1);
        """)

        db.execSQL("""
            INSERT INTO UnitType(Type, Seq) VALUES ('G',2);
        """)

        db.execSQL("""
            INSERT INTO UnitType(Type, Seq) VALUES ('L',3);
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {


    }

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "FitApp.db"
    }
}