package com.example.notify.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notify.modal.Notes

@Dao
interface notesDao {
    @Query("SELECT * FROM notes " )
    fun getnotes() : LiveData<List<Notes>>

    @Query("SELECT * FROM notes WHERE priority= 3" )
    fun gethighpnotes() : LiveData<List<Notes>>
    @Query("SELECT * FROM notes WHERE priority= 2" )
    fun getmidppnotes() : LiveData<List<Notes>>
    @Query("SELECT * FROM notes WHERE priority= 1" )
    fun getlowppnotes() : LiveData<List<Notes>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes)

    @Query("DELETE FROM notes Where id = :id ")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes)
}