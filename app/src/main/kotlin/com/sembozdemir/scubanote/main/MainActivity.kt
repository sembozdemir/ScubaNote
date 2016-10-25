package com.sembozdemir.scubanote.main

import android.os.Bundle
import com.sembozdemir.scubanote.BaseActivity
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.addnew.AddNewActivity
import com.sembozdemir.scubanote.data.provider.DataProvider
import com.sembozdemir.scubanote.extensions.setIcon
import kotlinx.android.synthetic.main.layout_fab.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.onClick
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    private val dataProvider: DataProvider = DataProvider.get()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // fab
        fab.setIcon(R.drawable.ic_add)
        fab.onClick {
            startActivity<AddNewActivity>()
        }

        if (dataProvider.getDiveCount() == 0) {
            alert(R.string.main_message_first_dive,
                    R.string.main_message_first_dive_title) {
                positiveButton(android.R.string.ok) { startActivity<AddNewActivity>() }
            }.show()
        }

        addFragment(MainFragment()).commit()

    }
}
