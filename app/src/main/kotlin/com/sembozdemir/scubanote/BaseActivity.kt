package com.sembozdemir.scubanote

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.IntegerRes
import android.support.annotation.LayoutRes
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.AnkoLogger

abstract class BaseActivity : AppCompatActivity(), AnkoLogger {

    @CallSuper override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        setSupportActionBar(toolbar)
    }

    @LayoutRes abstract fun getLayoutResId(): Int

    protected fun replaceFragment(fragment: BaseFragment,
                                  tag: String = fragment.getDefaultTag(),
                                  addToBackStack: Boolean = false): FragmentTransaction {

        val transaction = supportFragmentManager.beginTransaction()
                .replace(getContainerResId(), fragment, tag)

        if (addToBackStack) transaction.addToBackStack(tag)

        return transaction
    }

    protected fun addFragment(fragment: BaseFragment,
                              tag: String = fragment.getDefaultTag(),
                              addToBackStack: Boolean = false): FragmentTransaction {

        val transaction = supportFragmentManager.beginTransaction()
                .add(getContainerResId(), fragment, tag)

        if (addToBackStack) transaction.addToBackStack(tag)

        return transaction
    }

    protected fun getFragment(tag: String): BaseFragment {
        return supportFragmentManager.findFragmentByTag(tag) as BaseFragment
    }

    /**
     * Get container resource id.
     *
     * Default value is R.id.container. Override this, if you need to change your container id.
     * If you do not have any container and you do not need to use fragments, do not use this method and
     * fragment operation methods @see [replaceFragment], [addFragment]
     *
     * @return container resource id of activity layout, default value is R.id.container
     */
    @IntegerRes open fun getContainerResId(): Int = R.id.container

}
