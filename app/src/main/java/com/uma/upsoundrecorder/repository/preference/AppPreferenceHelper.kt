package com.example.yugan.savedata.repository.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


/**
 * Created by CompIndia on 30/01/18.
 */
class AppPreferenceHelper(ctx: Context,
                          val preference: SharedPreferences? = ctx.getSharedPreferences(PreferenceKeys.preference_name, Context.MODE_PRIVATE)) {
    val pref: SharedPreferences? = ctx.getSharedPreferences(PreferenceKeys.preference_name, Context.MODE_PRIVATE)

    /**
     * set string value into the preference....
     */
    fun putString(key: String, value: String?) {
        preference?.edit {
            putString(key, value)
        }
    }

    /**
     * set int value into the preference....
     */
    fun putInt(key: String, value: Int) {
        preference?.edit {
            putInt(key, value)
        }
    }

    /**
     * set boolean into the preference....
     */
    fun putBoolean(key: String, value: Boolean) {
        preference?.edit {
            putBoolean(key, value)
        }
    }

    /**
     * get boolean into the preference....
     */
    fun getBoolean(key: String): Boolean? {
        return preference?.getBoolean(key, false)
    }

    /**
     * get boolean default true into the preference....
     */
    fun getBooleanDefaultTrue(key: String): Boolean? {
        return preference?.getBoolean(key, true)
    }

    /**
     * get string into the preference....
     */
    fun getString(key: String): String? {
        return preference?.getString(key, "")
    }

    /**
     * get int into the preference....
     */
    fun getInt(key: String): Int? {
        return preference?.getInt(key, 0)
    }

    fun putStringPermanent(key: String, value: String?) {
        pref?.edit {
            putString(key, value)
        }
    }

    fun getStringPermanent(key: String): String? {
        return pref?.getString(key, "")
    }


}