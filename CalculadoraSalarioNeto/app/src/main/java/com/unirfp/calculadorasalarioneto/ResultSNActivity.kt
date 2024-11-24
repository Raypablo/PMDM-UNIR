package com.unirfp.calculadorasalarioneto

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unirfp.calculadorasalarioneto.MainActivity.Companion.DEDUC_KEY
import com.unirfp.calculadorasalarioneto.MainActivity.Companion.RIRPF_KEY
import com.unirfp.calculadorasalarioneto.MainActivity.Companion.SBRUTO_KEY
import com.unirfp.calculadorasalarioneto.MainActivity.Companion.SNETO_KEY

class ResultSNActivity : AppCompatActivity() {

    private lateinit var tvResultNeto: TextView
    private lateinit var tvSNetoResult: TextView

    private lateinit var tvResultBruto: TextView
    private lateinit var tvSBrutoResult: TextView

    private lateinit var tvResultIRFP: TextView
    private lateinit var tvIRPFResult: TextView

    private lateinit var tvResultDeduc: TextView
    private lateinit var tvDeducResult: TextView

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
        val resultSB = intent.extras?.getDouble(SBRUTO_KEY) ?: -1.0
        val resultIRPF = intent.extras?.getDouble(RIRPF_KEY) ?: -1.0
        val resultDEDUC = intent.extras?.getDouble(DEDUC_KEY) ?: -1.0

        initComponents()

        initListenners()

        initUI(resultSN, resultSB, resultIRPF, resultDEDUC)


    }

    private fun initUI(resultSN: Double, resultSB:Double, resultIRPF:Double, resultDEDUC:Double  ) {

        when (resultSN){

            in -1.00..0.00 ->{

                this.tvSNetoResult.text = getString(R.string.error)

            }

            else ->{

                this.tvSNetoResult.text = String.format("%.2f €", resultSN)

            }

        }

        when (resultSB){

            in -1.00..0.00 ->{

                this.tvSBrutoResult.text = getString(R.string.error)

            }

            else ->{

                this.tvSBrutoResult.text = String.format("%.2f €", resultSB)

            }

        }

        when (resultIRPF){

            in -1.00..0.00 ->{

                this.tvIRPFResult.text = getString(R.string.error)

            }

            else ->{

                this.tvIRPFResult.text = String.format("%.2f %%", resultIRPF)

            }

        }

        when (resultDEDUC){

            in -999999999.00..-0.01 ->{

                this.tvDeducResult.text = getString(R.string.error)

            }

            else ->{

                this.tvDeducResult.text = String.format("%.2f €", resultDEDUC)

            }

        }

    }

    private fun initListenners() {

        this.btnReCalcular.setOnClickListener{

            onBackPressedDispatcher.onBackPressed()



        }


    }

    private fun initComponents() {

        this.tvResultNeto = findViewById(R.id.tvResultNeto)
        this.tvSNetoResult = findViewById(R.id.tvSNetoResult)

        this.tvResultBruto = findViewById(R.id.tvResultBruto)
        this.tvSBrutoResult = findViewById(R.id.tvSBrutoResult)

        this.tvResultIRFP = findViewById(R.id.tvResultIRFP)
        this.tvIRPFResult = findViewById(R.id.tvIRPFResult)

        this.tvResultDeduc = findViewById(R.id.tvResultDeduc)
        this.tvDeducResult = findViewById(R.id.tvDeducResult)

        this.btnReCalcular = findViewById(R.id.btnReCalc)

    }
}