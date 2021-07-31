package com.bignerdbranch.android.navigationcomponentsexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

private const val RECIPIENT_NAME_TAG = "recipient"
private const val RECIPIENT_AMOUNT_TAG = "amount"

class ConfirmationFragment : Fragment() {

    private lateinit var recipientName: String
    private lateinit var recipientAmount: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipientName = requireArguments()!!.getString(RECIPIENT_NAME_TAG).toString()
        var amount = requireArguments()!!.getParcelable<Money>(RECIPIENT_AMOUNT_TAG)
        recipientAmount = amount!!.amount!!.toPlainString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirmation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var message = "\$$recipientAmount was sent to $recipientName"
        view.findViewById<TextView>(R.id.confirmation_message).text = message
    }

}