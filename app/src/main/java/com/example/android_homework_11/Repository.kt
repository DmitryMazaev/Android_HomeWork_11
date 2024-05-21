package com.example.android_homework_11

import android.content.SharedPreferences
private const val KEY_STRING_NAME = "prefs_name"
class Repository(val prefs: SharedPreferences) {
    private val editor: SharedPreferences.Editor = prefs.edit()
    var localVariable: String? = null
    /*constructor(_prefs: SharedPreferences){
        prefs = _prefs
    }*/
    fun getDataFromSharedPreference(): String? {
        return prefs.getString(KEY_STRING_NAME, "Значение не найдено")
    }
    private fun getDataFromLocalVariable(): String? {
        return localVariable
    }
    fun saveText(text: String) {
        localVariable = text
        editor.putString(KEY_STRING_NAME, text)
        editor.apply()
    }
    fun clearText() {
        localVariable = null
        editor.remove(KEY_STRING_NAME)
    }
    fun getText(): String {
        return when {
            localVariable != null -> getDataFromLocalVariable()!! + " (значение из локальной переменной)"
            getDataFromSharedPreference() != null -> getDataFromSharedPreference()!! + " (значение из репозитория)"
            else -> "Нет значений ни в локальной переменной, ни в репозитории"
        }
    }
}