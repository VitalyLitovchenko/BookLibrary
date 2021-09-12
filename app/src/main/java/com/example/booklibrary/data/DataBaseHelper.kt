package com.example.booklibrary.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "Booklibrary.db"
const val VERSON = 1
const val TABLE_NAME = "library"
const val COLUMN_ID = "id"
const val COLUMN_TITLE = "book_title"
const val COLUMN_AUTHOR = "book_author"
const val COLUMN_PAGES = "book_pages"

class DataBaseHelper(_context: Context)
    : SQLiteOpenHelper(_context,
                        DATABASE_NAME,
                        null,
                        VERSON) {

    private val context = _context

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_AUTHOR TEXT, " +
                "$COLUMN_PAGES INTEGER);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addBook(title: String, author: String, pages: Int){
        val db: SQLiteDatabase = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_AUTHOR, author)
        cv.put(COLUMN_PAGES, pages)

        val result = db.insert(TABLE_NAME, null, cv)
        if (result.equals(-1)){
            Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this.context, "Added Successfully!", Toast.LENGTH_SHORT).show()
        }
    }

    fun readAllData(): Cursor{
        val query = "SELECT * FROM $TABLE_NAME"
        val db: SQLiteDatabase = readableDatabase

        var cursor: Cursor? = null
        if (db != null){
            cursor = db.rawQuery(query, null)
        }

        return cursor!!
    }

    fun updateData(rowid: String, title: String, author: String, pages: String){
        val db: SQLiteDatabase = writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_TITLE, title)
        cv.put(COLUMN_AUTHOR, author)
        cv.put(COLUMN_PAGES, pages)

        val result = db.update(TABLE_NAME, cv, "id=?", arrayOf(rowid))
        if (result == (-1)){
            Toast.makeText(this.context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this.context, "Successfully Updated!", Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOneRow(rowId: String){
        val db : SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "id=?", arrayOf(rowId))
    }

    fun deleteAllData(){
        val db: SQLiteDatabase = writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }
}