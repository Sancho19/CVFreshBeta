package com.st10083248.firebasedemo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.st10083248.firebasedemo.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.tvLogin
        binding.btnSignUp.setOnClickListener{

            val email = binding.etxtEmail.text.toString()
            val pass = binding.etxtPassword.text.toString()
            val confirmpass = binding.etxtRePassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmpass.isNotEmpty())
            {
                if(pass == confirmpass)
                {
                   firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener{
                       if(it.isSuccessful){
                           val intent = Intent(this, SignInActivity::class.java)
                           startActivity(intent)

                       }else{
                           Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                       }
                   }
                }

                else{
                    Toast.makeText(this, "Password is not matching!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Ensure all fields are filled!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}