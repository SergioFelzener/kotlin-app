package br.senac.redditcover.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.room.Database
import br.senac.redditcover.R
import br.senac.redditcover.activities.CategoryActivity
import br.senac.redditcover.model.Category
import br.senac.redditcover.model.Post
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.card_category.view.*
import java.io.Serializable

class CategoriesAdapter(var context: Context, var categories: List<Category>): BaseAdapter() {
    var database: DatabaseReference? = null
    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return categories[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View = View.inflate(context, R.layout.card_category, null)
        var nameCategory: TextView = view.nameCategory
        var descCategory: TextView = view.descCategory

        view.setOnClickListener {
            val database = FirebaseDatabase.getInstance().reference

            val firebaseDataEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {

                    Log.w("home_activity", "configureFirebase", error.toException())

                    Toast.makeText(context, "Erro na conexão com firebase", Toast.LENGTH_LONG).show()

                }
                override fun onDataChange(snapshot: DataSnapshot) {
                    val postsList = arrayListOf<Post>()

                    // forEach child inside the current snapshot
                    snapshot.children.forEach {

                        it.child("posts").children.forEach {
                            val id = it.child("id").value.toString()
                            val name = it.child("name").value.toString()
                            val description = it.child("description").value.toString()
                            val user_id = it.child("user_id").value.toString()
                            val liked: Boolean = it.child("liked").value as Boolean
                            val category = it.child("category").value
                            val post = Post(id, name, description, liked, user_id)

                            postsList.add(post)


                        }
                    }
                    val intent = Intent(view.context, CategoryActivity::class.java)
                    intent.putExtra("name", nameCategory.text.toString().trim())
                    intent.putExtra("posts", postsList as Serializable)
                    view.context.startActivity(intent)
                }

            }

            database.addValueEventListener(firebaseDataEventListener)


        }
        var listItem: Category = categories[position]

        nameCategory.text = listItem.name
        descCategory.text = listItem.description

        return view
    }

    fun configureFirebase() {
        //Reference to all posts


    }
}