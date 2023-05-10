package com.example.endproject

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.endproject.adapters.MyAdapter
import com.example.endproject.databinding.ActivityMainBinding
import com.example.endproject.databinding.ItemDialogBinding
import com.example.endproject.db.MyDbHelper
import com.example.endproject.models.User

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myDbHelper: MyDbHelper
    private lateinit var myAdapter: MyAdapter
    private lateinit var list: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
            dialog.setView(itemDialogBinding.root)
            dialog.show()
            dialog.setCancelable(true)
            itemDialogBinding.btnSave.setOnClickListener {
                val user = User(
                    itemDialogBinding.edtName.text.toString()
                )
                myDbHelper.addUser(user)
                loadData()
                Toast.makeText(this, "User qo'shildi", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }
        }

    }

    fun loadData() {
        myDbHelper = MyDbHelper(this)
        list = ArrayList()
        myAdapter = MyAdapter(this, myDbHelper.getAllUsers())
        binding.itemRv.adapter = myAdapter
    }


}