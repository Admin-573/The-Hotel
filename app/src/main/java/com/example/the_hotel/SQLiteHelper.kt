package com.example.the_hotel

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "hotel.db"
        private const val TBL_HOTEL = "tbl_hotel"
        private const val NAME = "name"
        private const val PASS = "pass"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblHotel = ("CREATE TABLE $TBL_HOTEL($NAME TEXT,$PASS TEXT)")
        db?.execSQL(createTblHotel)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_HOTEL")
        onCreate(db)
    }

    fun insertUser(Usr: UserModel) : Long{

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(NAME,Usr.name)
        contentValues.put(PASS,Usr.pass)

        val success = db.insert(TBL_HOTEL,null,contentValues)
        db.close()
        return success
    }

    @SuppressLint("Range")
    fun getAllStudent() : ArrayList<UserModel>{

        val usrList : ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_HOTEL"
        val db = this.readableDatabase

        val cursor : Cursor?

        try{
            cursor = db.rawQuery(selectQuery,null)
        } catch (e : Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var name : String
        var pass : String

        if (cursor.moveToFirst()){
            do{
                name = cursor.getString(cursor.getColumnIndex("name"))
                pass = cursor.getString(cursor.getColumnIndex("pass"))

                val Usr = UserModel(name = name,pass = pass)
                usrList.add(Usr)
            } while (cursor.moveToNext())
        }

        return usrList
    }

}