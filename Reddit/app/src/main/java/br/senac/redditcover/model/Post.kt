package br.senac.redditcover.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
        var id: String? = null,
        var name: String? = null,
        var description: String? = null,
        var liked: Boolean = false,
        var user_id: String? = null,
        var category: Category? = null,
        var comments: List<Comment>? = null

) : Parcelable


