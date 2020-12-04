package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import br.senac.redditcover.R
import br.senac.redditcover.adapters.CustomAdapter
import br.senac.redditcover.model.Post
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val category = intent.getStringExtra("name")
        val posts = intent.getParcelableArrayListExtra<Post>("posts")



        tvCategory.text = category

        if (posts != null){
            val post : MutableList<Post> = ArrayList()
            for(p in posts!!){
                post.add(p)
            }



            val adapter = CustomAdapter(post)
            val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            this.listPostsCategory.layoutManager = layoutManager
            this.listPostsCategory.setHasFixedSize(true)
            this.listPostsCategory.adapter = adapter
        }

    }
}