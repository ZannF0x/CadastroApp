package com.zannardyapps.cadastroapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zannardyapps.cadastroapp.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    var cadastros = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCadastrar.setOnClickListener {

            // cadastrar
            if(checarInfo()){
                val user = User(
                    binding.edtNome.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtSenha.text.toString()
                )
                Toast.makeText(this, "Usuário ${binding.edtNome.text} Cadastrado!", Toast.LENGTH_LONG).show()
                cadastros.add(user)

                binding.edtNome.setText("")
                binding.edtEmail.setText("")
                binding.edtSenha.setText("")

            }
        }

        binding.btnLogar.setOnClickListener{

           val check = checarInfo()

           // logar
           if(check){
                val user = User(
                    binding.edtNome.text.toString(),
                    binding.edtEmail.text.toString(),
                    binding.edtSenha.text.toString()
                )
                val contains = cadastros.contains(user)

                if(contains){
                    GlobalScope.launch {

                        DataStoreMenager.saveData(this@MainActivity, "name", binding.edtNome.text.toString())
                        DataStoreMenager.saveData(this@MainActivity, "email", binding.edtEmail.text.toString())
                        DataStoreMenager.saveData(this@MainActivity, "senha", binding.edtEmail.text.toString())

                    }

                    val intent = Intent(this, ContaUserConectedActivity::class.java)
                    startActivity(intent)
                    finish()

                }else{
                    binding.edtNome.setText("")
                    binding.edtEmail.setText("")
                    binding.edtSenha.setText("")
                    Toast.makeText(this, "E-Mail ou Senha incorreto!\nTente novamente.", Toast.LENGTH_LONG).show()
                }
           }
        }
    }


    private fun checarInfo(): Boolean{


            if (binding.edtNome.text.isEmpty()){
                binding.edtNome.error = "ERRO - Digite seu Nome."
            }
            else if (binding.edtEmail.text.isEmpty()){
                binding.edtEmail.error = "ERRO - Digite seu E-Mail."
            }
            else if (binding.edtSenha.text.isEmpty()){
                binding.edtSenha.error = "ERRO - Digite sua Senha."
            }
            else if (binding.edtEmail.text !in "@gmail.com" && binding.edtSenha.text.length < 8) {
                binding.edtEmail.setText("")
                binding.edtSenha.setText("")
                Toast.makeText(this, "E-Mail ou Senha Invalido!\nTente novamente.", Toast.LENGTH_LONG).show()
            }
            else{
                // tudo certo
                return true
            }
        // errado
        return false
    }

}