package br.senac.redditcover.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.ObservableSnapshotArray
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_post_category.view.*
import kotlinx.android.synthetic.main.post_card.view.*
import kotlinx.android.synthetic.main.post_card.view.favoriteCheckBox
import kotlinx.android.synthetic.main.post_card_all.view.*

class CustomAdapter(
    private var posts: List<Post>
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    private val items: MutableList<CardView>

    init {
        this.items = ArrayList()
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvName: TextView = itemView.tvNamePost
        val tvDescription: TextView = itemView.tvDescriptionPost
        val card: CardView = itemView.card_post_category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_post_category, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = posts[position].name
        holder.tvDescription.text = posts[position].category?.name
        items.add(holder.card)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}