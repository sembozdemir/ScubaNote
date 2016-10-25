package com.sembozdemir.scubanote.detail

import android.os.Bundle
import android.support.v4.view.ViewPager
import com.sembozdemir.scubanote.BaseActivity
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.data.provider.DataProvider
import com.sembozdemir.scubanote.extensions.enableHomeAsUp
import com.sembozdemir.scubanote.extensions.setIcon
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.layout_fab.*
import org.jetbrains.anko.error
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.onPageChangeListener

class DetailActivity : BaseActivity() {

    companion object {
        const val EXTRA_POSITION = "position"
        const val EXTRA_TITLE = "title"
    }

    enum class MODE {
        EDIT, DONE
    }

    val dataProvider: DataProvider = DataProvider.get()

    var mode: MODE = MODE.DONE

    var position: Int = 0

    lateinit var adapter: DivePagerAdapter

    override fun getLayoutResId(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        position = intent.getIntExtra(EXTRA_POSITION, 0)
        val title = intent.getStringExtra(EXTRA_TITLE)

        // toolbar
        setTitle(title)
        enableHomeAsUp()

        setDoneMode()

        // fab
        fab.onClick {
            adapter = viewPager.adapter as DivePagerAdapter
            val fragment = adapter.getRegisteredFragment(position) as DetailFragment
            if (isDoneMode()) {
                setEditMode(fragment)
            } else if (isEditMode()) {
                try {
                    val newDive = fragment.getNewDive()
                    dataProvider.editDive(position, newDive)
                    setDoneMode(fragment)
                } catch(e: IllegalStateException) {
                    error(e.message)
                }
            }

        }

        // viewpager
        adapter = DivePagerAdapter(supportFragmentManager, dataProvider.getDives())
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 1
        viewPager.currentItem = position
        viewPager.onPageChangeListener {
            onPageSelected {
                position = it
                setTitle(adapter.getPageTitle(position))
                setDoneMode(adapter.getRegisteredFragment(position) as DetailFragment)
            }

            onPageScrollStateChanged {
                when (it) {
                    ViewPager.SCROLL_STATE_IDLE -> fab.show()
                    ViewPager.SCROLL_STATE_DRAGGING, ViewPager.SCROLL_STATE_SETTLING -> fab.hide()
                }
            }
        }

    }

    override fun onBackPressed() {

        if (isDoneMode()) {
            super.onBackPressed()
        } else {
            setDoneMode(adapter.getRegisteredFragment(position) as DetailFragment)
        }
    }

    private fun setDoneMode(fragment: DetailFragment? = null) {
        fragment?.onDoneMode()
        fab.setIcon(R.drawable.ic_edit)
        mode = MODE.DONE
    }

    private fun setEditMode(fragment: DetailFragment? = null) {
        fragment?.onEditMode()
        fab.setIcon(R.drawable.ic_done)
        mode = MODE.EDIT
    }

    private fun isDoneMode(): Boolean = mode == MODE.DONE

    private fun isEditMode(): Boolean = mode == MODE.EDIT
}