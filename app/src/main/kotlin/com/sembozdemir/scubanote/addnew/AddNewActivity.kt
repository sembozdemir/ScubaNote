package com.sembozdemir.scubanote.addnew

import android.os.Bundle
import com.sembozdemir.scubanote.BaseActivity
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.data.provider.DataProvider
import com.sembozdemir.scubanote.extensions.enableHomeAsUp
import com.sembozdemir.scubanote.extensions.setIcon
import kotlinx.android.synthetic.main.layout_fab.*
import org.jetbrains.anko.error
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.withArguments

class AddNewActivity : BaseActivity() {

    private val dataProvider: DataProvider = DataProvider.get()

    private val fragmentTag = "addNewFragment"

    override fun getLayoutResId(): Int = R.layout.activity_addnew

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // title
        setTitle(R.string.addnew_title)
        enableHomeAsUp()

        // fab
        fab.setIcon(R.drawable.ic_done)
        fab.onClick {
            val fragment = getFragment(fragmentTag) as AddNewFragment
            try {
                val newDive = fragment.getNewDive()
                dataProvider.addDive(newDive)
                finish()
            } catch(e: IllegalStateException) {
                error(e.message)
            }
        }

        addFragment(AddNewFragment()
                .withArguments(AddNewFragment.KEY_POSITION to dataProvider.getDiveCount()),
                fragmentTag)
                .commit()
    }
}