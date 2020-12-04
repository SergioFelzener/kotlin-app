package br.senac.redditcover.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.senac.redditcover.R
import br.senac.redditcover.api.RetrofitClient
import br.senac.redditcover.model.Category
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_add_post.*
import kotlinx.android.synthetic.main.fragment_my_posts.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddPostActivity : AppCompatActivity(){
    var database: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)
        configureFirebase()
//        var categorySelected: Category
        RetrofitClient.instance.getCategories()
            .enqueue(object : Callback<List<Category>> {
                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    val categories: List<Category> = response.body()!!
                    val adapter = ArrayAdapter<Category>(
                        applicationContext,
                        android.R.layout.simple_spinner_item, categories
                    )
                    spinnerCategories.adapter = adapter
                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


        btnAddPost.setOnClickListener {
            spinnerCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val category = parent.selectedItem as Category
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(applicationContext, "Opa", Toast.LENGTH_LONG).show()
                }
            }
            var post = Post(
                name = etName.text.toString(),
                description = etDesc.text.toString(),
                user_id = getCurrentUser()?.uid.toString()
            )

            var newPost = database?.child("posts")?.push()

            post.id = newPost?.key
            post.category = spinnerCategories.selectedItem as Category?

            newPost?.setValue(post)

            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)
        }

    }
//    fun getSelectedUser(v: View?): Category? {
//        val category = spinner.selectedItem as Category
//        displayUserData(category)
//        return category
//    }
//
//    private fun displayUserData(category: Category): Category {
//        val name = category.name
//        val description: String = category.description
//        return category
//    }

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

                    Toast.makeText(applicationContext, "Erro na conexão com firebase", Toast.LENGTH_LONG).show()

                }

                override fun onDataChange(snapshot: DataSnapshot) {


                }


            }

            database?.addValueEventListener(firebaseDataEventListener)
        }
    }
}