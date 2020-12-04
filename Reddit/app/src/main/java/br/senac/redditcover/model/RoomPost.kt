package br.senac.redditcover.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter


@Entity
data class RoomPost(

    @PrimaryKey(autoGenerate = false)
    var id : String = "",
    var name: String?,
    var description: String?,
    var isLiked: Boolean?,
    var user_id: String?,
    var category: String?,
    var comments: String?
    //var category: Category?,
    //var comments: List<Comment>?


)
