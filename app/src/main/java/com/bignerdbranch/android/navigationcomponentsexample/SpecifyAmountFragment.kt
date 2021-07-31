package com.bignerdbranch.android.navigationcomponentsexample

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import java.math.BigDecimal

private const val RECIPIENT_NAME_TAG = "recipient"
private const val RECIPIENT_AMOUNT_TAG = "amount"

class SpecifyAmountFragment : Fragment(), View.OnClickListener {
    private var navController: NavController? = null

    private lateinit var recipientName: String
    private lateinit var amountInput: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipientName = requireArguments().getString(RECIPIENT_NAME_TAG).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specify_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        var message = "Sending money to $recipientName"

        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.send_btn).setOnClickListener(this)
        amountInput = view.findViewById(R.id.input_amount)
        view.findViewById<TextView>(R.id.recipient).setText(message)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.send_btn -> {
                if (!TextUtils.isEmpty(amountInput.text.toString())) {
                    val amount = Money(BigDecimal(amountInput.text.toString()))
                    val bundle = bundleOf(
                        RECIPIENT_NAME_TAG to recipientName,
                        RECIPIENT_AMOUNT_TAG to amount
                    )
                    navController!!.navigate(
                        R.id.action_specifyAmountFragment_to_confirmationFragment,
                        bundle
                    )
                } else {
                    Toast.makeText(context,"Enter an amount...",Toast.LENGTH_LONG).show()
                }
            }
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}