package com.sembozdemir.scubanote.detail

import com.sembozdemir.scubanote.BaseFragment
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.data.model.Dive
import com.sembozdemir.scubanote.data.provider.DataProvider
import com.sembozdemir.scubanote.extensions.*
import kotlinx.android.synthetic.main.fragment_detail.*

open class DetailFragment : BaseFragment() {

    companion object {
        const val KEY_POSITION = "keyPosition"
    }

    private val dataProvider: DataProvider = DataProvider.get()

    override fun getLayoutResId(): Int = R.layout.fragment_detail

    private var no: Int = 0

    override fun onReady() {
        no = arguments[KEY_POSITION] as Int

        initInfo(no)
    }

    private fun initInfo(no: Int) {
        val dive = dataProvider.getDive(no)

        textViewDetailNo.text = no.plus1()

        rootLayout.enableEditTexts(false)

        editTextDate.setText(dive.date.asString())
        editTextPlace.setText(dive.name)
        editTextDept.setText(dive.dept.toString())
        editTextMins.setText(dive.mins.toString())
        editTextStartBar.setText(dive.startBar.toString())
        editTextEndBar.setText(dive.endBar.toString())
        editTextComments.setText(dive.comments)
        radioGroupBoatShore.check(if (dive.type == Dive.BOAT) radioButtonBoat.id else radioButtonShore.id)
        radioGroupBoatShore.enable(false)
    }

    fun onEditMode() {
        rootLayout.enableEditTexts(true)
        with(editTextPlace) {
            requestFocus()
            moveCursorEnd()
        }
        radioGroupBoatShore.enable(true)
    }

    fun onDoneMode() {
        initInfo(no)
        rootLayout.enableEditTexts(false)
        rootLayout.requestFocus()
        radioGroupBoatShore.enable(false)
    }

    fun getNewDive(): Dive {

        var isValid = true
        val name = editTextPlace.asString()
        val dept = editTextDept.asString()
        val mins = editTextMins.asString()

        if (name.isEmpty()) {
            editTextPlaceWrapper.error = getString(R.string.detail_error_blank_input_place)
            isValid = false
        }

        if (dept.isEmpty()) {
            editTextDeptWrapper.error = getString(R.string.detail_error_blank_input_short)
            isValid = false
        }

        if (mins.isEmpty()) {
            editTextMinsWrapper.error = getString(R.string.detail_error_blank_input_short)
            isValid = false
        }

        if (!isValid) throw error("Blank input values")

        val type: String =
                if (radioButtonBoat.id == radioGroupBoatShore.checkedRadioButtonId)
                    Dive.BOAT
                else
                    Dive.SHORE

        return Dive(
                editTextPlace.asString(),
                DateHelper.create(editTextDate.text.toString()),
                editTextDept.asString(),
                editTextMins.asString(),
                editTextStartBar.asString(),
                editTextEndBar.asString(),
                type,
                editTextComments.asString())
    }
}