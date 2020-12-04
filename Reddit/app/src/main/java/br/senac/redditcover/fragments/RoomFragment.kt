package br.senac.redditcover.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import br.senac.redditcover.R
import br.senac.redditcover.db.AppDatabase
import br.senac.redditcover.model.RoomPost
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.postsScrol
import kotlinx.android.synthetic.main.fragment_room.*
import kotlinx.android.synthetic.main.post_card_all.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RoomFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {

                val thread = Thread {
           getPost(requireContext())
//
//                for (p in posts) {
//                    val activity: Activity? = activity
//                    if (activity != null) {
//                        val postCard =
//                            layoutInflater.inflate(R.layout.post_card_all, roomPostsLayout, false)
//                        postCard.postNane.text = p.name
//                        postCard.tvPostDescription.text = p.description
//                        roomPostsLayout.addView(postCard)
//                    }
//                }
//
        }
        thread.start()
        super.onActivityCreated(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    private fun getPost(context: Context): List<RoomPost>{
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "AppDb").build()

        var list = db.postDao().listAll()
        for ((i, l) in list.withIndex()){

            Log.i("RoomPost", "${list.get(i)}")
        }
        return list
    }
}