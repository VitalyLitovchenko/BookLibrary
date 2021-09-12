package com.example.booklibrary.ui

import android.app.ActionBar
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.booklibrary.R
import com.example.booklibrary.data.DataBaseHelper
import com.example.booklibrary.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get()= _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookId = arguments?.getString("id")
        binding.editTextTitleUpdate.setText(arguments?.getString("title"))
        binding.editTextAuthorUpdate.setText(arguments?.getString("author"))
        binding.editTextPagesUpdate.setText(arguments?.getString("pages"))

        binding.btnUpdate.setOnClickListener {
            val dbHelper = DataBaseHelper(requireActivity())
            dbHelper.updateData(bookId.toString(),
                binding.editTextTitleUpdate.text.toString().trim(),
                binding.editTextAuthorUpdate.text.toString().trim(),
                binding.editTextPagesUpdate.text.toString().trim())
            findNavController().navigate(R.id.action_updateFragment_to_mainListFragment)
        }

        binding.btnDelete.setOnClickListener {
            val dbHelper = DataBaseHelper(requireActivity())
            dbHelper.deleteOneRow(bookId.toString())
            findNavController().navigate(R.id.action_updateFragment_to_mainListFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}