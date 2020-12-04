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
import kotlinx.android.synthetic.main.post_card.view.favoriteCheckBox
import kotlinx.android.synthetic.main.post_card_all.view.*

class CustomAdapter(options: FirebaseRecyclerOptions<Post>): FirebaseRecyclerAdapter<Post, CustomAdapter.CustomAdapterViewHolder>(options) {


    class CustomAdapterViewHolder(override val containerView: View, val snapshots: ObservableSnapshotArray<Post>)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(post: Post){
            containerView.postName.text = post.name
            containerView.postDescription.text = post.description
            containerView.favoriteCheckBox.isChecked = post.liked
            containerView.favoriteCheckBox.setOnCheckedChangeListener { button, isChecked ->
                snapshots.getSnapshot(adapterPosition).ref.child("liked")?.setValue(isChecked)
            }
            containerView.deleteIcon.setOnClickListener {
                snapshots.getSnapshot(adapterPosition).ref.removeValue()
            }

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  CustomAdapterViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_card, parent,false)
        return CustomAdapterViewHolder(view, snapshots)
    }

    override fun onBindViewHolder(holder: CustomAdapterViewHolder, position: Int, post: Post) {

        holder.bind(post)
    }
}