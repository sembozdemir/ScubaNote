package com.sembozdemir.scubanote.addnew

import android.app.DatePickerDialog
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.detail.DetailFragment
import com.sembozdemir.scubanote.extensions.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.onFocusChange
import org.jetbrains.anko.support.v4.act
import java.util.*

class AddNewFragment : DetailFragment() {

    companion object {
        const val KEY_POSITION = "keyPosition"
    }

    override fun getLayoutResId(): Int = R.layout.fragment_detail

    override fun onReady() {
        val no = arguments[KEY_POSITION] as Int
        textViewDetailNo.text = no.plus1()

        editTextPlace.requestFocus()

        initDateInput()

    }

    private fun initDateInput() {
        val today = Calendar.getInstance()
        editTextDate.setText(today.time.asString())
        editTextDate.onFocusChange { view, focused -> if (focused) openDatePicker() }
    }

    private fun openDatePicker() {
        val date = Calendar.getInstance()

        if (editTextDate.asString().isNotEmpty()) {
            date.time = DateHelper.create(editTextDate.asString())
        }

        DatePickerDialog(act,
                DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->

                    val newDate = Calendar.getInstance()
                    newDate.set(year, month, day)
                    editTextDate.setText(newDate.time.asString())

                }, date.year, date.month, date.day)
                .show()

        editTextDept.requestFocus()
    }
}

