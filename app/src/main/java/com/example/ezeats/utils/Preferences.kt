package com.example.ezeats.utils

import android.content.Context
import android.content.SharedPreferences

object Preferences {
    fun initPref(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
    private fun editorPreference(context: Context, name: String): SharedPreferences.Editor {
        val sharedPref = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        return sharedPref.edit()
    }
    fun saveToken(token: String, context: Context) {
        val editor = editorPreference(context, "onSignIn")
        editor.putString("token", token)
        editor.apply()
    }

    fun logout(context: Context){
        val editor = editorPreference(context, "onSignIn")
        editor.remove("token")
        editor.remove("status")
        editor.apply()
    }
}