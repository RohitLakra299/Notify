package com.example.notify.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notify.R
import com.example.notify.databinding.SingleNotesBinding
import com.example.notify.modal.Notes
import com.example.notify.ui.fragmets.homeFragmentDirections

class notesAdapter(val requireContext: Context, var notesList: List<Notes>) : RecyclerView.Adapter<notesAdapter.notesViewHolder>() {
    fun filltering(newlist: ArrayList<Notes>) {
    notesList = newlist
        notifyDataSetChanged()
    }
    class notesViewHolder( val binding: SingleNotesBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
       return notesViewHolder(SingleNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        val data = notesList[position]
        holder.binding.notesTitle.text = data.title
        holder.binding.notesdate.text = data.date
        holder.binding.notessubject.text = data.subtitle
        when(data.priority){
            "1" -> { holder.binding.notepriority.setBackgroundResource(R.drawable.lowp)}
            "2" -> {holder.binding.notepriority.setBackgroundResource(R.drawable.medip)}
            "3" -> {holder.binding.notepriority.setBackgroundResource(R.drawable.highp)}
        }
        holder.binding.root.setOnClickListener{
            val action = homeFragmentDirections.actionHomeFragmentToEditFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

}