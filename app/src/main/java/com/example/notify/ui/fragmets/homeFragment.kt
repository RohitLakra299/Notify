package com.example.notify.ui.fragmets

import android.app.Fragment
import android.os.Bundle
import android.view.*
import android.widget.SearchView

import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notify.R
import com.example.notify.ViewModal.notesViewModal
import com.example.notify.databinding.FragmentHomeBinding
import com.example.notify.modal.Notes
import com.example.notify.ui.adapter.notesAdapter

class homeFragment : Fragment() {
  lateinit var binding : FragmentHomeBinding
    val viewModal : notesViewModal by viewModels()
    var oldNotes = arrayListOf<Notes>()
    lateinit var adapter: notesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)
        viewModal.getNotes().observe(viewLifecycleOwner,{
            notesList ->
            oldNotes = notesList as ArrayList<Notes>

            adapter = notesAdapter(requireContext(),notesList)
            binding.recyclerview.layoutManager= GridLayoutManager(requireContext(),2)
                binding.recyclerview.adapter = adapter

        })
        binding.filterhigh.setOnClickListener{
            viewModal.gethighnotes().observe(viewLifecycleOwner,{
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = notesAdapter(requireContext(),notesList)
                binding.recyclerview.layoutManager= GridLayoutManager(requireContext(),2)

                binding.recyclerview.adapter = adapter

            })
        }
        binding.filterlow.setOnClickListener{
            viewModal.getlownotes().observe(viewLifecycleOwner,{
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = notesAdapter(requireContext(),notesList)
                binding.recyclerview.layoutManager= GridLayoutManager(requireContext(),2)
                binding.recyclerview.adapter = adapter

            })
        }
        binding.filtermed.setOnClickListener {
            viewModal.getmidnotes().observe(viewLifecycleOwner,{
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = notesAdapter(requireContext(),notesList)
                binding.recyclerview.layoutManager= GridLayoutManager(requireContext(),2)
                binding.recyclerview.adapter = adapter

            })
        }
        binding.filterimage.setOnClickListener {
            viewModal.getNotes().observe(viewLifecycleOwner,{
                    notesList ->
                oldNotes = notesList as ArrayList<Notes>
                adapter = notesAdapter(requireContext(),notesList)
                binding.recyclerview.layoutManager= GridLayoutManager(requireContext(),2)
                binding.recyclerview.adapter = adapter

            })
        }
        binding.addnotes.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_inputFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.serch_menu,menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView= item.actionView as SearchView
        searchView.queryHint = "Enter Notes Here...."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NotesFiltering(p0)
                return true
            }



        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    private fun NotesFiltering(p0 : String?) {
val newlist = arrayListOf<Notes>()
        for(i in oldNotes){
            if(i.title.contains(p0!!) || i.subtitle.contains(p0!!) ){
                newlist.add(i)
            }
        }
        adapter.filltering(newlist)
    }

}