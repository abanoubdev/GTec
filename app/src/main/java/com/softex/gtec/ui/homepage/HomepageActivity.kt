package com.softex.gtec.ui.homepage

import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.softex.gtec.R
import com.softex.gtec.databinding.ActivityHomepageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomepageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.mainBottomNavigationView

        val navController = findNavController(R.id.main_nav_host)
        navView.setupWithNavController(navController)

        initViews()
    }

    private fun initViews() {
        val ivMenu = findViewById<ImageView>(R.id.ivMenu)
        val ivLogoHeader = findViewById<ImageView>(R.id.ivLogo)
        ivMenu.setOnClickListener {
            openDrawer()
        }
        ivLogoHeader.setOnClickListener {
            openDrawer()
        }
    }

    private fun openDrawer() {
        if (!binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START))
            binding.mainDrawerLayout.openDrawer(GravityCompat.START)
        else
            binding.mainDrawerLayout.closeDrawer(GravityCompat.END)
    }
}