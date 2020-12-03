package br.senac.redditcover.api

import br.senac.redditcover.model.Category
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("categories")
    fun getCategories() : Call<List<Category>>
}