package gunveer.codes.videoplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecViewAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecViewAdapter.ViewHolder>() {
    private val dataSet: ArrayList<Videos> = ArrayList()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
        init {
            itemView.setOnClickListener {
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rec_layout, parent, false)

        view.setOnClickListener {

        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = dataSet[position].title
        holder.tvDesc.text = dataSet[position].desc
    }

    override fun getItemCount(): Int {
        if (dataSet != null) {
            return dataSet.size
        }
        return 0
    }



    fun updateVideos(updatedVideos: ArrayList<Videos>) {
        dataSet.clear()
        dataSet.addAll(updatedVideos)

        notifyDataSetChanged()
    }
}
