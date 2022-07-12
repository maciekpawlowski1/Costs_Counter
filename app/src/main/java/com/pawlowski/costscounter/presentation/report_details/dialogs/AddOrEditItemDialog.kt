package com.pawlowski.costscounter.presentation.report_details.dialogs

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pawlowski.costscounter.R
import java.util.*

class AddOrEditItemDialog(private val addItemDialogButtonsClickListener: AddItemDialogButtonsClickListener,
                          private val secondButtonText: String = "Add",
                          private val startName: String = "",
                          private val startCost: Double = -1.0,
                          private val startAmount: Int = 1
                          ): DialogFragment() {

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
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)


        val cancelButton: Button = view.findViewById(R.id.cancel_button_add_item_dialog)
        val addButton: Button = view.findViewById(R.id.add_button_add_item_dialog)

        addButton.text = secondButtonText

        val nameInput: TextInputEditText = view.findViewById(R.id.name_input_add_item_dialog)
        val costInput: TextInputEditText = view.findViewById(R.id.cost_input_add_item_dialog)
        val amountInput: TextInputEditText = view.findViewById(R.id.amount_input_add_item_dialog)
        nameInput.setText(startName)
        if(startCost != -1.0)
        {
            costInput.setText("$startCost")
        }
        amountInput.setText("$startAmount")


        val nameInputLayout: TextInputLayout = view.findViewById(R.id.nameInputLayout_add_item_dialog)
        val costInputLayout: TextInputLayout = view.findViewById(R.id.costInputLayout_add_item_dialog)
        val amountInputLayout: TextInputLayout = view.findViewById(R.id.amountInputLayout_add_item_dialog)

        val calendarButton: ImageButton = view.findViewById(R.id.calendar_button_add_item_dialog)
        calendarButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val d = calendar.get(Calendar.DAY_OF_MONTH)
            val m = calendar.get(Calendar.MONTH)
            val y = calendar.get(Calendar.YEAR)
            val dateDialog = DatePickerDialog(requireContext(),
                { view, year, month, dayOfMonth ->
                    val month2 = if(month < 10)
                        "0$month"
                    else
                        month

                    val day2 = if(dayOfMonth<10)
                        "0$dayOfMonth"
                    else
                        dayOfMonth
                    var previousName = nameInput.text.toString()
                    previousName += "$day2.${month2}.$year"
                    nameInput.setText(previousName)

                }, y, m, d)
            dateDialog.show()
        }

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
                addItemDialogButtonsClickListener.onConfirmButtonInAddEditItemDialogClick(name, cost.toDouble(), amount.toInt())
                dismiss()
            }

        }


        return view
    }

    interface AddItemDialogButtonsClickListener
    {
        fun onConfirmButtonInAddEditItemDialogClick(name: String, cost: Double, amount: Int)
    }
}