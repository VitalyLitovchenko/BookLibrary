package com.example.booklibrary.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.booklibrary.R
import com.example.booklibrary.data.DataBaseHelper
import com.example.booklibrary.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            val dbHelper = DataBaseHelper(requireActivity())
            dbHelper.addBook(binding.editTextTitle.text.toString().trim(),
                binding.editTextAuthor.text.toString().trim(),
                (binding.editTextPages.text.toString().trim()).toInt())
            findNavController().navigate(R.id.action_addFragment_to_mainListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}