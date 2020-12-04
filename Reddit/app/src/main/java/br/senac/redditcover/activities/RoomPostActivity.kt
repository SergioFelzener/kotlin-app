package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import br.senac.redditcover.R
import br.senac.redditcover.db.AppDatabase
import br.senac.redditcover.db.PostDao
import br.senac.redditcover.model.RoomPost

class RoomPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_post)

    }

    fun insertFavoritePost(post: RoomPost) {
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "Favorites").build()

        Thread {
            db.postDao().insert(post)
            finish()
        }.start()
    }

    fun listAllFavoritesPost() {
        // TODO
    }

}