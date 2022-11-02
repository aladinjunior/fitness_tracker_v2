package co.aladinjunior.tfitstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import co.aladinjunior.tfitstudio.models.Fees

class BmrActivity : AppCompatActivity() {

    private lateinit var tvHeight: EditText
    private lateinit var tvWeight: EditText
    private lateinit var gender: AutoCompleteTextView
    private lateinit var bmrBttn: Button
    private lateinit var tvResult: TextView
    private lateinit var tvAge: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmr)

        tvHeight = findViewById(R.id.bmrHeight_txt)
        tvWeight = findViewById(R.id.bmrWeight_txt)
        gender = findViewById(R.id.gender)
        bmrBttn = findViewById(R.id.bmrOk_bttn)
        tvResult = findViewById(R.id.bmrResult_txt)
        tvAge = findViewById(R.id.bmrAge_txt)

        val weight = tvWeight.text.toString()
        val height = tvHeight.text.toString()
        val age = tvAge.text.toString()

        val items = resources.getStringArray(R.array.bmr_gender)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        gender.setAdapter(adapter)


        bmrBttn.setOnClickListener {
            if(!isValid(weight,height,age)){
                val bmrValue = calculateBmr(tvWeight.text.toString().toInt(),
                    tvHeight.text.toString().toInt(),
                    tvAge.text.toString().toInt())
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.bmr_result_title,bmrValue))
                    .setPositiveButton(android.R.string.ok){_,_->}
                    .setNegativeButton(R.string.save){_,_->
                        Thread{
                            val app = application as App
                            val database = app.database.feesDao()
                            val insert = database.insert(Fees(type = "bmr", value = bmrValue))
                            runOnUiThread {
                                startActivity(Intent(this, DataListActivity::class.java)
                                    .putExtra("type","bmr"))
                            Toast.makeText(this,R.string.save,Toast.LENGTH_LONG).show()
                            }
                        }.start()
                    }
                    .create()
                    .show()
            }else{
                Toast.makeText(this,R.string.field_message,Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun calculateBmr(weight: Int, height: Int, age: Int) : Double{
        val arGender = resources.getStringArray(R.array.bmr_gender)
        return if(gender.text.toString().equals(arGender[0])){
            (10 * weight) + (6.25 * height) - (5*age) + 5
            }else if(gender.text.toString().equals(arGender[1])){
            (10 * weight) + (6.25 * height) - (5*age) - 161
        }else{
            0.0
        }

    }

    fun isValid(weight: String, height: String, age: String): Boolean {
        return (weight.isNotEmpty()
                && height.isNotEmpty()
                && !weight.startsWith("0")
                && !height.startsWith("0")
                && age.isNotEmpty())
    }

}