package com.example.notify.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notify.DAO.notesDao
import com.example.notify.modal.Notes

@Database(entities = [Notes::class], version = 1,exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun mynotesDao() : notesDao

    companion object{
        @Volatile
        var INSTANCE : NotesDatabase? = null

        fun getDatabaseinstance(context: Context): NotesDatabase {
            val tempInstance  = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val roomdatabaseinstance = Room.databaseBuilder(context,NotesDatabase::class.java,"notes").allowMainThreadQueries().build()
                INSTANCE = roomdatabaseinstance
                return return roomdatabaseinstance
            }
        }

    }
}