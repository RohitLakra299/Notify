package com.example.notify.ui.fragmets

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.example.notify.R
import com.example.notify.ViewModal.notesViewModal
import com.example.notify.databinding.FragmentEditBinding
import com.example.notify.modal.Notes
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*


class editFragment : Fragment() {

 val oldnotes by navArgs<editFragmentArgs>()
    lateinit var binding : FragmentEditBinding
    var priority : String = "1"
    val viewModal : notesViewModal by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(layoutInflater,container,false)

        binding.editTitle.setText(oldnotes.data.title)
        setHasOptionsMenu(true)
        binding.editsub.setText(oldnotes.data.subtitle)
        binding.editnote.setText(oldnotes.data.notes)
        when(oldnotes.data.priority){
            "1" -> {
                binding.editlowp.setImageResource(R.drawable.done)
                binding.edithighp.setImageResource(0)
                binding.editmidp.setImageResource(0)
                priority = "1"
            }
            "2" -> {
                binding.editmidp.setImageResource(R.drawable.done)
                binding.editlowp.setImageResource(0)
                binding.edithighp.setImageResource(0)
                priority = "2"
            }
            "3" -> {
                binding.edithighp.setImageResource(R.drawable.done)
                binding.editlowp.setImageResource(0)
                binding.editmidp.setImageResource(0)
                priority = "3"
            }
        }
        binding.edithighp.setOnClickListener{
            binding.edithighp.setImageResource(R.drawable.done)
            binding.editlowp.setImageResource(0)
            binding.editmidp.setImageResource(0)
            priority = "3"
        }
        binding.editlowp.setOnClickListener{
            binding.editlowp.setImageResource(R.drawable.done)
            binding.edithighp.setImageResource(0)
            binding.editmidp.setImageResource(0)
            priority = "1"
        }
        binding.editmidp.setOnClickListener {
            binding.editmidp.setImageResource(R.drawable.done)
            binding.editlowp.setImageResource(0)
            binding.edithighp.setImageResource(0)
            priority = "2"
        }
        binding.editbtn.setOnClickListener {
            updateNotes(it)
        }
        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title =  binding.editTitle.text.toString()
        val subtitle =  binding.editsub.text.toString()
        val notes = binding.editnote.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        val data  = Notes(oldnotes.data.id,title = title,subtitle = subtitle,notes = notes ,date = currentDate.toString(),priority = priority)
        viewModal.updateNotes(data)
        Toast.makeText(requireContext() , "Note Updated", Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_editFragment_to_homeFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menudeleteBtn){
            val bottomSheet = BottomSheetDialog(requireContext(),R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.delete_dialog)
            val textviewyes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialogno)

            textviewyes?.setOnClickListener {
          viewModal.deleteNotes(oldnotes.data.id!!)
                bottomSheet.dismiss()
                Toast.makeText(requireContext() , "Note deleted", Toast.LENGTH_LONG).show()

                NavHostFragment.findNavController(this).navigate(R.id.action_editFragment_to_homeFragment)
            }
            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()

        }
        return super.onOptionsItemSelected(item)
    }

}