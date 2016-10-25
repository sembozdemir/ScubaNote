package com.sembozdemir.scubanote.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import com.sembozdemir.scubanote.data.model.Dive
import org.jetbrains.anko.support.v4.withArguments

class DivePagerAdapter(fragmentManager: FragmentManager, val diveList: List<Dive>) :
        FragmentStatePagerAdapter(fragmentManager) {

    private val registeredFragments = SparseArray<Fragment>()

    override fun getCount(): Int = diveList.size

    override fun getPageTitle(position: Int): CharSequence = diveList[position].name.toString()

    override fun getItem(position: Int): Fragment =
            DetailFragment().withArguments(DetailFragment.KEY_POSITION to position)

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any?) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, obj)
    }

    fun getRegisteredFragment(position: Int): Fragment = registeredFragments.get(position)
}