package com.bignerdbranch.android.navigationcomponentsexample

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation

private const val RECIPIENT_NAME_TAG = "recipient"

class ChooseRecipientFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private lateinit var inputRecipient: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.next_btn).setOnClickListener(this)
        inputRecipient =
            (view.findViewById(R.id.input_recipient) as EditText).also { it.setOnClickListener(this) }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.next_btn -> {
                if (!TextUtils.isEmpty(inputRecipient.text.toString())) {
                    val bundle = bundleOf(RECIPIENT_NAME_TAG to inputRecipient.text.toString())
                    navController!!.navigate(
                        R.id.action_chooseRecipientFragment_to_specifyAmountFragment,
                        bundle
                    )
                } else {
                    Toast.makeText(context, "Enter an name...", Toast.LENGTH_LONG).show()
                }
            }
            R.id.cancel_btn -> requireActivity().onBackPressed()
        }
    }
}