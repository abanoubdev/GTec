package com.softex.gtec.extensions

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
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

fun Fragment.snackbarLong(message: String?) {
    activity?.let {
        message?.let {
            Snackbar.make(this.requireView(), message, Snackbar.LENGTH_LONG).show()
        }
    }
}