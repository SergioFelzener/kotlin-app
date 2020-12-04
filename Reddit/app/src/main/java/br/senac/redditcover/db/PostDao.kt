package br.senac.redditcover.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.senac.redditcover.model.RoomPost

@Dao
interface PostDao {

    @Insert
    fun insert(post: RoomPost)

    @Query(value = "SELECT * FROM RoomPost")
    fun listAll(): List<RoomPost>

}