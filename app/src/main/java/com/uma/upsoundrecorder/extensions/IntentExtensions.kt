package com.uma.upsoundrecorder.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by satheeshkumar on 31/01/18.
 * Copyright Indyzen Inc, 2018
 */
/**
 * Inline function to redirect another screen and waiting for result
 */
inline fun <reified T : Any> Activity.launchActivityForResult(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}

/**
 * Inline function to redirect another screen
 */
inline fun <reified T : Any> Activity.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
    this.finish()
}

/**
 * Inline function to redirect another screen without finish the current activity.
 */
inline fun <reified T : Any> Activity.launchActivityNotFinish(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

/**
 * Inline function to build intent to the result activity
 */
inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

//inline fun AppCompatActivity.attachFragment(id: Int, boolean: Boolean, fragment: Fragment,
//                                            noinline init: AppCompatActivity.() -> Unit = {}) {
//    supportFragmentManager
//}


@SuppressLint("MissingPermission")
        /**
 * Extenstion function to call
 */
fun Activity.call(mobileNo: String?) {
    val CALL_PERMISSION_REQUEST = 100

    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), CALL_PERMISSION_REQUEST)
    } else {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("""tel:$mobileNo"""))
        startActivity(intent)
    }
}

/**
 * Inline function to redirect another screen and waiting for result.
 * By finishing the current result
 */
inline fun <reified T : Any> Activity.launchActivityForResultWithFinish(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
        finish()
    } else {
        startActivityForResult(intent, requestCode)
        finish()
    }


}
