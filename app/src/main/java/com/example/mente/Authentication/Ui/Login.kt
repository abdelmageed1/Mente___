package com.example.mente.Authentication.Ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mente.Authentication.AuthenticationViewModel.AuthVM
import com.example.mente.Models.User
import com.example.mente.Parent.HomeParentActivity
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.constant
import com.example.mente.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthVM
    var user = User()
    private val auth: FirebaseAuth = Firebase.auth
    private var database = Firebase.database

//    override fun onStart() {
//        super.onStart()
//
//        binding.signLogo.animate().scaleXBy(1f).duration = 2000
//
//
//    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.base_color)

        authViewModel = ViewModelProvider(this)[AuthVM::class.java]

        authViewModel.getUserInfo()





           binding.btnLogin.setOnClickListener {

            hideBtnSignUp()
            showProgress()
             login()

              // setUser()

            //  setSuccessLoginObserve()
              setFailureLoginObserve()
        }





        binding.tvCreateAccount.setOnClickListener {
            startActivity(Intent(applicationContext, SignUp::class.java))
            finish()
        }
    }


    private fun setSuccessLoginObserve() {
        authViewModel.successLoginMutableLiveData.observe(this) {
            if (it != null)
            {

                database.reference.child("Users").child(auth.currentUser?.uid.toString())
                    .child("UserInfo").child("type").addValueEventListener(object :
                        ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if (snapshot.value == constant.userTypeSpecialist)
                            {
                                goToHomeSpecialist()
                            }
                            else{
                                goToHomeParent()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {

                        }
                    })

        }

    }
    }



    private fun setFailureLoginObserve() {
        authViewModel.failureLoginMutableLiveData.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()
        }
    }


    private fun login() {

        var email = binding.etLoginEmail.text.toString().trim()
        var pass = binding.etLoginPassword.text.toString().trim()
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            authViewModel.login(email, pass)
            authViewModel.getUserInfo()
              setSuccessLoginObserve()

        } else {
            Toast.makeText(
                applicationContext,
                "complete_this_fields",
                Toast.LENGTH_SHORT
            ).show()
            hideProgress()
            showBtnSignUp()
        }
    }

   private fun setUser()
     {
         authViewModel.mGetUserInfo.observe(this) {
             user = it
         }
    }

    private fun goToHomeParent(){
        startActivity(Intent(applicationContext, HomeParentActivity::class.java))
        finish()
    }
    private fun goToHomeSpecialist(){
        startActivity(Intent(applicationContext, HomeSpecialistActivity::class.java))
        finish()
    }

    private fun showProgress() {
        binding.progressLogin.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressLogin.visibility = View.GONE
    }

    private fun showBtnSignUp() {
        binding.btnLogin.visibility = View.VISIBLE
    }

    private fun hideBtnSignUp() {
        binding.btnLogin.visibility = View.GONE
    }


}








