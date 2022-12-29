package com.ace.c11flight.ui.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityHomeBinding
import com.ace.c11flight.databinding.ActivityRegisterBinding
import com.ace.c11flight.ui.viewmodel.HomeActivityViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeActivityViewModel by viewModels()
    private lateinit var  analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        analytics = Firebase.analytics

        setBottomNav()
        isLoginInfoValid()
        setOnClickListeners()

    }





    private fun setBottomNav() {
        val homeFragment = HomeFragment()
        val moreFragment = MoreFragment()
        setCurrentFragment(homeFragment)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.booking -> goToBooking()
                R.id.more -> setCurrentFragment(moreFragment)

            }
            true
        }
    }

    private fun goToBooking() {
        val change = Intent(this@HomeActivity,BookingActivity::class.java)
        startActivity(change)
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
    }

    private fun isLoginInfoValid() {
        viewModel.getLoginStatus().observe(this) {
            if (it) {
                binding.messageLogin.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                goToProfile()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToProfile() {
        val i = Intent(this, ProfileActivity::class.java)
        startActivity(i)
    }

    private fun setOnClickListeners() {

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private var backButtonCount = 0
    override fun onBackPressed() {
        if (backButtonCount < 1) {
            Toast.makeText(this, getString(R.string.press_back_again), Toast.LENGTH_SHORT).show()
            backButtonCount += 1
        } else {
            moveTaskToBack(true)
            backButtonCount = 0
        }
    }

}