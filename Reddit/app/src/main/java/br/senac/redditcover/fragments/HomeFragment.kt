package br.senac.redditcover.fragments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.senac.redditcover.R
import br.senac.redditcover.adapters.PostsAdapter
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.post_card_all.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    var database: DatabaseReference? = null
    var adapter: PostsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {

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
            Toast.makeText(context, "Autenticado", Toast.LENGTH_LONG).show()

        }

        Log.i("Error", database?.root.toString())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                configureFirebase()
                Toast.makeText(context, "Autenticado", Toast.LENGTH_LONG).show()
            }
            else {
                activity?.finish()
            }
        }
    }


    fun updateScreen(posts: List<Post>) {
        posts.forEach {
            val activity: Activity? = activity
            if (activity != null) {
                val postCard = layoutInflater.inflate(R.layout.post_card_all, postsScrol, false)
                postCard.postNane.text = it.name
                postCard.tvPostDescription.text = it.description
                postsScrol.addView(postCard)
                // etc ...
            }

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
        val database = FirebaseDatabase.getInstance().reference

        val firebaseDataEventListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {

                Log.w("home_activity", "configureFirebase", error.toException())

                Toast.makeText(context, "Erro na conexão com firebase", Toast.LENGTH_LONG).show()

            }
            override fun onDataChange(snapshot: DataSnapshot) {
                updateScreen(convertFirebaseSnapshot(snapshot))
            }

        }

        database.addValueEventListener(firebaseDataEventListener)

    }

    fun convertFirebaseSnapshot(snapshot: DataSnapshot): List<Post> {

        val postsList = arrayListOf<Post>()

        // forEach child inside the current snapshot
        snapshot.children.forEach {

            it.child("posts").children.forEach {
                val id = it.child("id").value.toString()
                val name = it.child("name").value.toString()
                val description = it.child("description").value.toString()
                val user_id = it.child("user_id").value.toString()
                val post = Post(id, name, description, user_id = user_id)
                postsList.add(post)
            }
        }

        return postsList
    }

}