package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.senac.redditcover.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnLogout.setOnClickListener {
            //instancia firebase Auth
            FirebaseAuth.getInstance().signOut()
        }
    }
}