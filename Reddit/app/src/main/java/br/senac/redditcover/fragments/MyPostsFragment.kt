package br.senac.redditcover.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.senac.redditcover.R
import br.senac.redditcover.activities.AddPostActivity
import br.senac.redditcover.adapters.CustomAdapter
import br.senac.redditcover.adapters.PostsAdapter
import br.senac.redditcover.model.Post
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_my_posts.*
import kotlinx.android.synthetic.main.post_card.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyPostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyPostsFragment : Fragment() {

    var database: DatabaseReference? = null
    var adapter: PostsAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {

        configureFirebase()
        // Fab to add a new post
        addPostFab.setOnClickListener {
            val i = Intent(context, AddPostActivity::class.java)
            startActivity(i)
        }
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_posts, container, false)
    }

    fun getCurrentUser(): FirebaseUser? {

        //instancia firebase Auth
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser

    }
    fun configureFirebase() {
        val user = getCurrentUser()

        //Reference to all posts
        user?.let {
            database = FirebaseDatabase.getInstance().reference.child(user.uid)

            database?.let {
                val options = FirebaseRecyclerOptions.Builder<Post>()
                    .setQuery(it.child("posts"), Post::class.java)
                    .build()

                adapter = PostsAdapter(options)
                listPosts.layoutManager = LinearLayoutManager(context)
                listPosts.adapter = adapter

                adapter?.startListening()
            }
        }

    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }

    override fun onResume() {
        super.onResume()
        adapter?.startListening()

    }
}