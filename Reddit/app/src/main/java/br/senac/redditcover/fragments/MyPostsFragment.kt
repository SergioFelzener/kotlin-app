package br.senac.redditcover.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        configureFirebase()
        // Fab to add a new post
        addPostFab.setOnClickListener {
            addPost()
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

    fun addPost() {

        //Set layout into dialog
        var nameField = EditText(context)
        nameField.hint = "Nome do seu post"

        var descriptionField = EditText(context)
        nameField.hint = "Digite aqui"

        var layoutView = LinearLayout(context)
        layoutView.setOrientation(LinearLayout.VERTICAL)

        layoutView.addView(nameField)
        layoutView.addView(descriptionField)

        AlertDialog.Builder(requireContext())
            .setTitle("Add post")
            .setView(layoutView)
            .setPositiveButton("Postar") { dialog, button ->

                var post = Post(
                    name = nameField.text.toString(),
                    description =  descriptionField.text.toString(),
                    user_id = getCurrentUser()?.uid.toString()


                )

                var newPost = database?.child("posts")?.push()

                post.id = newPost?.key

                newPost?.setValue(post)

            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()

    }

    fun updateScreen(posts: List<Post>) {
        postsScrol.removeAllViews()

        posts.forEach {

            var postCard = layoutInflater.inflate(
                R.layout.post_card,
                postsScrol,
                false
            )
            postCard.postNameTextField.text = it.name
            postCard.postDescriptionTextField.text = it.description

            postCard.deleteIcon.setOnClickListener { view ->

                it.id?.let { id ->
                    val item = database?.child("posts")?.child(id)

                    item?.removeValue()
                }
                postsContainer.removeView(view)
            }

            postsScrol.addView(postCard)
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
        user?.let{
            //Reference is only the current user -> .child(user.id)
            database = FirebaseDatabase.getInstance().reference.child(it.uid)

            val firebaseDataEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                    Log.w("main_activity", "configureFirebase", error.toException())

                    Toast.makeText(context, "Erro na conexão com firebase", Toast.LENGTH_LONG).show()

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

        // forEach child inside the current snapshot, set a post to postsList
        snapshot.child("posts").children.forEach {

            val map = it.getValue() as HashMap<String, Any>

            val id = map.get("id") as String
            val name = map.get("name") as String
            val description = map.get("description") as String
            val user_id = map.get("user_id") as String
            // val liked = map.get("liked") as Boolean

            val post = Post(id, name, description, user_id = user_id)

            postsList.add(post)

        }

        return postsList
    }
}