package com.example.mente.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class User  (
    var userId   : String ="",
    var fName    : String ="" ,
    var lName    : String ="",
    var email    :String ="" ,
    var password :String ="" ,
    var type     :String = "" ,


                ): Parcelable


