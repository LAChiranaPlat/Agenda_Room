package com.example.agendaroom.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Usuarios(
    @PrimaryKey(autoGenerate = true) var id:Int=0,
    @ColumnInfo(name="nombres") val name:String="",
    @ColumnInfo(name="apellidos")val lname:String="",
    val cargo:String="",
    val fono:String="",
    val direccion:String="",
    val email:String="",
    val edad:Int=10
)
