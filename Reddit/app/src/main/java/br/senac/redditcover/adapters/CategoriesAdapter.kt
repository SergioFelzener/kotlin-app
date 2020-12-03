package br.senac.redditcover.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.senac.redditcover.R
import br.senac.redditcover.model.Category
import kotlinx.android.synthetic.main.card_category.view.*

class CategoriesAdapter(var context: Context, var categories: List<Category>): BaseAdapter() {
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

        var listItem: Category = categories[position]

        nameCategory.text = listItem.name
        descCategory.text = listItem.description

        return view
    }
}