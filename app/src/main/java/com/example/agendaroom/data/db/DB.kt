package com.example.agendaroom.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agendaroom.data.dao.DaoAgenda
import com.example.agendaroom.data.model.Usuarios

@Database(
    entities = [Usuarios::class,],
    version = 2,
    exportSchema = false
)

abstract class DB:RoomDatabase() {

    abstract val myDao:DaoAgenda

    companion object{
        @Volatile
        var instanciaActual:DB?=null

        fun getInstance(ctx:Context):DB{

            synchronized(this){
                var instancia= instanciaActual

                if(instancia==null){

                    instancia= Room.databaseBuilder(
                        ctx.applicationContext,
                        DB::class.java,
                        "Inscritos"
                    ).fallbackToDestructiveMigration().build()
                }

                instanciaActual=instancia

            }

            return instanciaActual!!
        }

    }

}