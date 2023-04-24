package com.example.agendaroom.ui.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.agendaroom.data.model.Usuarios
import com.example.agendaroom.data.repo.DBUsuarios
import kotlinx.coroutines.launch

class vmMain:AndroidViewModel(Application()) {

    var lista=MutableLiveData<ArrayList<Usuarios>>()

    fun getUser(){

        DBUsuarios.ctx=this.getApplication()

        viewModelScope.launch {

            lista.value?.addAll(DBUsuarios.list())

        }
    }

}