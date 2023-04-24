package com.example.agendaroom.data.repo

import android.content.Context
import com.example.agendaroom.data.db.DB
import com.example.agendaroom.data.model.Usuarios

object DBUsuarios {

    var ctx:Context?=null

    suspend fun guardar(user:Usuarios){
        DB.getInstance(ctx!!.applicationContext).myDao.saveUser(user)
    }

    suspend fun delete(user:Usuarios){
        DB.getInstance(ctx!!.applicationContext).myDao.removeUser(user)
    }

    suspend fun list():List<Usuarios>{
        return DB.getInstance(ctx!!.applicationContext).myDao.listUsers()
    }

    suspend fun update(user:Usuarios){
        DB.getInstance(ctx!!.applicationContext).myDao.upUser(user)
    }


}