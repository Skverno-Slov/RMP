package com.example.labwork28

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHandler(context: Context) : SQLiteOpenHelper(context, DB_NAME , null, DB_VERSION) {
    companion object{
        private  const val DB_NAME = "Library.db"
        private  const val DB_VERSION = 1
        private  const val TABLE_BOOKS = "Books"

        private  const val COLUMN_ID = "id"
        private  const val COLUMN_TITLE = "title"
        private  const val COLUMN_AUTHOR = "author"
        private  const val COLUMN_YEAR = "year"
        private  const val COLUMN_PAGES = "pages"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_BOOKS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_TITLE TEXT," +
                "$COLUMN_AUTHOR TEXT," +
                "$COLUMN_YEAR INTEGER," +
                "$COLUMN_PAGES INTEGER)")
        db?.execSQL(query)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKS")
        onCreate(db)
    }

    fun addBook(book: Book){
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE, book.name)
        values.put(COLUMN_AUTHOR, book.author)
        values.put(COLUMN_YEAR, book.year)
        values.put(COLUMN_PAGES, book.pageCount)

        db.insert(TABLE_BOOKS, null, values)

        db.close()
    }

    fun getBooks(): List<Book>{
        val bookList = mutableListOf<Book>()
        val db = readableDatabase
        val query = "SELECT * FROM books"
        val cursor = db.rawQuery(query, null)

        if(cursor.moveToFirst()){
            do{
                val book = Book(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)),
                    author = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR)),
                    year = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_YEAR)),
                    pageCount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PAGES)),
                )
                bookList.add(book)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return bookList
    }

    fun updateBook(book: Book){
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_TITLE, book.name)
        values.put(COLUMN_AUTHOR, book.author)
        values.put(COLUMN_YEAR, book.year)
        values.put(COLUMN_PAGES, book.pageCount)

        db.update(TABLE_BOOKS, values, "id = ?", arrayOf(book.id.toString()))

        db.close()
    }
}

data class Book(var id: Int? = null, val name: String, val author: String, val year: Int, val pageCount: Int)