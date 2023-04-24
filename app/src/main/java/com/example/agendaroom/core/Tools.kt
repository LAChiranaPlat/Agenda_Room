package com.example.agendaroom.core

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

object Tools {

    fun TextInputLayout.vacio():Boolean{
        return this.editText?.text!!.isEmpty()
    }

    fun TextInputLayout.str():String{
        return this.editText?.text.toString()
    }

    fun TextInputLayout.cls(){
        this.editText?.text?.clear()
    }

    fun TextInputLayout.on(){
        this.editText?.requestFocus()
    }

    fun verifyForm(cl:ConstraintLayout):Boolean{

        cl.forEach {

            if(it is TextInputLayout)
                if(it.vacio())
                    return false

        }

        return true
    }

    fun messages(context:Context,user:String="",fSi:()->Unit){
        MaterialAlertDialogBuilder(context)
            .setTitle("Atención: Proceso Ireversible")
            .setMessage("Ud esta seguro de eliminar el siguiente registro: $user")
            .setNegativeButton("NO") { dialog, which ->
                // Respond to negative button press
            }
            .setPositiveButton("OK") { dialog, which ->
                fSi()
            }
            .show()
    }

}