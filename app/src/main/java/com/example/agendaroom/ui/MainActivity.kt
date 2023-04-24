package com.example.agendaroom.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agendaroom.R
import com.example.agendaroom.core.Tools
import com.example.agendaroom.data.db.DB
import com.example.agendaroom.data.model.Usuarios
import com.example.agendaroom.data.repo.DBUsuarios
import com.example.agendaroom.databinding.ActivityMainBinding
import com.example.agendaroom.graficos.Graficos
import com.example.agendaroom.ui.adapter.rv.myRecycler
import com.example.agendaroom.ui.dataClass.Contactos
import com.example.agendaroom.ui.fragments.addUser
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), myRecycler.IDeleteListener {

    lateinit var views:ActivityMainBinding
    var adapter:myRecycler = myRecycler(this)

    var lstUsers:ArrayList<Usuarios> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)

        supportFragmentManager.setFragmentResultListener("newUser",this, FragmentResultListener{req,pack->
            if(pack.getInt("indicador")==1){

                lifecycleScope.launch {
                    updateList(DBUsuarios.list())
                }


            }
        })

        //room
        adapter.myDiffUtil.submitList(lstUsers)

        views.apply {
            rvList.layoutManager=LinearLayoutManager(this@MainActivity)
            rvList.adapter=adapter

            btnAdd.setOnClickListener{
                val addUser=addUser()

                addUser.show(supportFragmentManager.beginTransaction(),"addUser")
            }

            btnEstadisticas.setOnClickListener {

                startActivity(Intent(this@MainActivity,Graficos::class.java))
            }
        }



    }

    override fun DeleteItem(user: Usuarios) {

        Tools.messages(this,user.name.toString(),{

            lifecycleScope.launch {
                DBUsuarios.delete(user)
                updateList(DBUsuarios.list())
            }

        })




    }

    fun updateList(value:List<Usuarios>){
        lstUsers.clear()
        lstUsers.addAll(value)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        DBUsuarios.ctx=this.applicationContext

        lifecycleScope.launch {

            updateList(DBUsuarios.list())

        }



    }

}