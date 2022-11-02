package co.aladinjunior.tfitstudio

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.aladinjunior.tfitstudio.models.Fees
import org.w3c.dom.Text

class DataListActivity : AppCompatActivity(), OnItemClickListener {


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataListAdapter
    private lateinit var datalist: MutableList<Fees>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.database_list)

        val type = intent.extras?.getString("type")

        recyclerView = findViewById(R.id.list_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        datalist = mutableListOf<Fees>()
        adapter = DataListAdapter(datalist,this)
        recyclerView.adapter = adapter

        Thread{
            val app = application as App
            val db = app.database.feesDao()
            val query = db.query(type)
            runOnUiThread {
                datalist.addAll(query)
                adapter.notifyDataSetChanged()

            }
        }.start()



    }

    override fun onClick(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(position: Int, fees: Fees) {
        Thread{
            val app = application as App
            val database = app.database.feesDao()
            val delete = database.delete(fees)
            runOnUiThread {
                datalist.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }.start()
    }

    private inner class DataListAdapter(private val database: List<Fees>,
    private val listener: OnItemClickListener) : RecyclerView.Adapter<DataListAdapter.DataListViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
            val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return DataListViewHolder(view)
        }

        override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
            val list = database[position]
            holder.bind(list)
        }

        override fun getItemCount(): Int {
            return database.size
        }


        inner class DataListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(fees: Fees) {
                val container = itemView as TextView
                val value = fees.value
                val type = fees.type
                container.text = getString(R.string.list_response,type, value)
                container.setOnLongClickListener {
                    listener.onLongClick(adapterPosition, fees)

                    true
                }
            }

        }
    }



}


