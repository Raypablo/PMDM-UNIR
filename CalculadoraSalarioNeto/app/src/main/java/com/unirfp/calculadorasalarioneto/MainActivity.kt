package com.unirfp.calculadorasalarioneto

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    //Creamos variables privadas para recoger los elementos visuales. Inicializacion tardia (Lazy)
    private lateinit var editSalarioNeto: EditText
    private lateinit var editEdad: EditText
    private lateinit var spinnerNPagas: Spinner
    private lateinit var spinnerGProfesional: Spinner
    private lateinit var spinnerGDiscapacidad: Spinner
    private lateinit var spinnerECivil: Spinner
    private lateinit var tvNHijos: TextView
    private lateinit var btnCalc: Button
    private lateinit var btnSubNHijos: FloatingActionButton
    private lateinit var btnPlusNHijos: FloatingActionButton


    //Creamos los atributos necesarios para la logica de nuestros componentes
    private var currentHijos: Int = 0

    companion object{

        const val SNETO_KEY = "SNETO_RESULT"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Nuestro codigo en el OnCreate

        //Iniciar los componentes visuales
        initComponents()

        //Iniciar ños listeners de los eventos
        initListeners()

        //configuraciones visuales de los componentes
        initUI()
            

    }


    private fun initComponents() {
        editSalarioNeto = findViewById(R.id.editSalarioNeto)
        editEdad = findViewById(R.id.editEdad)
        spinnerNPagas = findViewById(R.id.spinnerNPagas)
        spinnerGProfesional = findViewById(R.id.spinnerGProfesional)
        spinnerGDiscapacidad = findViewById(R.id.spinnerGDiscapacidad)
        spinnerECivil = findViewById(R.id.spinnerECivil)
        tvNHijos = findViewById(R.id.tvNHijos)
        btnSubNHijos = findViewById(R.id.btnSubNHijos)
        btnPlusNHijos = findViewById(R.id.btnPlusNHijos)
        btnCalc = findViewById(R.id.btnCalc)
    }

    private fun initListeners() {

        this.btnSubNHijos.setOnClickListener{

            if(this.currentHijos > 0) {
                this.currentHijos -= 1
                setNHijos()
            }
        }

        this.btnPlusNHijos.setOnClickListener{

            this.currentHijos += 1
            setNHijos()

        }


        this.btnCalc.setOnClickListener{

            val resultSNeto = calcSueldoNeto()
            navigateToResult(resultSNeto)

        }

    }

    private fun navigateToResult(resultSNeto: Double) {

        val intent = Intent(this, ResultSNActivity::class.java)
        intent.putExtra(SNETO_KEY, resultSNeto)

        this.startActivity(intent)

    }

    private fun calcSueldoNeto(): Double {

        val salarioNeto: Double
        val numGProf:Double
        val numGDisc:Double
        val numEcivil:Double
        val numPagas:Int
        val numSBruto:Int
        val numEdad:Int
        val numHijos:Int

        val salarioBrutoStr = editSalarioNeto.text.toString()
        val edadStr = editEdad.text.toString()
        val nPagasStr = spinnerNPagas.selectedItem.toString()
        val gProfesional = spinnerGProfesional.selectedItem.toString()
        val gDiscapacidad = spinnerGDiscapacidad.selectedItem.toString()
        val eCivil = spinnerECivil.selectedItem.toString()
        val nHijosStr = tvNHijos.text.toString()

        when (gProfesional) {
            "Licenciados" -> numGProf = 1.1
            "Ingenieros técnicos, peritos y ayudantes" -> numGProf = 1.2
            "Jefes administrativos y de taller" -> numGProf = 1.3
            "Ayudantes no titulados" -> numGProf = 1.4
            "Oficiales administrativos" -> numGProf = 1.5
            "Subalternos" -> numGProf = 1.6
            "Auxiliares administrativos" -> numGProf = 1.7
            "Oficiales de 1ª y 2ª" -> numGProf = 1.8
            "Oficiales de 3ª" -> numGProf = 1.9
            "Peones" -> numGProf = 2.0

            else -> {
                Toast.makeText(this, "Por favor, selecciona el Grupo Profesional.", Toast.LENGTH_LONG).show()
                // Salir de la función devolviendo null
                return 0.0
            }
        }

        when (gDiscapacidad) {

            "Sin discapacidad" -> numGDisc = 1.1
            "Menor del 65% (sin asistencia)" -> numGDisc = 0.7
            "Menor del 65% (con asistencia)" -> numGDisc = 0.4
            "Mayor o igual al 65%" -> numGDisc = 0.2

            else -> {
                Toast.makeText(this, "Por favor, selecciona el Grupo de discapacidad.", Toast.LENGTH_LONG).show()
                // Salir de la función devolviendo null
                return 0.0
            }
        }

        when (eCivil) {

            "Casado" -> numEcivil = 1.1
            "Divorciado" -> numEcivil = 1.2
            "Separado" -> numEcivil = 1.3
            "Soltero" -> numEcivil = 1.4
            "Viudo" -> numEcivil = 1.5

            else -> {
                Toast.makeText(this, "Por favor, selecciona el Estado Civil.", Toast.LENGTH_LONG).show()
                // Salir de la función devolviendo null
                return 0.0
            }
        }

        when (nPagasStr) {

            "12" -> numPagas = 12
            "14" -> numPagas = 14

            else -> {
                Toast.makeText(this, "Por favor, selecciona el número de pagas.", Toast.LENGTH_LONG).show()
                return 0.0
            }
        }

        numSBruto = salarioBrutoStr.toInt()
        numEdad = edadStr.toInt()
        numHijos = nHijosStr.toInt()

        salarioNeto = (numSBruto/numPagas)*(((((numEdad/20)+numHijos))*numEcivil)*numGDisc)*numGProf
        Log.i("salarioNeto","salarioNeto: $salarioNeto")
        return  salarioNeto

    }


    private fun setNHijos(){

        this.tvNHijos.text = this.currentHijos.toString()

    }


    private fun initUI() {

        this.setNHijos()

    }
}