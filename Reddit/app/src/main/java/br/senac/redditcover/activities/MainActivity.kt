package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.senac.redditcover.R
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_dialog.*




class MainActivity : AppCompatActivity() {
    var database: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // se estiver usuário logado
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
        // Fab to add a new post
        addPostFab.setOnClickListener {
            addPost()
        }

    }

    // fun to create a new post
    fun addPost() {

        //Set layout into dialog
        val nameField = EditText(this)
        nameField.hint = "Nome do seu post"

        AlertDialog.Builder(this)
                .setTitle("Add post")
                .setView(nameField)
                .setPositiveButton("Postar") { dialog, button ->

                    val post = Post(
                        name = nameField.text.toString(),
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                configureFirebase()
                Toast.makeText(this, "Autenticado", Toast.LENGTH_LONG).show()
            }
            else {
                finishAffinity()
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

        // if user logged in get firebase reference
        user?.let{
            database = FirebaseDatabase.getInstance().reference.child(user.uid)
        }


    }

}