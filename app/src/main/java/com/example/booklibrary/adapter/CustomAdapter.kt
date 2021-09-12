package com.example.booklibrary.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.booklibrary.R
import com.example.booklibrary.databinding.RecyclerViewRowBinding

class CustomAdapter(private val context: Context,
                    private val bookId: MutableList<Int>,
                    private val bookTitle: MutableList<String>,
                    private val bookAuthor: MutableList<String>,
                    private val bookPages: MutableList<Int>
): RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    private var navController: NavController? = null
    private var bundle = Bundle()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false)
        return CustomViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.bookIdTxt.setText(bookId.get(position).toString())
        holder.bookTitleTxt.setText(bookTitle.get(position))
        holder.bookAuthorTxt.setText(bookAuthor.get(position))
        holder.bookPagesTxt.setText(bookPages.get(position).toString())
        holder.linearLayout.setOnClickListener {
            bundle.putString("id", bookId.get(position).toString())
            bundle.putString("title", bookTitle.get(position))
            bundle.putString("author", bookAuthor.get(position))
            bundle.putString("pages", bookPages.get(position).toString())
            navController = Navigation.findNavController(holder.itemView)
            navController!!.navigate(R.id.action_mainListFragment_to_updateFragment, bundle)

        }
    }

    override fun getItemCount(): Int {
        return bookId.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val linearLayout =  itemView.findViewById<LinearLayout>(R.id.mainLinearLayout)
        val bookIdTxt = itemView.findViewById<TextView>(R.id.bookId)
        val bookTitleTxt = itemView.findViewById<TextView>(R.id.bookTitle)
        val bookAuthorTxt = itemView.findViewById<TextView>(R.id.bookAuthor)
        val bookPagesTxt = itemView.findViewById<TextView>(R.id.bookPages)

    }
}
