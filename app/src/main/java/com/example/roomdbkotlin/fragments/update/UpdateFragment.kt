package com.example.roomdbkotlin.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdbkotlin.R
import com.example.roomdbkotlin.model.User
import com.example.roomdbkotlin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.custome_list.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_update, container, false)

        mViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        view.edtUpdateFirstName.setText(args.currentUser.firstName)
        view.edtUpdateLastName.setText(args.currentUser.lastname)
        view.edtUpdateAge.setText(args.currentUser.age.toString())

        view.btnUpdate.setOnClickListener{
            updateItem()
        }

        //Add Menu
        setHasOptionsMenu(true)

        return  view
    }

    private fun updateItem() {
        val firstName = edtUpdateFirstName.text.toString()
        val lastName = edtUpdateLastName.text.toString()
        val age = Integer.parseInt(edtUpdateAge.text.toString())

        if (inputCheck(firstName,lastName,edtUpdateAge.text)){
            val updateUser = User(args.currentUser.id,firstName,lastName,age)
            mViewModel.updateUser(updateUser)

            Toast.makeText(context,"Item Updated",Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.updateFragmentToListFragment)
        }else{
            Toast.makeText(context,"Please Enter Valid Data",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstname : String, lastname: String, age : Editable): Boolean{

        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastname) && age.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId  == R.id.deleteIconId){
            deleteUser()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "User Removed ${args.currentUser.firstName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.updateFragmentToListFragment)
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}")
        builder.create().show()
    }
}