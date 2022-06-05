package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var binding:ActivitySettingBinding
    lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initLayout()
    }

    private fun initLayout() {
        dbHelper = DBHelper(this, "dysw.db", null, 1)
        binding.apply {
            SettingUpdateBtn.setOnClickListener {
                val age = binding.SettingAgechangeEt.text.toString().toInt()
                val sleeptime = binding.SettingSleeptimechangeEt.text.toString().toInt()
                dbHelper.updateUserData(age, sleeptime)
                finish()
            }
        }
    }
}