package com.example.mente.Student.Repo

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.mente.Models.Quiz
import com.example.mente.Student.Model.Student
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RepoStudent {
    private var auth = Firebase.auth
    var refStudent = Firebase.database.reference.child("Users")
        .child("${auth.currentUser?.uid}").child("Students")

    var refStudentInParent =  Firebase.database.reference.child("Users")
        .child("${auth.currentUser?.uid}").child("Student")

    var addedStudentSuccess = MutableLiveData<Boolean>()
    var addedStudentFailure = MutableLiveData<String>()
     var studentAddedNow =Student()
    var mStudent = MutableLiveData<MutableList<Student>>()

     var mSetQuizSuccess = MutableLiveData<Boolean>()
     var mSetQuizParentSuccess = MutableLiveData<Boolean>()

    var mSetQuizFailure = MutableLiveData<String>()
    var mSetQuizParentFailure = MutableLiveData<String>()




    @SuppressLint("SuspiciousIndentation")
    fun addStudentInfo(student: Student) {

       var path =  refStudent.push()
          student.key = path.key!!
         studentAddedNow = student
             path.setValue(student).addOnCompleteListener {
               addedStudentSuccess.value = it.isSuccessful
             }
                 .addOnFailureListener {
                  addedStudentFailure.value = it.message
                 }

           }

    fun getStudentInSpecialist() {

        var students = mutableListOf<Student>()
        refStudent.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    students.add(child.getValue(Student::class.java)!! )
                }
                mStudent.postValue(students)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }  )
    }

    fun setQuizResult(quiz: Quiz , student :Student){
        refStudent.child(student.key).child("Quizzes").child(quiz.categoryQuiz).child(quiz.nameQuiz).setValue(quiz)
            .addOnCompleteListener {
                mSetQuizSuccess.value = it.isSuccessful
            }
            .addOnFailureListener  {
                mSetQuizFailure.value = it.message
            }

    }

  fun setQuizResultInParent(quiz: Quiz){
    refStudentInParent.child(quiz.nameQuiz).setValue(quiz)
        .addOnCompleteListener {
        mSetQuizParentSuccess.value = it.isSuccessful
    }
        .addOnFailureListener  {
            mSetQuizParentFailure.value = it.message
        }
  }


    fun removeStudent(studentId: String){
     refStudent.child(studentId).removeValue()
    }



    companion object
    {
        private  var instance : RepoStudent? = null

        fun getInstance():RepoStudent{
            if (instance == null)
            {
                instance = RepoStudent()
            }
            return instance!!
        }
    }



}