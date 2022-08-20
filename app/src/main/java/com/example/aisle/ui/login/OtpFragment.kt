package com.example.aisle.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aisle.R
import com.example.aisle.utils.Common.Companion.insertSpaceAfterCountryCode

class OtpFragment : Fragment() {

    private lateinit var btnContinue: Button
    private lateinit var tvUserPhoneNumber: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_otp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View?) {
        tvUserPhoneNumber = view?.findViewById(R.id.primary_title_tv)!!
        val userPhoneNumber= arguments?.getString("navArgPhoneNumber") ?: ""
        tvUserPhoneNumber.text = userPhoneNumber.insertSpaceAfterCountryCode()

        btnContinue = view?.findViewById(R.id.continue_btn)!!
        btnContinue.setOnClickListener {
            val action =
                OtpFragmentDirections.actionOtpFragmentToHomeActivity()
            findNavController().navigate(action)
            requireActivity().finish()
        }
    }

}