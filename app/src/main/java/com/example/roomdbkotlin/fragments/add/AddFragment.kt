package com.example.roomdbkotlin.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdbkotlin.R
import com.example.roomdbkotlin.model.User
import com.example.roomdbkotlin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserviewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserviewModel = ViewModelProvider(this)[UserViewModel::class.java]

        view.btnAdd.setOnClickListener{
            insertdataToDatabase()
        }

        return  view
    }

    private fun insertdataToDatabase() {

        val firstName = edtFirstName.text.toString()
        val lastName = edtLastName.text.toString()
        val age = edtAge.text

        if (inputCheck(firstName,lastName,age)){
            val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))

            mUserviewModel.addUser(user)
            Toast.makeText(context,"Data Add successfully",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.addFragmentToListFragment)
        }else{
            Toast.makeText(context,"Please Enter Valid Data",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstname : String, lastname: String, age : Editable): Boolean{

        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && age.isEmpty())

    }
}