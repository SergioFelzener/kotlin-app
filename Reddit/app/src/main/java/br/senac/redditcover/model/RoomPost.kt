package br.senac.redditcover.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomPost(

    @PrimaryKey(autoGenerate = false)

    var id : String,
    var user : String,
    var title : String,
    var description : String,

)
