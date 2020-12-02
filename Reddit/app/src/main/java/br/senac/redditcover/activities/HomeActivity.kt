package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_card.view.*

class HomeActivity : AppCompatActivity() {
    var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        configureFirebase()
    }

    fun configureFirebase() {


            //Reference to all posts
            val database = FirebaseDatabase.getInstance().reference.root

            val firebaseDataEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                    Log.w("home_activity", "configureFirebase", error.toException())

                    Toast.makeText(this@HomeActivity, "Erro na conexão com firebase", Toast.LENGTH_LONG).show()

                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    updateScreen(convertFirebaseSnapshot(snapshot))

                }

            }

            database?.addValueEventListener(firebaseDataEventListener)

    }

    fun convertFirebaseSnapshot(snapshot: DataSnapshot): List<Post> {

        val postsList = arrayListOf<Post>()

        // forEach child inside the current snapshot
        snapshot.children.forEach {


            val map = it.child("posts").getValue() as HashMap<String, Any>

            map.forEach {

                Log.i("All app posts -> ", it.toString())

            }


        }

        return postsList
    }

    fun updateScreen(posts: List<Post>) {
        allPostsList.removeAllViews()

        posts.forEach {

            val postCard = layoutInflater.inflate(R.layout.post_card, postsContainer, false)

            postCard.postNameTextField.text = it.name
            postCard.postDescriptionTextField.text = it.description

            postCard.deleteIcon.setOnClickListener { view ->

                it.id?.let {id ->
                    val item = database?.child("posts")?.child(id)

                    item?.removeValue()
                }

            }

            allPostsList.addView(postCard)

        }
    }
}