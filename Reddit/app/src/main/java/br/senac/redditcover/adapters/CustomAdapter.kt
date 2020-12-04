package br.senac.redditcover.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.ObservableSnapshotArray
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_card.view.*
import kotlinx.android.synthetic.main.post_card.view.favoriteCheckBox
import kotlinx.android.synthetic.main.post_card_all.view.*

class CustomAdapter(var context: Context, var posts: List<Post>): BaseAdapter() {
    override fun getCount(): Int {
        return posts.size
    }

    override fun getItem(position: Int): Any {
        return posts[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View = View.inflate(context, R.layout.post_card, null)
        var namePost: TextView = view.postName
        var descPost: TextView = view.postDescription

        var listItem: Post = posts[position]

        namePost.text = listItem.name
        descPost.text = listItem.description

        return view
    }
}