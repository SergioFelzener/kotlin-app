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
import br.senac.redditcover.adapters.CategoriesAdapter
import br.senac.redditcover.adapters.CustomAdapter
import br.senac.redditcover.adapters.PostsAdapter
import br.senac.redditcover.model.Post
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_my_posts.*


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

        // if user logged in get firebase reference
        // if user logged in get firebase reference
        user?.let{
            //Reference is only the current user -> .child(user.id)
            database = FirebaseDatabase.getInstance().reference.child("posts")

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

        var adapter: CustomAdapter? = null
//        adapter = PostsAdapter(posts)
//        listPosts.layoutManager = LinearLayoutManager(context)
//        listPosts.adapter = adapter
//
//        adapter?.startListening()
//        var postsAdapter = PostsAdapter()
        adapter = CustomAdapter(requireContext(), posts)
        grid_myposts.adapter = adapter
    }

}