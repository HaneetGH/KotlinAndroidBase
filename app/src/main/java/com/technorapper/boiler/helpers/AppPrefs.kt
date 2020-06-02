package com.technorapper.boiler.helpers

import android.content.SharedPreferences
import javax.inject.Inject

class AppPrefs @Inject constructor(private val pref: SharedPreferences?) {
    var IS_LOGIN = "is_login"
    var TOKEN_ = "user_token"

    var isLogin: Boolean
        get() = pref!!.getBoolean(IS_LOGIN, false)
        set(value) {
            pref?.edit()?.putBoolean(IS_LOGIN, value)?.apply()
        }

    var TOKEN: String?
        get() = pref!!.getString(TOKEN_, "")
        set(value) {
            pref?.edit()?.putString(TOKEN_, value)?.apply()
        }


}
