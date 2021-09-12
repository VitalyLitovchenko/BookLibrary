package com.example.booklibrary.ui

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booklibrary.R
import com.example.booklibrary.adapter.CustomAdapter
import com.example.booklibrary.data.DataBaseHelper
import com.example.booklibrary.databinding.FragmentMainListBinding

class MainListFragment : Fragment() {

    private var _binding: FragmentMainListBinding? = null
    private val binding get()= _binding!!

    private val bookId = mutableListOf<Int>()
    private val bookTitle = mutableListOf<String>()
    private val bookAuthor = mutableListOf<String>()
    private val bookPages = mutableListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainListFragment_to_addFragment)
        }

        storeDataInLists()

        val adapter = CustomAdapter(requireActivity(), bookId, bookTitle, bookAuthor, bookPages)
        binding.mainRecyclerView.adapter = adapter


    }

    fun storeDataInLists(){
        val dbHelper = DataBaseHelper(requireActivity())
        val cursor: Cursor = dbHelper.readAllData()
        if (cursor.count == 0){
            binding.imageViewEmpty.setVisibility(View.VISIBLE)
            binding.textViewEmpty.setVisibility(View.VISIBLE)
        } else {
            while (cursor.moveToNext()){
                bookId.add(cursor.getInt(0))
                bookTitle.add(cursor.getString(1))
                bookAuthor.add(cursor.getString(2))
                bookPages.add(cursor.getInt(3))
            }
            binding.imageViewEmpty.setVisibility(View.GONE)
            binding.textViewEmpty.setVisibility(View.GONE)
        }
    }
}