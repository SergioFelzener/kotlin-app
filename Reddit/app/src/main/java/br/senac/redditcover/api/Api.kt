package br.senac.redditcover.api

import br.senac.redditcover.model.Category
import retrofit2.Call

import retrofit2.http.*

interface Api {


    @GET("category")
    fun getCategories(): Call<DefaultResponse>

    @GET("category/{id}")
    fun getCategory(@Path("id") _id: String): Call<Category>
}