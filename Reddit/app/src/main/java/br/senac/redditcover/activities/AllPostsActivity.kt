package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.senac.redditcover.R
import br.senac.redditcover.adapters.PostsAdapter
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_all_posts.*
import kotlinx.android.synthetic.main.fragment_home.*

class AllPostsActivity : AppCompatActivity() {
    var database: DatabaseReference? = null
    var adapter: PostsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        configureFirebase()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_posts)
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
            database = FirebaseDatabase.getInstance().reference

            Log.i("Error", database!!.root.child("posts").toString())
            database?.let {
                val options = FirebaseRecyclerOptions.Builder<Post>()
                    .setQuery(it.child("posts"), Post::class.java)
                    .build()

                adapter = PostsAdapter(options)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

                adapter?.startListening()
            }
        }

    }

}