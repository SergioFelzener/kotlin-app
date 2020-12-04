package br.senac.redditcover.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import br.senac.redditcover.R
import br.senac.redditcover.adapters.PostsAdapter
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_all_posts.*
import kotlinx.android.synthetic.main.fragment_home.*

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
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        if(getCurrentUser() == null ) {
//
//            val providers
//                    = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())
//
//            val i = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .build()
//
//            startActivityForResult(i, 0)
//
//        }else {
//            configureFirebase()
//            Toast.makeText(context, "Autenticado", Toast.LENGTH_LONG).show()
//
//        }
//
//        Log.i("Error" , database?.root.toString())
//        super.onActivityCreated(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 0) {
//            if (resultCode == RESULT_OK) {
//                configureFirebase()
//                Toast.makeText(context, "Autenticado", Toast.LENGTH_LONG).show()
//            }
//            else {
//                activity?.finish()
//            }
//        }
//    }
//
//    fun getCurrentUser(): FirebaseUser? {
//
//        //instancia firebase Auth
//        val auth = FirebaseAuth.getInstance()
//        return auth.currentUser
//
//    }
//    fun configureFirebase() {
//        val user = getCurrentUser()
//
//        //Reference to all posts
//        user?.let {
//            database = FirebaseDatabase.getInstance().reference
//
//            Log.i("Error", database!!.root.child("posts").toString())
//            database?.let {
//                val options = FirebaseRecyclerOptions.Builder<Post>()
//                    .setQuery(it.child("posts"), Post::class.java)
//                    .build()
//
//                adapter = PostsAdapter(options)
//                listPosts.layoutManager = LinearLayoutManager(context)
//                listPosts.adapter = adapter
//
//                adapter?.startListening()
//            }
//        }
//
//    }
//
//    override fun onStop() {
//        super.onStop()
//        adapter?.stopListening()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        adapter?.startListening()
//
//    }

}