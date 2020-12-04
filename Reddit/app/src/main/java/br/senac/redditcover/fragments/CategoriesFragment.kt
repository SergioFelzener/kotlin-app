package br.senac.redditcover.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.senac.redditcover.R
import br.senac.redditcover.adapters.CategoriesAdapter
import br.senac.redditcover.api.RetrofitClient
import br.senac.redditcover.model.Category
import kotlinx.android.synthetic.main.fragment_categories.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {

    private var categoriesAdapter: CategoriesAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
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

                    val activity: Activity? = activity
                    if (activity != null) {
                        categoriesAdapter = CategoriesAdapter(requireContext(), category)
                        grid_categories.adapter = categoriesAdapter
                    }


                }

                override fun onFailure(call: Call<List<Category>>, t: Throwable) {
                    Toast.makeText(context, "Deu erro!", Toast.LENGTH_LONG)
                }

            })
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


}