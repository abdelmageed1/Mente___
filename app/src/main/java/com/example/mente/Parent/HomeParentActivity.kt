package com.example.mente.Parent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mente.Authentication.Ui.Login
import com.example.mente.R
import com.example.mente.databinding.ActivityHomeParentBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class HomeParentActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeParentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeParentBinding.inflate(layoutInflater)
        setContentView(binding.root)

         setAppBarAndNavigationDrawer()

        window.statusBarColor = ContextCompat.getColor(this,R.color.base_color)


    }





    private fun setAppBarAndNavigationDrawer() {

        setSupportActionBar(binding.appBarHomeParent.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home_parent)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home_parent, R.id.nav_about_parent  , R.id.nav_setting_parent,R.id.historyParentFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.menu.findItem(R.id.nav_setting_parent).setOnMenuItemClickListener {
            signOut()
            true
        }

    }

    private fun signOut() {
        startActivity(Intent(this, Login::class.java))
        this.finish()
        Firebase.auth.signOut()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home_parent)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}