package com.example.endproject.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.endproject.models.User

class MyDbHelper(val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, VERSION),
    MyDbInterface {

    companion object {
        const val DB_NAME = "my_db"
        const val VERSION = 1
        const val TABLE_NAME = "my_table"
        const val ID = "id"
        const val NAME = "name"
        const val DATE = "date"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query =
            "create table $TABLE_NAME ($ID integer not null primary key autoincrement unique,$NAME text not null,$DATE text not null)"
        p0?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addUser(user: User) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME, user.name)
        contentValues.put(DATE, user.date)
        database.insert(TABLE_NAME, null, contentValues)
        database.close()
    }

    override fun getAllUsers(): ArrayList<User> {
        val list = ArrayList<User>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        val cursor = database.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(User(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                ))
            } while (cursor.moveToNext())
        }
        return list
    }


}