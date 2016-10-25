package com.sembozdemir.scubanote.main

import android.support.v7.widget.LinearLayoutManager
import com.sembozdemir.scubanote.BaseFragment
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.data.provider.DataProvider
import com.sembozdemir.scubanote.detail.DetailActivity
import com.sembozdemir.scubanote.views.LineDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.startActivity

class MainFragment : BaseFragment() {

    private val dataProvider: DataProvider = DataProvider.get()

    override fun getLayoutResId(): Int = R.layout.fragment_main

    private lateinit var diveListAdapter: DiveListAdapter

    override fun onReady() {
        recyclerViewDives.layoutManager = LinearLayoutManager(act)
        recyclerViewDives.addItemDecoration(LineDividerItemDecoration(act))
        diveListAdapter = DiveListAdapter(dataProvider.getDives(), {
            position, title -> startActivity<DetailActivity>(DetailActivity.EXTRA_POSITION to position,
                    DetailActivity.EXTRA_TITLE to title)
        })
        recyclerViewDives.adapter = diveListAdapter
    }

    override fun onResume() {
        super.onResume()
        diveListAdapter.refresh(dataProvider.getDives())
    }
}