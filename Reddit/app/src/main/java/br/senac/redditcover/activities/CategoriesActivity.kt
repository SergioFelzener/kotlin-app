package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senac.redditcover.R
import br.senac.redditcover.adapters.CategoriesAdapter
import br.senac.redditcover.api.RetrofitClient
import br.senac.redditcover.model.Category
import kotlinx.android.synthetic.main.fragment_categories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        RetrofitClient.instance.getCategories()
            .enqueue(object : Callback<List<Category>> {
                override fun onResponse(
                    call: Call<List<Category>>,
                    response: Response<List<Category>>
                ) {
                    val categories: List<Category> = response.body()!!
                    val category : ArrayList<Category> = ArrayList()
                    for (c in categories){
                        category.add(c)
                    }

                    if (category.isNotEmpty()){
                        var categoriesAdapter = CategoriesAdapter(this@CategoriesActivity, category)
                        grid_categories.adapter = categoriesAdapter
                    }


                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Deu erro!", Toast.LENGTH_LONG)
                }

            })
    }
}