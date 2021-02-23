package com.softex.gtec.extensions

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.softex.gtec.ui.homepage.HomepageActivity
import com.softex.gtec.util.SaltEncryption
import java.lang.Exception

fun log(tag: String, message: String) {
    Log.d(tag, message)
}

fun Fragment.snackbarShort(message: String?) {
    activity?.let {
        message?.let {
            Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).show()
        }
    }
}

fun Fragment.snackbarShort(exception: Exception?) {
    activity?.let {
        exception?.let {
            Snackbar.make(this.requireView(), it.message!!, Snackbar.LENGTH_SHORT).show()
        }
    }
}

fun Fragment.startHomepage() {
    val intent = Intent(requireActivity(), HomepageActivity::class.java)
    startActivity(intent)
    requireActivity().finish()
}

fun Fragment.snackbarLong(message: String?) {
    activity?.let {
        message?.let {
            Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).show()
        }
    }
}

fun String.isEmpty(): Boolean {
    return TextUtils.isEmpty(this)
}

fun String.encrypt(): String {
    return SaltEncryption.encrypt(this)
}
