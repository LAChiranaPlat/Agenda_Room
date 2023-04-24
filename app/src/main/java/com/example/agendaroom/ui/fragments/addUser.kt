package com.example.agendaroom.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.example.agendaroom.R
import com.example.agendaroom.core.Tools.cls
import com.example.agendaroom.core.Tools.on
import com.example.agendaroom.core.Tools.str
import com.example.agendaroom.core.Tools.verifyForm
import com.example.agendaroom.data.model.Usuarios
import com.example.agendaroom.data.repo.DBUsuarios
import com.example.agendaroom.databinding.FragmentAddUserBinding
import kotlinx.coroutines.launch


class addUser : DialogFragment() {

    lateinit var layout:FragmentAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout=FragmentAddUserBinding.inflate(inflater,container,false)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout.apply {

            tilName.on()

            btnSave.setOnClickListener {

                if(verifyForm(root)==false) {
                    Toast.makeText(requireContext(), "Vacios", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                //Toast.makeText(requireContext(),"Usuario Registrado",Toast.LENGTH_LONG).show()

                DBUsuarios.ctx=requireContext().applicationContext

                lifecycleScope.launch {

                    DBUsuarios.guardar(
                        Usuarios(
                            0,
                            tilName.str(),
                            tilLname.str(),
                            tilCargo.str())
                    )

                    tilName.cls()
                    tilLname.cls()
                    tilCargo.cls()

                    tilName.on()

                    var pack=Bundle()
                    pack.putInt("indicador",1)

                    parentFragmentManager.setFragmentResult("newUser",pack)

                }

            }

            btnSave.onFocusChangeListener= object : OnFocusChangeListener{

                override fun onFocusChange(v: View?, hasFocus: Boolean) {
                    if(hasFocus){
                        val mInput=requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        mInput.hideSoftInputFromWindow(view.windowToken,0)
                    }
                }

            }

            btnCancel.setOnClickListener {
                dismiss()
            }

        }

    }

}