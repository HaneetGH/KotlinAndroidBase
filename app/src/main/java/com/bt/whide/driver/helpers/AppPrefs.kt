package com.bt.whide.driver.helpers

import android.content.SharedPreferences
import javax.inject.Inject

class AppPrefs @Inject  constructor( private val pref: SharedPreferences?) {
    var IS_LOGIN = "is_login"

    var isLogin: Boolean
        get() = pref!!.getBoolean(IS_LOGIN, false)
        set(value) {
            pref?.edit()?.putBoolean(IS_LOGIN, value)?.apply()
        }


}
