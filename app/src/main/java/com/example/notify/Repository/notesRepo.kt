package com.example.notify.Repository

import androidx.lifecycle.LiveData
import com.example.notify.DAO.notesDao
import com.example.notify.modal.Notes

class notesRepo(val dao : notesDao) {
    fun getallnotes() : LiveData<List<Notes>> {
        return dao.getnotes()
    }
    fun gethighnotes() : LiveData<List<Notes>> = dao.gethighpnotes()
    fun getlownotes() : LiveData<List<Notes>> = dao.getlowppnotes()
    fun getmidnotes() : LiveData<List<Notes>> = dao.getmidppnotes()
    fun insetNotes(notes : Notes){
        dao.insertNotes(notes)
    }
    fun deleteNotes(id: Int){
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}