package com.unirfp.calculadorasalarioneto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unirfp.calculadorasalarioneto.MainActivity.Companion.SNETO_KEY

class ResultSNActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvSNetoResult: TextView
    private lateinit var btnReCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_snactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val resultSN = intent.extras?.getDouble(SNETO_KEY) ?: -1.0

        initComponents()

        initListenners()

        initUI(resultSN)


    }

    private fun initUI(resultSN: Double) {

        when (resultSN){

            in -1.00..0.00 ->{

                this.tvSNetoResult.text = getString(R.string.error)

            }

            else ->{

                this.tvSNetoResult.text = String.format("%.2f €", resultSN)

            }

        }

    }

    private fun initListenners() {

        this.btnReCalcular.setOnClickListener{

            onBackPressedDispatcher.onBackPressed()



        }


    }

    private fun initComponents() {

        this.tvResult = findViewById(R.id.tvResult)
        this.tvSNetoResult = findViewById(R.id.tvSNetoResult)
        this.btnReCalcular = findViewById(R.id.btnReCalc)

    }
}