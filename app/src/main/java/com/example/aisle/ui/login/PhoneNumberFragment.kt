package com.example.aisle.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aisle.R

class PhoneNumberFragment : Fragment() {

    private val TAG = "PhoneNumberFragment"
    private lateinit var viewModel: PhoneViewModel
    private lateinit var btnContinue: Button
    private lateinit var etCountryCode: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var userPhoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PhoneViewModel::class.java]
        initObservers()
    }

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
        etCountryCode = view?.findViewById(R.id.country_code_et)!!
        etPhoneNumber = view.findViewById(R.id.phone_number_et)
        btnContinue = view.findViewById(R.id.continue_btn)

        btnContinue.setOnClickListener {
            userPhoneNumber = etCountryCode.text.toString().trim() + etPhoneNumber.text.toString().trim()
            viewModel.invokeIsExistingUserApi(userPhoneNumber)
        }
    }

    private fun initObservers() {
        viewModel.isExistingUserLiveData.observe(this, Observer { status ->
            Log.i(TAG, "Status of user is: $status")
            if(status) {
                val action =
                    PhoneNumberFragmentDirections.actionPhoneNumberFragmentToOtpFragment(userPhoneNumber)
                findNavController().navigate(action)
            }else {
                Toast.makeText(requireContext(), getString(R.string.incorrect_ph_number), Toast.LENGTH_LONG).show()
            }
        })
    }

}