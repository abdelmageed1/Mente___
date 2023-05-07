package com.example.mente

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mente.Authentication.AuthenticationViewModel.AuthVM
import com.example.mente.Authentication.Repo.AuthRepo
import com.example.mente.Authentication.Ui.Login
import com.example.mente.Parent.HomeParentActivity
import com.example.mente.Specialist.HomeSpecialistActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val auth: FirebaseAuth = Firebase.auth
    private var database = Firebase.database
    private lateinit var authViewModel: AuthVM

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = ContextCompat.getColor(this, R.color.base_color)

        codeMain()


    }


    private fun codeMain() {
        authViewModel = ViewModelProvider(this)[AuthVM::class.java]
        authViewModel.getUserInfo()

        var currentUser = AuthRepo.getInstance().getCurrentUser()


        if (currentUser == null) {
            goToLogin()
        } else {
            database.reference.child("Users").child(auth.currentUser?.uid.toString())
                .child("UserInfo").child("type").addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.value == constant.userTypeSpecialist) {
                            goToHomeSpecialist()
                        } else {
                            goToHomeParent()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }
                })


        }
    }


    private fun goToLogin() {
        var intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToHomeSpecialist() {
        var intent = Intent(this, HomeSpecialistActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToHomeParent() {
        var intent = Intent(this, HomeParentActivity::class.java)
        startActivity(intent)
        finish()
    }


}
