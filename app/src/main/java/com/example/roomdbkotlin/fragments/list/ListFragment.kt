package com.example.roomdbkotlin.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdbkotlin.R
import com.example.roomdbkotlin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val  recyclerview = view.recyclerViewList
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.listFragmentToAddFragment)
        }

        setHasOptionsMenu(true)

        return  view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteIconId){
            deleteAllUsersFromDB()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsersFromDB() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mUserViewModel.deleteAllUsersFromDB()
            Toast.makeText(
                requireContext(),
                "Successfully removed Everything",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete Everything?")
        builder.setMessage("Are you sure you want to delete Everything")
        builder.create().show()
    }
}