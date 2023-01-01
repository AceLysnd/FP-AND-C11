package com.ace.c11flight.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityHomeBinding
import com.ace.c11flight.ui.view.ProfileActivity.Companion.ACCOUNT_ID
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
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        analytics = Firebase.analytics

        isLoginInfoValid()
        setBottomNav()
        setOnClickListeners()

        initInAppNotification()

    }

    private fun initInAppNotification() {
        viewModel.getInAppStatus().observe(this) {
            if (it == 1) {
                var status = it + 1
                makeDialog()
                viewModel.setInAppStatus(status)
            }
        }
    }

    private fun makeDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Notification")
        builder.setMessage("You just made a transaction, wanna check your history?")

//        builder.set()
        builder.setPositiveButton("yes, take me there") { dialog, which ->
            intent = Intent(this, TransactionHistoryActivity::class.java)
            startActivity(intent)
            finish()
        }

        builder.setNegativeButton("no") { dialog, which ->

        }
        builder.show()
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
        val change = Intent(this@HomeActivity, BookingActivity::class.java)
        startActivity(change)
        finish()
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }

    private fun isLoginInfoValid() {
        viewModel.getLoginStatus().observe(this) {
            if (it) {
                binding.messageLogin.visibility = View.GONE
                LOGIN_STATUS = it
            }
        }
        viewModel.getAccountPrefs().observe(this) {
            ACCOUNT_ID = it.accountId
            USERNAME = it.username
            TOKEN = it.token
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        if (LOGIN_STATUS == false) {
            menuInflater.inflate(R.menu.menu_home_nologin, menu)
        } else if (LOGIN_STATUS == true) {
            if (NOTIFICATION_STATUS == false) {
                menuInflater.inflate(R.menu.menu_home, menu)
            } else{
                menuInflater.inflate(R.menu.menu_home_notif, menu)
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {
                goToProfile()
                true
            }
            R.id.notification -> {
                goToNotification()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToNotification() {
        val intent = Intent (this, NotificationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToProfile() {
        val i = Intent(this, ProfileActivity::class.java)
        startActivity(i)
        finish()
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

    companion object {
        var USERNAME = ""
        var TOKEN = ""
        var LOGIN_STATUS = false
        var NOTIFICATION_STATUS = false
    }
}