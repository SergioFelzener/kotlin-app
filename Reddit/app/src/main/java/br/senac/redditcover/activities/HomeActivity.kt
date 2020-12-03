package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import br.senac.redditcover.R
import br.senac.redditcover.fragments.CategoriesFragment
import br.senac.redditcover.fragments.HomeFragment
import br.senac.redditcover.fragments.ProfileFragment
import br.senac.redditcover.model.Post
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_card.view.*

class HomeActivity : AppCompatActivity() {
    var database: DatabaseReference? = null
    lateinit var categoriesFragment: CategoriesFragment
    lateinit var homeFragment: HomeFragment
    lateinit var profileFragment: ProfileFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView : BottomNavigationView = findViewById(R.id.btm_nav)
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home ->{
                    homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
                R.id.categories ->{
                    categoriesFragment = CategoriesFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, categoriesFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
                R.id.profile ->{
                    profileFragment = ProfileFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, categoriesFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }
            }

            true
        }
    }




}