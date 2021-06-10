package com.zannardyapps.cadastroapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zannardyapps.cadastroapp.databinding.ActivityContaUserConectedBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContaUserConectedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContaUserConectedBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityContaUserConectedBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        supportActionBar!!.hide()

        GlobalScope.launch(Dispatchers.IO) {
            val valueNameUser = DataStoreMenager.getToValue(this@ContaUserConectedActivity, "name")
            val valueEmailUser = DataStoreMenager.getToValue(this@ContaUserConectedActivity, "email")

            GlobalScope.launch(Dispatchers.Main){
                binding.textEmailUser.text = valueEmailUser
                binding.textNameUser.text = valueNameUser
            }
        }

        binding.sair.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnImprimir.setOnClickListener {
            binding.StatusImprimir.text = binding.editTextStatus.text
        }

        binding.btnApagar.setOnClickListener {
            binding.StatusImprimir.text = ""
            binding.editTextStatus.setText("")

        }


    }
}