package br.senac.redditcover.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val fab: View = view.fab
        fab.setOnClickListener {
            addPost()
        }

        return view
    }


    // fun to create a new post
    fun addPost() {

        //Set layout into dialog
        val nameField = EditText(context)
        nameField.hint = "Nome do seu post"
        AlertDialog.Builder(requireContext())
            .setTitle("Add post")
            .setView(nameField)
            .setPositiveButton("Postar") { dialog, button ->
                val post = Post(
                    name = nameField.text.toString().trim(),
                    description = "description"

                )
                val newPost = database?.child("posts")?.push()
                post.id = newPost?.key
                newPost?.setValue(post)
            }
            .setNegativeButton("Cancelar", null)
            .create()
            .show()
    }
}