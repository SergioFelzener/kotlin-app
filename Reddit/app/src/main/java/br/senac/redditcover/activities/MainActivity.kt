package br.senac.redditcover.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentTransaction
import br.senac.redditcover.R
import br.senac.redditcover.fragments.CategoriesFragment
import br.senac.redditcover.fragments.HomeFragment
import br.senac.redditcover.fragments.ProfileFragment
import br.senac.redditcover.model.Post
import com.firebase.ui.auth.AuthUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_dialog.*




class MainActivity : AppCompatActivity() {
    lateinit var profileFragment: ProfileFragment
    lateinit var categoriesFragment: CategoriesFragment
    lateinit var homeFragment: HomeFragment

    var database: DatabaseReference? = null

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
            configureFirebase()
            Toast.makeText(this, "Autenticado", Toast.LENGTH_SHORT).show()

        }

        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.btn_nav)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId){
                R.id.home->{
                    homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
                R.id.categories->{
                    categoriesFragment = CategoriesFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, categoriesFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
                R.id.profile->{
                    profileFragment = ProfileFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, profileFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
            }
            true

        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                configureFirebase()
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

    fun configureFirebase() {
        val user = getCurrentUser()

        // if user logged in get firebase reference
        user?.let{
            database = FirebaseDatabase.getInstance().reference.child(user.uid)
        }


    }

}