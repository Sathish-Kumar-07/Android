package com.example.a7minuteworkoutapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HistoryDatabase(context:Context) :SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_ID) {
    companion object{
        private const val DATABASE_ID = 1
        private const val DATABASE_NAME = "HistoryDataBase"
        private const val TABLE_NAME = "HistoryTable"
        private const val SL_NO = "SlNo"
        private const val DATE_TIME = "DateTime"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createQuery = "CREATE TABLE $TABLE_NAME ( "+
                "$SL_NO INTEGER PRIMARY KEY AutoIncrement,"+
                "$DATE_TIME TEXT)"
        db?.execSQL(createQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun onAddTuple(dateTime:String,slNo: Int? = null){
        val contentValue = ContentValues()
        slNo?.let { contentValue.put(SL_NO,slNo) }
        contentValue.put(DATE_TIME,dateTime)
        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,contentValue)
        db.close()
    }
    fun onRemoveTuple(slNo:Int){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$SL_NO = $slNo",null)
        db.close()
    }
    fun onSelectQuery():List<HistoryData>{
        val historyList = arrayListOf<HistoryData>()
        var cursor:Cursor?
        val db = this.readableDatabase
        cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        if (cursor!=null){
            while (cursor.moveToNext()){
                val slNo = cursor.getInt(cursor.getColumnIndex("$SL_NO"))
                val dateTime = cursor.getString(cursor.getColumnIndex("$DATE_TIME"))
                historyList.add(HistoryData(slNo,dateTime))
            }

        }
        cursor.close()
        return historyList
    }
}