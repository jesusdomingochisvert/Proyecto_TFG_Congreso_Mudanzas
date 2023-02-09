package com.example.congresotfg.mainModule

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.LoginModule.LoginActivity
import com.example.congresotfg.R
import com.example.congresotfg.common.utils.Shortcut
import com.example.congresotfg.databinding.FragmentHomeBinding
import com.example.congresotfg.databinding.MainActivityBinding
import com.google.android.gms.tasks.OnCompleteListener
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

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupShortcuts()

        toolbar = binding.customToolbar.toolbarMain
        bottomNav = binding.bnv
        drawer = binding.drawerMain
        navController = findNavController(R.id.frame_main)
        navDrawer = binding.nvMain

        toggle = ActionBarDrawerToggle(this, drawer, R.string.openToggle, R.string.closeToggle)

        drawer.addDrawerListener(toggle)

        toggle.syncState()

        setSupportActionBar(toolbar)

        bottomNav.selectedItemId = R.id.menu_home

        bottomNav.setupWithNavController(navController)

        moveFragments()

        appBarConfiguration = AppBarConfiguration(navController.graph, drawer)

        NavigationUI.setupActionBarWithNavController(this, navController, drawer)

        NavigationUI.setupWithNavController(navDrawer, navController)

        navDrawer.setNavigationItemSelectedListener(this)

    }

    private fun setupShortcuts() {

        if (Build.VERSION.SDK_INT >= 25) {

            Shortcut.setUp(applicationContext)

        }

    }

    private fun moveFragments() {

        binding.bnv.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.menu_location -> {

                    navController.navigate(R.id.location)

                    binding.customToolbar.txtTitleToolbar.text = "Location"

                }

                R.id.menu_home -> {

                    navController.navigate(R.id.home)

                    binding.customToolbar.txtTitleToolbar.text = "Home"

                }

                R.id.menu_like -> {

                    navController.navigate(R.id.my_events)

                    binding.customToolbar.txtTitleToolbar.text = "My List"

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

        if (toggle.onOptionsItemSelected(item)) {

            return true

        }

        when(item.itemId) {

            R.id.menu_log_out -> {

                val intent = Intent(this, LoginActivity::class.java)

                Toast.makeText(this, "Cerrando Sesión...", Toast.LENGTH_SHORT).show()

                val recordar = getSharedPreferences("recordar", MODE_PRIVATE)
                val socio = getSharedPreferences("socio", MODE_PRIVATE)

                val s: SharedPreferences.Editor = socio.edit()
                s.clear()
                s.apply()

                val r: SharedPreferences.Editor = recordar.edit()
                r.clear()
                r.apply()

                finish()
                startActivity(intent)

            }

            R.id.menu_profile -> {
                navController.navigate(R.id.profile)

                binding.customToolbar.txtTitleToolbar.text = "My profile"

            }


        }

        return super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.drawer_contact -> {

                navController.navigate(R.id.contact)

                binding.customToolbar.txtTitleToolbar.text = "Contact"

            }

            R.id.drawer_about -> {

                navController.navigate(R.id.about)

                binding.customToolbar.txtTitleToolbar.text = "About"

            }

            R.id.drawer_my_tickets -> {

                navController.navigate(R.id.my_tickets)

                binding.customToolbar.txtTitleToolbar.text = "My Tickets"

            }

            R.id.drawer_friends -> {
                navController.navigate(R.id.partners)

                binding.customToolbar.txtTitleToolbar.text = "Partners"
            }

            R.id.drawer_allEvents -> {
                navController.navigate(R.id.allEvents)

                binding.customToolbar.txtTitleToolbar.text = "All events"
            }

        }

        drawer.closeDrawer(GravityCompat.START)

        return true

    }

    override fun onBackPressed() {



    }

}