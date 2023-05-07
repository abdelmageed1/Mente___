package com.example.mente.Authentication.Repo

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mente.Models.User
import com.example.mente.constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthRepo() {

    private val auth: FirebaseAuth = Firebase.auth
    private var database = Firebase.database

        // sign in
    private var successUserSignUpMutableLiveData = MutableLiveData<FirebaseUser>()
    private var failureSignUpMutableLiveData = MutableLiveData<String>()

       // login
      var successLoginMutableLiveData = MutableLiveData<FirebaseUser>()
    private var failureLoginMutableLiveData = MutableLiveData<String>()



   // lateinit var user: User
     var mGetSpecialistUser = MutableLiveData<User>()
     var mGetUser = MutableLiveData<User>()

    fun createNewUser(user: User) {

        auth.createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    successUserSignUpMutableLiveData.postValue(auth.currentUser)
                }
            }
            .addOnFailureListener {
                failureSignUpMutableLiveData.postValue(it.message)
            }
    }


    @SuppressLint("SuspiciousIndentation")
    fun sendUserData(user: User) {
        user.userId = auth.currentUser!!.uid
        var refUser = database.getReference("Users")
            refUser.child(auth.currentUser!!.uid).child("UserInfo").setValue(user)


    }



    fun loginRepo(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    successLoginMutableLiveData.postValue(auth.currentUser)
                }
            }
            .addOnFailureListener {
                failureLoginMutableLiveData.postValue(it.message)
            }
    }

    fun signOut() {
        Firebase.auth.signOut()
    }

  fun getUserSpecialist() {

        database.reference.child("Users").child(constant.userTypeSpecialist).child(auth.currentUser?.uid.toString())
            .child("UserInfo").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mGetSpecialistUser.postValue(snapshot.getValue<User>(User::class.java))
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

    }
 fun getUser() {
//// Do not Use Specify type  Parent or Specialist
        database.reference.child("Users").child(auth.currentUser?.uid.toString())
            .child("UserInfo").addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    mGetSpecialistUser.postValue(snapshot.getValue<User>(User::class.java))
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

    }



    fun getSuccessSignUpMutable() = successUserSignUpMutableLiveData
    fun getFailureSignUpMutable() = failureSignUpMutableLiveData

    fun getFailureLoginMutable() = failureLoginMutableLiveData



    fun getCurrentUser() = Firebase.auth.currentUser



    companion object {
     private  var instance : AuthRepo? = null


        fun getInstance():AuthRepo
        {
            if (instance == null )
            {
                instance = AuthRepo()
            }
            return  instance!!

        }
    }

}