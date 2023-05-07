package com.example.mente.Specialist


import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mente.Authentication.AuthenticationViewModel.AuthVM
import com.example.mente.Authentication.Ui.Login
import com.example.mente.R
import com.example.mente.databinding.ActivityHomeSpecialistBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeSpecialistActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeSpecialistBinding
    lateinit var authVM: AuthVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeSpecialistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authVM = ViewModelProvider(this)[AuthVM::class.java]
        authVM.getUserInfo()

        // set Navigation Drawer
        setAppBarAndNavigationDrawer()

        setUserNameInNavHeader()

        window.statusBarColor = ContextCompat.getColor(this,R.color.base_color)


    }


    private fun setUserNameInNavHeader() {

        var n = binding.navView.getHeaderView(0)
        var userName = n.findViewById<TextView>(R.id.tvUserNameInDrawer)
        var userEmail = n.findViewById<TextView>(R.id.tvUserEmailInDrawer)
        authVM.mGetUserInfo.observe(this) {
            if (it != null)
            {
                userName.text = "${it.fName.capitalize()} ${it.lName.capitalize()}"
                userEmail.text = " ${it.email}"
            }


        }

    }


    private fun setAppBarAndNavigationDrawer() {
        setSupportActionBar(binding.appBarHomeSpecialist.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home_specialist)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.aboutUsFragment, R.id.connectUsFragment, R.id.ratingFragment

            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.menu.findItem(R.id.settingFragment).setOnMenuItemClickListener {
            signOut()
            true
        }

    }

    private fun signOut(){
        startActivity(Intent(this, Login::class.java))
        this.finish()
        Firebase.auth.signOut()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_specialist)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}