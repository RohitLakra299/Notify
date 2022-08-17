package com.example.notify.ViewModal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notify.Database.NotesDatabase
import com.example.notify.Repository.notesRepo
import com.example.notify.modal.Notes

class notesViewModal(application: Application) : AndroidViewModel(application) {
    val repository : notesRepo

    init{
        val dao = NotesDatabase.getDatabaseinstance(application)?.mynotesDao()
        repository = notesRepo(dao)
    }
    fun addNotes(notes : Notes){
        repository.insetNotes(notes)
    }
    fun getNotes() : LiveData<List<Notes>> = repository.getallnotes()

    fun gethighnotes() : LiveData<List<Notes>> = repository.gethighnotes()
    fun getlownotes() : LiveData<List<Notes>> = repository.getlownotes()
    fun getmidnotes() : LiveData<List<Notes>> = repository.getmidnotes()
    fun deleteNotes(id : Int){
        repository.deleteNotes(id)
    }
    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }
}