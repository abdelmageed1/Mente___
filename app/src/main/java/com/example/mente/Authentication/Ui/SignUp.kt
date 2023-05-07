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
import com.example.mente.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {
    private  var user: User = User()
    private lateinit var binding: ActivitySignUpBinding
     private lateinit var authViewModel: AuthVM

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this,R.color.base_color)

        authViewModel = ViewModelProvider(this)[AuthVM::class.java]

//        binding.signLogo.animate().scaleXBy(1f).duration = 2000





        binding.btnSignup.setOnClickListener {

            hideBtnSignUp()
            showProgress()
            signUpAndSendData()

            setSuccessObserver()
            setFailureObserver()

        }

        binding.tvGotoLogin.setOnClickListener {
            startActivity(Intent(applicationContext, Login::class.java))
            finish()
        }


    }


     private fun setSuccessObserver() {
         authViewModel.getSuccessSignUpMutable().observe(this) {
            if (it != null) {
                Toast.makeText(applicationContext, "create success ", Toast.LENGTH_SHORT).show()
                authViewModel.initUserInfo(user)

                if (user.type == constant.userTypeSpecialist)
                {
                    startActivity(Intent(this, HomeSpecialistActivity::class.java))
                    finish()
                }
                else if (user.type == constant.userTypeParent)
                {
                    startActivity(Intent(this, HomeParentActivity::class.java))
                    finish()
                }

            }


        }
    }

    private fun setFailureObserver() {
        authViewModel = ViewModelProvider(this)[AuthVM::class.java]
        authViewModel.getFailureSignUpMutable().observe(this) {
            Toast.makeText(applicationContext, "${it.toString()}", Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()
        }
    }


    private fun signUpAndSendData() {

        var fName = binding.etSignupFname.text.toString().trimStart().trimEnd()
        var lName = binding.etSignupLname.text.toString().trimStart().trimEnd()
        var email = binding.etSignupMail.text.toString().trimStart().trimEnd()
        var password = binding.etSignupPassword.text.toString().trimStart().trimEnd()
        var passwordConfirm = binding.etSignupPasswordConfirm.text.toString().trimStart().trimEnd()



        var typeUser = getUserType()

        if (  email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty() && fName.isNotEmpty()
              && lName.isNotEmpty()   && typeUser != null

            )
        {
              if (password == passwordConfirm)
              {
                  user = User("",fName=fName, lName = lName, email =email, password=password , type = typeUser)
                  authViewModel.createNewUser(user)

              }
            else
              {
                  Toast.makeText(applicationContext,
                      " كلمة السر غير متطايقة",
                      Toast.LENGTH_SHORT).show()
                  hideProgress()
                  showBtnSignUp()
              }


        } else {
            Toast.makeText(applicationContext,
                " complete this fields",
                Toast.LENGTH_SHORT).show()
            hideProgress()
            showBtnSignUp()

        }

    }

    @SuppressLint("SuspiciousIndentation")
    private fun getUserType(): String? {

        var radioSelectedId = binding.radioGroupGender.checkedRadioButtonId

        return when (radioSelectedId) {
            R.id.rb_btm_specialist -> "specialist"
            R.id.rb_btm_parent -> "parent"
            else -> null
        }

    }

    private fun showProgress() {
        binding.progressSignup.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        binding.progressSignup.visibility = View.GONE
    }

    private fun showBtnSignUp() {
        binding.btnSignup.visibility = View.VISIBLE
    }

    private fun hideBtnSignUp() {
        binding.btnSignup.visibility = View.GONE
    }


}

