package com.example.notify.ui.fragmets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notify.R
import com.example.notify.ViewModal.notesViewModal
import com.example.notify.databinding.FragmentInputBinding
import com.example.notify.modal.Notes
import java.text.SimpleDateFormat
import java.util.*


class inputFragment : Fragment() {
   lateinit var binding: FragmentInputBinding
   var priority : String = "1"
    val viewModal : notesViewModal by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentInputBinding.inflate(layoutInflater,container,false)
        binding.lowp.setImageResource(R.drawable.done)
        binding.higp.setOnClickListener{
        binding.higp.setImageResource(R.drawable.done)
            binding.lowp.setImageResource(0)
            binding.medp.setImageResource(0)
            priority = "3"
        }
        binding.lowp.setOnClickListener{
        binding.lowp.setImageResource(R.drawable.done)
            binding.higp.setImageResource(0)
            binding.medp.setImageResource(0)
            priority = "1"
        }
        binding.medp.setOnClickListener {
            binding.medp.setImageResource(R.drawable.done)
            binding.lowp.setImageResource(0)
            binding.higp.setImageResource(0)
            priority = "2"
        }
        binding.savebtn.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it : View?) {
      val title =  binding.addTitle.text.toString()
        val subtitle =  binding.addsubtitle.text.toString()
        val notes = binding.addnote.text.toString()
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        val data  = Notes(null,title = title,subtitle = subtitle,notes = notes ,date = currentDate.toString(),priority = priority)
        viewModal.addNotes(data)
        Toast.makeText(requireContext() , "Note Created",Toast.LENGTH_LONG).show()
        Navigation.findNavController(it!!).navigate(R.id.action_inputFragment_to_homeFragment)
    }


}