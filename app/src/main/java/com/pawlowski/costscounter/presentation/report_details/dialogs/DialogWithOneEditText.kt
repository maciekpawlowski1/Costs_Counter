package com.pawlowski.costscounter.presentation.report_details.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.pawlowski.costscounter.R

class DialogWithOneEditText(private val onDialogButtonsClickListener: OnDialogButtonsClickListener, private val confirmButtonText: String = "Add", private val startingEditTextText: String? = "", private val dialogTittle: String = "Add new category", private val inputHint: String = "Category name"): DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.dialog_with_one_input, container, false)
        //Removing white color around dialog
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setStyle(STYLE_NO_FRAME, android.R.style.Theme)
        dialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)


        val cancelButton: Button = view.findViewById(R.id.cancel_button_dialog_with_one_input)
        val addButton: Button = view.findViewById(R.id.add_button_dialog_with_one_input)

        addButton.text = confirmButtonText

        val nameInput: TextInputEditText = view.findViewById(R.id.name_input_dialog_with_one_input)
        val nameInputLayout: TextInputLayout = view.findViewById(R.id.nameInputLayout_dialog_with_one_input)

        val dialogTittleTextView: TextView = view.findViewById(R.id.dialog_tittle_text_view_dialog_with_one_input)
        dialogTittleTextView.text = dialogTittle

        nameInput.setText(startingEditTextText)
        nameInputLayout.hint = inputHint

        cancelButton.setOnClickListener {
            dismiss()
        }

        addButton.setOnClickListener {
            nameInputLayout.error = null
            if(nameInput.text.toString().isEmpty())
            {
                nameInputLayout.error = "Required!"
            }
            else
            {
                val name = nameInput.text.toString()
                onDialogButtonsClickListener.onConfirmButtonClickInDialog(name)
                dismiss()
            }
        }

        return view
    }

    interface OnDialogButtonsClickListener
    {
        fun onConfirmButtonClickInDialog(name: String)
    }
}