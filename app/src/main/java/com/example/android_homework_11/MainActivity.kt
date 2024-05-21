package com.example.android_homework_11

import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.widget.addTextChangedListener
import com.example.android_homework_11.databinding.ActivityMainBinding
private const val PREFERRENCES_NAME = "prefs_name"
//private const val KEY_STRING_NAME = "prefs_name"
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = getSharedPreferences(PREFERRENCES_NAME, MODE_PRIVATE)
        val repository = Repository(prefs)
        binding.buttonSave.isEnabled = false
        binding.editText.addTextChangedListener {
            checOnEnterText()
        }
        binding.text.text = repository.getText()
        binding.buttonSave.setOnClickListener {
            repository.saveText(binding.editText.text.toString())
            binding.text.text = repository.getText()
            binding.editText.text.clear()
        }
        binding.buttonClear.setOnClickListener {
            binding.text.text = ""
            repository.clearText()
            binding.editText.text.clear()
        }
    }
    private fun checOnEnterText() {
        if (!binding.editText.text.isNullOrEmpty()) {
            binding.buttonSave.isEnabled = true
        }
        else {
            binding.buttonSave.isEnabled = false
        }
    }
}