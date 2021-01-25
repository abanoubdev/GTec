package com.softex.gtec.extensions

import android.util.Log
import androidx.fragment.app.Fragment

fun Fragment.log(tag: String, message: String) {
    Log.d(tag, message)
}