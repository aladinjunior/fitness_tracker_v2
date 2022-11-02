package co.aladinjunior.tfitstudio



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class ItemsAdapter(private val items: List<Items>,
private val listener: OnItemClickListener) : RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>(){

    private lateinit var context: Context
    private lateinit var imgView: ImageView
    private lateinit var tvFullName: TextView
    private lateinit var tv3LName: TextView
    private lateinit var cardView: CardView


    private val itemsList = mutableListOf(
        Items(
            1,
            R.string.bmi_full_name,
            R.string.bmi_3L_name,
            R.drawable.ic_health_svgrepo_com
        ),
        Items(2,
            R.string.bmr_full_name,
        R.string.bmr_3L_name,
        R.drawable.ic_health_svgrepo_com)

    )



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false)
        return ItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val currentItem = itemsList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class ItemsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(items: Items) {
            tvFullName = itemView.findViewById(R.id.row_fullName)
            tv3LName = itemView.findViewById(R.id.row_3LName)
            imgView = itemView.findViewById(R.id.row_icon)
            cardView = itemView.findViewById(R.id.cardView)

            tvFullName.setText(items.name)
            tv3LName.setText(items.name3L)
            imgView.setImageResource(items.icon)

            cardView.setOnClickListener {
                listener.onClick(items.id)
            }
        }
    }

}

