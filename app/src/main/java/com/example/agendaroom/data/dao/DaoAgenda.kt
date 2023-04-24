package com.example.agendaroom.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.agendaroom.data.model.Usuarios

@Dao
interface DaoAgenda {

    @Insert
    suspend fun saveUser(usuario:Usuarios):Long

    @Delete
    suspend fun removeUser(usuario:Usuarios)

    @Update
    suspend fun upUser(usuario:Usuarios)

    @Query("select * from users")
    suspend fun listUsers():List<Usuarios>

    /*@Query("select count(id) from users")
    suspend fun cantUser():List<Usuarios>*/

}