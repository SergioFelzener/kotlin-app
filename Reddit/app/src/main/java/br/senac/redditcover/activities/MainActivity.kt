package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.senac.redditcover.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // se estiver usuário logado
        if(getCurrentUser() == null ) {

            val providers
                    = arrayListOf(AuthUI.IdpConfig.EmailBuilder().build())

            val i = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .build()

            startActivityForResult(i, 0)

        }else {

            Toast.makeText(this, "Autenticado", Toast.LENGTH_LONG).show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Autenticado", Toast.LENGTH_LONG).show()
            }
            else {
                finishAffinity()
            }
        }
    }

    fun getCurrentUser(): FirebaseUser? {

        //instancia firebase Auth
        val auth = FirebaseAuth.getInstance()
        return auth.currentUser

    }

}