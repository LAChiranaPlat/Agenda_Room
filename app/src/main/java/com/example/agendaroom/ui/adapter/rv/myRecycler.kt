package com.example.agendaroom.ui.adapter.rv

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.agendaroom.data.model.Usuarios
import com.example.agendaroom.data.repo.DBUsuarios
import com.example.agendaroom.databinding.ItemListBinding
import com.example.agendaroom.ui.dataClass.Contactos
import kotlinx.coroutines.launch

class myRecycler(val deleteListener:IDeleteListener):RecyclerView.Adapter<myRecycler.ContentViews>() {

    private lateinit var ctx:Context

    interface IDeleteListener{
        fun DeleteItem(user:Usuarios)
    }

    class ContentViews(var view:ItemListBinding):RecyclerView.ViewHolder(view.root)

    private var callback=object : DiffUtil.ItemCallback<Usuarios>(){
        override fun areItemsTheSame(oldItem: Usuarios, newItem: Usuarios): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: Usuarios, newItem: Usuarios): Boolean {
            return oldItem.lname == newItem.lname
        }


    }

    var myDiffUtil=AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViews {
        ctx=parent.context

        var layout=ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ContentViews(layout)
    }

    override fun getItemCount()= myDiffUtil.currentList.size

    override fun onBindViewHolder(holder: ContentViews, position: Int) {

        var current=myDiffUtil.currentList.get(position)

        holder.view.apply {
            txtName.text=current.name
            txtLname.text=current.lname
            txtCargo.text=current.cargo


            imgDelete.setOnClickListener {

                deleteListener.DeleteItem(current)

            }
        }

    }

}

