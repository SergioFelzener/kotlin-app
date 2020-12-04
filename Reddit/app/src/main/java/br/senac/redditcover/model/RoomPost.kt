package br.senac.redditcover.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomPost(

    @PrimaryKey(autoGenerate = true)

    var id: Int? = null,
    var name: String? = null,
    var description: String? = null,
    var user_id: String? = null,
    var category: String? = null,

)
