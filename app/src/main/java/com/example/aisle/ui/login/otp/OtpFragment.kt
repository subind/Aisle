package com.example.aisle.ui.login.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aisle.R
import com.example.aisle.utils.AppUtils
import com.example.aisle.utils.AppUtils.Companion.disableUserUiInteraction
import com.example.aisle.utils.AppUtils.Companion.enableUserUiInteraction
import com.example.aisle.utils.insertSpaceAfterCountryCode
import com.example.aisle.utils.isVisible
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class OtpFragment : Fragment() {

    private lateinit var viewModel: OtpViewModel
    private lateinit var btnContinue: Button
    private lateinit var tvUserPhoneNumber: TextView
    private lateinit var tvOtpCountDown: TextView
    private lateinit var etOtp: EditText
    private lateinit var userPhoneNumber: String
    private lateinit var pgBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[OtpViewModel::class.java]
        initObservers()
    }

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
        pgBar = view?.findViewById(R.id.pg_bar)!!
        etOtp = view?.findViewById(R.id.otp_et)!!
        tvOtpCountDown = view?.findViewById(R.id.otp_count_down_tv)!!

        tvUserPhoneNumber = view.findViewById(R.id.primary_title_tv)!!
        userPhoneNumber = arguments?.getString(getString(R.string.navArgPhoneNumber)) ?: ""
        tvUserPhoneNumber.text = userPhoneNumber.insertSpaceAfterCountryCode()

        btnContinue = view.findViewById(R.id.continue_btn)!!
        btnContinue.setOnClickListener {
            val userOtp = etOtp.text.toString().trim()
            if(userPhoneNumber.isNotEmpty() &&
                userOtp.isNotEmpty() &&
                userOtp.length == 4) {
                pgBar.isVisible(true)
                disableUserUiInteraction(requireActivity())
                viewModel.getTokenFromApi(userPhoneNumber, userOtp)
            }else {
                Toast.makeText(requireContext(), getString(R.string.otp_issue), Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun initObservers() {
        viewModel.otpCountDownCompleteStatusLiveData.observe(this, Observer {
            if(it) {
                Toast.makeText(requireContext(), getString(R.string.countdown_complete), Toast.LENGTH_LONG).show()
                runBlocking {
                    delay(3000)
                    viewModel.countDownTimer()
                }
            }
        })

        viewModel.otpCountDownTimeLiveData.observe(this, Observer {
            tvOtpCountDown.text = "00:${it.toString()}"
        })

        viewModel.otpVerifiedTokenObtainedLiveData.observe(this, Observer { token ->
            enableUserUiInteraction(requireActivity())
            pgBar.isVisible(false)
            if(token.isNullOrEmpty()) {
                Toast.makeText(requireContext(), getString(R.string.token_issue), Toast.LENGTH_LONG).show()
            } else {
                val action =
                    OtpFragmentDirections.actionOtpFragmentToHomeActivity(token)
                findNavController().navigate(action)
                requireActivity().finish()
            }
        })

    }

}