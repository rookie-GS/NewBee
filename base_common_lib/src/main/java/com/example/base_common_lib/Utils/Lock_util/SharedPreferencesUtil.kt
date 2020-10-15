package com.github.ihsg.demo.util

import android.content.SharedPreferences
import com.example.base_common_lib.Utils.Other_Utils.getContext


/**
 * Created by hsg on 14/10/2017.
 */
internal class SharedPreferencesUtil private constructor() {
    private val prefer: SharedPreferences = getContext()
        .getSharedPreferences("test", android.content.Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefer.edit()

    fun saveString(name: String?, data: String?) {
        editor.putString(name, data)
        editor.commit()
    }

    fun getString(name: String?): String? {
        return prefer.getString(name, null)
    }

    companion object {
        @JvmStatic
        var instance: SharedPreferencesUtil? = null
            get() {
                if (field == null) {
                    synchronized(SharedPreferencesUtil::class.java) {
                        if (field == null) {
                            field = SharedPreferencesUtil()
                        }
                    }
                }
                return field
            }
            private set
    }
}