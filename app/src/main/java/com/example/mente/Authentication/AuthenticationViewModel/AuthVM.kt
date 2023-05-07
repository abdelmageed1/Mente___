package com.example.mente.Authentication.AuthenticationViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mente.Authentication.Repo.AuthRepo
import com.example.mente.Models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthVM (application: Application) : AndroidViewModel(application) {

    var authRepo = AuthRepo.getInstance()
    var mGetSpecialistUserInfo = authRepo.mGetSpecialistUser
    var mGetUserInfo = authRepo.mGetSpecialistUser

    private var successSignUpMutableLiveData = authRepo.getSuccessSignUpMutable()
    private var failureSignUpMutableLiveData = authRepo.getFailureSignUpMutable()

     var successLoginMutableLiveData  = authRepo.successLoginMutableLiveData
     var failureLoginMutableLiveData  = authRepo.getFailureLoginMutable()


    fun createNewUser(user: User) {
        authRepo.createNewUser(user)
    }

    fun initUserInfo(user: User) {
        authRepo.sendUserData(user)
    }



    // sign up
    fun getSuccessSignUpMutable() = successSignUpMutableLiveData
    fun getFailureSignUpMutable() = failureSignUpMutableLiveData



    fun login(email: String, password: String) {
        authRepo.loginRepo(email, password)
    }





    fun getSpecialistUserInfo() {
            authRepo.getUserSpecialist()
    }


    fun getUserInfo() {
            authRepo.getUser()
    }

    fun signOut() {

            authRepo.signOut()
                            }

}

