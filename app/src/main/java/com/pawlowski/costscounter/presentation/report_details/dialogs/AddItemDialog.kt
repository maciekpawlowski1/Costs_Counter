package com.pawlowski.costscounter.presentation.report_details.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pawlowski.costscounter.R

class AddItemDialog(private val addItemDialogButtonsClickListener: AddItemDialogButtonsClickListener): DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.add_item_dialog, container, false)
        //Removing white color around dialog
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, android.R.style.Theme)
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        val cancelButton: Button = view.findViewById(R.id.cancel_button_add_item_dialog)
        val addButton: Button = view.findViewById(R.id.add_button_add_item_dialog)

        val nameInput: TextInputEditText = view.findViewById(R.id.name_input_add_item_dialog)
        val costInput: TextInputEditText = view.findViewById(R.id.cost_input_add_item_dialog)
        val amountInput: TextInputEditText = view.findViewById(R.id.amount_input_add_item_dialog)

        val nameInputLayout: TextInputLayout = view.findViewById(R.id.nameInputLayout_add_item_dialog)
        val costInputLayout: TextInputLayout = view.findViewById(R.id.costInputLayout_add_item_dialog)
        val amountInputLayout: TextInputLayout = view.findViewById(R.id.amountInputLayout_add_item_dialog)

        cancelButton.setOnClickListener {
            dismiss()
        }

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val cost = costInput.text.toString()
            val amount = amountInput.text.toString()
            nameInputLayout.error = null
            costInputLayout.error = null
            amountInputLayout.error = null
            var isError = false
            if (name.isEmpty())
            {
                nameInputLayout.error = "Required!"
                isError = true
            }
            if(cost.isEmpty())
            {
                costInputLayout.error = "Required!"
                isError = true
            }
            if(amount.isEmpty())
            {
                amountInputLayout.error = "Required!"
                isError = true
            }

            if(!isError)
            {
                addItemDialogButtonsClickListener.onAddButtonInDialogClick(name, cost.toDouble(), amount.toInt())
                dismiss()
            }

        }


        return view
    }

    interface AddItemDialogButtonsClickListener
    {
        fun onAddButtonInDialogClick(name: String, cost: Double, amount: Int)
    }
}