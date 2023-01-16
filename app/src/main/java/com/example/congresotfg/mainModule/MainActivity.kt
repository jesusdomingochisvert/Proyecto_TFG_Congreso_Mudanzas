package com.example.congresotfg.mainModule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.congresotfg.LoginModule.LoginActivity
import com.example.congresotfg.R
import com.example.congresotfg.databinding.MainActivityBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: MainActivityBinding

    private lateinit var drawer: DrawerLayout

    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var navDrawer: NavigationView

    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        toolbar = binding.customToolbar.toolbarMain
        bottomNav = binding.bnv
        drawer = binding.drawerMain
        navController = findNavController(R.id.frame_main)
        navDrawer = binding.nvMain

        setSupportActionBar(toolbar)

        bottomNav.selectedItemId = R.id.menu_home

        bottomNav.setupWithNavController(navController)

        moveFragments()

        appBarConfiguration = AppBarConfiguration(navController.graph, drawer)

        NavigationUI.setupActionBarWithNavController(this, navController, drawer)

        NavigationUI.setupWithNavController(navDrawer, navController)

        navDrawer.setNavigationItemSelectedListener(this)

    }

    private fun moveFragments() {

        binding.bnv.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.menu_location -> {

                    navController.navigate(R.id.location)

                    binding.customToolbar.txtTitleToolbar.text = "Location"

                    Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show()

                }

                R.id.menu_home -> {

                    navController.navigate(R.id.home)

                    binding.customToolbar.txtTitleToolbar.text = "Home"

                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()

                }

                R.id.menu_like -> {

                    navController.navigate(R.id.like)

                    binding.customToolbar.txtTitleToolbar.text = "Like"

                    Toast.makeText(this, "Like", Toast.LENGTH_SHORT).show()

                }

                else -> {}

            }

            true

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_toolbar, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.menu_log_out -> {

                val intent = Intent(this, LoginActivity::class.java)

                Toast.makeText(this, "Cerrando Sesión...", Toast.LENGTH_SHORT).show()

                startActivity(intent)

            }

            R.id.menu_dark_mode -> Toast.makeText(this, "Modo Oscuro", Toast.LENGTH_SHORT).show()

        }

        return super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.drawer_contact -> {

                navController.navigate(R.id.contact)

                binding.customToolbar.txtTitleToolbar.text = "Contact"

                Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show()

            }

            R.id.drawer_about -> {

                navController.navigate(R.id.about)

                binding.customToolbar.txtTitleToolbar.text = "About"

                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show()

            }

            R.id.drawer_my_tickets -> {

                navController.navigate(R.id.my_tickets)

                binding.customToolbar.txtTitleToolbar.text = "My Tickets"

                Toast.makeText(this, "My Tickets", Toast.LENGTH_SHORT).show()

            }

            R.id.drawer_friends -> {
                Toast.makeText(this, "Friends", Toast.LENGTH_SHORT).show()
            }

            R.id.drawer_my_events -> {

                navController.navigate(R.id.my_events)

                binding.customToolbar.txtTitleToolbar.text = "My List"

                Toast.makeText(this, "My List", Toast.LENGTH_SHORT).show()

            }

        }

        drawer.closeDrawer(GravityCompat.START)

        return true

    }

}