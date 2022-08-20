package com.example.aisle.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aisle.R

class PhoneNumberFragment : Fragment() {

    private lateinit var btnContinue: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_phone_number, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View?) {
        btnContinue = view?.findViewById(R.id.continue_btn)!!
        btnContinue.setOnClickListener {
            val phoneNumber = "9952916759"
            val action =
                PhoneNumberFragmentDirections.actionPhoneNumberFragmentToOtpFragment(phoneNumber)
            findNavController().navigate(action)
        }
    }

}