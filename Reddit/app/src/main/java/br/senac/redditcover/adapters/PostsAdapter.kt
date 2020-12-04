package br.senac.redditcover.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.firebase.ui.database.ObservableSnapshotArray
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.post_card.view.*

class PostsAdapter(options: FirebaseRecyclerOptions<Post>): FirebaseRecyclerAdapter<Post, PostsAdapter.PostViewHolder>(options) {


    class PostViewHolder(override val containerView: View, val snapshots: ObservableSnapshotArray<Post>)
        : RecyclerView.ViewHolder(containerView), LayoutContainer{

        fun bind(post: Post){
            containerView.postName.text = post.name
            containerView.postDescription.text = post.description
            containerView.favoriteCheckBox.isChecked = post.liked
            containerView.deleteIcon.setOnClickListener {
                snapshots.getSnapshot(adapterPosition).ref.removeValue()
            }
            containerView.favoriteCheckBox.setOnCheckedChangeListener { button, isChecked ->
                snapshots.getSnapshot(adapterPosition).ref.child("liked")?.setValue(isChecked)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  PostViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_card, parent,false)
        return PostViewHolder(view, snapshots)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, post: Post) {
        holder.bind(post)
    }
}