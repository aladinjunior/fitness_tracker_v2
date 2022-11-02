package co.aladinjunior.tfitstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.tfitstudio.models.Fees

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<Items>()

        recyclerView = findViewById(R.id.main_recyclerView)
        val adapter = ItemsAdapter(list, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when{
            item.itemId == R.id.bmr_menu ->{
                startActivity(Intent(this, DataListActivity::class.java)
                    .putExtra("type","bmr"))
            }
            item.itemId == R.id.bmi_menu ->{
                startActivity(Intent(this, DataListActivity::class.java)
                    .putExtra("type","bmi"))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(id: Int) {
        when(id){
            1 ->{
                startActivity(Intent(this,BmiActivity::class.java))
            }
            2 ->{
                startActivity(Intent(this,BmrActivity::class.java))
            }
        }
    }

    override fun onLongClick(position: Int, fees: Fees) {
        TODO("Not yet implemented")
    }

}