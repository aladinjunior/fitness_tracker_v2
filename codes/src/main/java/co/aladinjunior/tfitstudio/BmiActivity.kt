package co.aladinjunior.tfitstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import co.aladinjunior.tfitstudio.models.Fees
import java.lang.Exception
import kotlin.math.pow

class BmiActivity : AppCompatActivity() {

    private lateinit var okBttn: Button
    private lateinit var tvWeight: EditText
    private lateinit var tvHeight: EditText
    private lateinit var tvResult: TextView
    private lateinit var tvFee: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        okBttn = findViewById(R.id.ok_bttn)
        tvHeight = findViewById(R.id.height_txt)
        tvWeight = findViewById(R.id.weight_txt)
        tvResult = findViewById(R.id.result_txt)
        tvFee = findViewById(R.id.bmi_fees)



        okBttn.setOnClickListener {
            val weight = tvWeight.text.toString()
            val height = tvHeight.text.toString()
            when(!Calculate.isValid(weight, height)){
                true ->{
                    Toast.makeText(this,"fields can't be empty or start with 0",Toast.LENGTH_LONG).show()
                }
        }

            val bmi = Calculate.calculateBMI(weight.toInt(), height.toInt())
            val fee = getBmiFee(bmi)
            tvResult.setText(getString(R.string.bmi_result, bmi))
            tvFee.setText(getString(R.string.bmi_fee,fee))
            tvFee.setOnClickListener {
                Thread{
                    val app = application as App
                    val database = app.database.feesDao()
                    val insert = database.insert(Fees(type = "bmi", value = bmi))
                    runOnUiThread {
                        startActivity(Intent(this, DataListActivity::class.java)
                            .putExtra("type", "bmi"))
                    }
                }.start()
            }


    }

    }

    fun getBmiFee(bmi: Double) : String {
        val fee = resources.getStringArray(R.array.bmi_fees)
        return when{
            bmi < 18.4 -> fee[0]
            bmi < 24.9 -> fee[1]
            bmi < 29.9 -> fee[2]
            bmi < 30.0 -> fee[3]
            else -> ""

        }
    }

}
