package br.senac.redditcover.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.senac.redditcover.R
import br.senac.redditcover.model.Category
import kotlinx.android.synthetic.main.category_card.view.*

class CategoriesAdapter(var context: Context, var categories: List<Category>): BaseAdapter() {
    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(p0: Int): Any {
        return categories.get(p0)
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View = View.inflate(context, R.layout.category_card, null)
        var nameCategory: TextView = view.nameCategory
        var descCategory: TextView = view.descCategory

        var listItem: Category = categories.get(position)

        nameCategory.text = listItem.name
        descCategory.text = listItem.description

        return view
    }
}