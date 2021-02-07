package com.softex.gtec.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.softex.gtec.ui.loading.LoadingDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    private var dialogFragment: LoadingDialog? = null

    abstract fun initViews()

    abstract fun subscribeObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initViews()
    }

    protected fun displayLoadingDialog(isLoading: Boolean) {
        if (isLoading) {
            showDialog()
        } else {
            if (dialogFragment != null && dialogFragment!!.isVisible)
                dialogFragment!!.dismiss()
        }
    }

    private fun showDialog() {
        dialogFragment = LoadingDialog()
        dialogFragment?.show(requireActivity().supportFragmentManager, "signature")
    }
}