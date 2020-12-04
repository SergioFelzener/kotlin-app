package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import br.senac.redditcover.R
import br.senac.redditcover.adapters.PostsAdapter
import br.senac.redditcover.fragments.CategoriesFragment
import br.senac.redditcover.fragments.HomeFragment
import br.senac.redditcover.fragments.MyPostsFragment
import br.senac.redditcover.fragments.ProfileFragment
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all_posts.*
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    var database: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(getCurrentUser() == null ) {

            val providers
                    = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

            val i = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()

            startActivityForResult(i, 0)

        }else {
            configureFirebase()
            Toast.makeText(this, "Autenticado", Toast.LENGTH_LONG).show()

        }

               cardTodosPosts.setOnClickListener {
                   val i = Intent(this, AllPostsActivity::class.java)
                   startActivity(i)
               }

                cardMyPosts.setOnClickListener {
                    val i = Intent(this, MyPostsActivity::class.java)
                    startActivity(i)
                }
                cardCategories.setOnClickListener {
                    val i = Intent(this, CategoriesActivity::class.java)
                    startActivity(i)
                }
                cardProfile.setOnClickListener {
                    val i = Intent(this, ProfileActivity::class.java)
                    startActivity(i)
                }

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

        }

    }




}