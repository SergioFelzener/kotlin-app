package br.senac.redditcover.model

import com.firebase.ui.auth.data.model.User


data class Post(

        var id: String? = null,
        var name: String,
        var description: String,
        var isLiked: Boolean = false,
        var user_id: String,
        var comments: List<Comment>? = null

)


