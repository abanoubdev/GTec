package com.softex.gtec.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.softex.gtec.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        val navDestination: NavDestination? =
            findNavController(R.id.nav_host_fragment).currentDestination
        if (navDestination != null && (navDestination.id == R.id.loginFragment)) {
            finish()
            return
        }
        super.onBackPressed()
    }
}