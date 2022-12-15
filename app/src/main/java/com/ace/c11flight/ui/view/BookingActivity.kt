package com.ace.c11flight.ui.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ace.c11flight.R
import com.ace.c11flight.databinding.ActivityBookingBinding
import kotlin.math.min

class BookingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookingBinding
    private var plusAdult : Int = 0;
    private var teenager : Int = 0;
    private var childreen: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

//        if (extras != null) {
//            getDetails(extras)
//
//        } else {
//            finish()
//        }
        setOnclick()
        btn_plust_Adult()
        btn_minus_Adult()
        btn_plust_Teenager()
        btn_min_Teenager()
        btn_plus_Children()
        btn_minus_Childreen()
    }

    private fun btn_minus_Childreen() {
        binding.btnMinusChildreen.setOnClickListener {
            childreen--
            binding.tvValueChildreen.text = childreen.toString()
            Toast.makeText(this,"Chilldren min",Toast.LENGTH_SHORT).show()
        }
    }

    private fun btn_plus_Children() {
        binding.btnPlusChildren.setOnClickListener {
            childreen++
            binding.tvValueChildreen.text = childreen.toString()
            Toast.makeText(this,"Chilldreen Add",Toast.LENGTH_SHORT).show()
        }
    }

    private fun btn_plust_Teenager() {
        binding.btnPlusTeenager.setOnClickListener {
            teenager++
            binding.tvValueTeenager.text = teenager.toString()
            Toast.makeText(this,"Teenager add",Toast.LENGTH_SHORT).show()
        }
    }

    private fun btn_minus_Adult() {
        binding.btnMinusPlus.setOnClickListener {
            plusAdult--
            binding.tvValueAdult.text = plusAdult.toString()
            Toast.makeText(this,"adult min",Toast.LENGTH_SHORT).show()

        }
    }

    private fun btn_plust_Adult() {
        binding.btnPlusAdult.setOnClickListener {
            plusAdult++
            binding.tvValueAdult.text = plusAdult.toString()
            Toast.makeText(this,"adult plus",Toast.LENGTH_SHORT).show()
        }
    }

    fun btn_min_Teenager() {
        binding.btnMinusTenager.setOnClickListener {
            teenager--
            binding.tvValueTeenager.text = teenager.toString()
            Toast.makeText(this,"Teenager min",Toast.LENGTH_SHORT).show()
        }
    }

    private fun setOnclick() {
        binding.btnOneway.setOnClickListener {
            binding.btnOneway.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnRoundtrip.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnRoundtrip.setOnClickListener {
            binding.btnRoundtrip.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnOneway.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnEconomy.setOnClickListener {
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnBussiness.setOnClickListener {
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
        binding.btnFirstclass.setOnClickListener {
            binding.btnFirstclass.setBackgroundColor(resources.getColor(R.color.main))
            binding.btnBussiness.setBackgroundColor(resources.getColor(R.color.grayd9))
            binding.btnEconomy.setBackgroundColor(resources.getColor(R.color.grayd9))
        }
    }


}