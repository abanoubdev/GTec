package com.softex.gtec.ui.homepage

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softex.gtec.R
import com.softex.gtec.databinding.ActivityHomepageBinding
import com.softex.gtec.extensions.snackbarShort
import com.softex.gtec.model.User
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.ui.homepage.adapter.MenuAdapter
import com.softex.gtec.ui.homepage.adapter.OnCategoryClickListener
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class HomepageActivity : AppCompatActivity(), OnCategoryClickListener {

    private lateinit var binding: ActivityHomepageBinding

    private val viewModel: HomepageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.mainBottomNavigationView

        val navController = findNavController(R.id.main_nav_host)
        navView.setupWithNavController(navController)

        initViews()

        subscribeObservers()

        viewModel.setStateEvent(HomepageStateEvent.GetData)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->
            val tvNavigationUsername = findViewById<TextView>(R.id.tvNavigationUsername)
            val tvNavigationEmail = findViewById<TextView>(R.id.tvNavigationEmail)

            when (dataState) {
                is DataState.Success<User?> -> {
                    tvNavigationUsername.text = dataState.data?.CustomerName
                    tvNavigationEmail.text = dataState.data?.CustomerName
                }
                else -> {

                }
            }
        })

        viewModel.menuItemsDataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<List<NavigationMenuResponseItem>?> -> {
                    val recyclerMenu = findViewById<RecyclerView>(R.id.recyclerMenuItems)
                    recyclerMenu.layoutManager = LinearLayoutManager(applicationContext)
                    recyclerMenu.isNestedScrollingEnabled = true
                    recyclerMenu.setHasFixedSize(true)
                    recyclerMenu.adapter = MenuAdapter(
                        recyclerMenu.layoutManager as LinearLayoutManager,
                        dataState.data!!, this@HomepageActivity
                    )
                }

                is DataState.Error -> {
                    snackbarShort(dataState.exception)
                }

                is DataState.Loading -> {
                    Log.d("Loading", "Loading")
                }
            }
        })
    }

    private fun initViews() {
        val ivMenu = findViewById<ImageView>(R.id.ivMenu)
        val ivBack = findViewById<ImageView>(R.id.ivBack)
        val ivLogoHeader = findViewById<ImageView>(R.id.ivLogo)
        ivMenu.setOnClickListener {
            openDrawer()
        }
        ivLogoHeader.setOnClickListener {
            openDrawer()
        }
        ivBack.setOnClickListener {
            closeDrawer()
        }
    }

    private fun closeDrawer() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START))
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun openDrawer() {
        if (!binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START))
            binding.mainDrawerLayout.openDrawer(GravityCompat.START)
        else
            binding.mainDrawerLayout.closeDrawer(GravityCompat.END)
    }

    override fun onCategoryClicked(categoryId: String) {
        closeDrawer()
    }
}