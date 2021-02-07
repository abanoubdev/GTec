package com.softex.gtec.ui.loading

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentLoadingBinding

class LoadingDialog : DialogFragment() {

    private var isDialogAttached: Boolean = true

    private var _binding: FragmentLoadingBinding? = null

    private val binding get() = _binding!!

    override fun getTheme(): Int {
        return R.style.DialogTheme
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        isCancelable = false
        _binding = FragmentLoadingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDetach() {
        isDialogAttached = false
        _binding = null
        super.onDetach()
    }
}