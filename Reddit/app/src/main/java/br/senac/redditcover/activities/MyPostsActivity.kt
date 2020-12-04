package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import br.senac.redditcover.R
import br.senac.redditcover.adapters.CustomAdapter
import br.senac.redditcover.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_my_posts.*

class MyPostsActivity : AppCompatActivity() {

    var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_posts)

        configureFirebase()
        // Fab to add a new post
        addPostFab.setOnClickListener {
            val i = Intent(this, AddPostActivity::class.java)
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

        // if user logged in get firebase reference
        // if user logged in get firebase reference
        user?.let{
            //Reference is only the current user -> .child(user.id)
            database = FirebaseDatabase.getInstance().reference.child("posts")

            val firebaseDataEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                    Log.w("main_activity", "configureFirebase", error.toException())

                    Toast.makeText(applicationContext, "Erro na conexão com firebase", Toast.LENGTH_LONG).show()

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    updateScreen(convertFirebaseSnapshot(snapshot))

                }


            }

            database?.addValueEventListener(firebaseDataEventListener)
        }
    }
    fun convertFirebaseSnapshot(snapshot: DataSnapshot): List<Post> {

        val postsList = arrayListOf<Post>()

        Log.i("Info", snapshot.toString())
        // forEach child inside the current snapshot, set a post to postsList
        snapshot.children.forEach {

            val map = it.getValue() as HashMap<String, Any>

            val id = map.get("id") as String
            val name = map.get("name") as String
            val description = map.get("description") as String
            val user_id = map.get("user_id") as String
            // val liked = map.get("liked") as Boolean

            val post = Post(id, name, description, user_id = user_id)
            if (post.user_id == getCurrentUser()?.uid){
                postsList.add(post)
            }


        }

        return postsList
    }
    fun updateScreen(posts: List<Post>) {

        var adapter = CustomAdapter(this, posts)
        grid_myposts.adapter = adapter
    }
}