package br.senac.redditcover.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import br.senac.redditcover.R
import br.senac.redditcover.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*


class HomeActivity : AppCompatActivity() {

    lateinit var categoriesFragment: CategoriesFragment
    lateinit var homeFragment: HomeFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var myPostsFragment: MyPostsFragment
    lateinit var roomFragment: RoomFragment

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
                R.id.roomPosts ->{
                    roomFragment = RoomFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, roomFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }

                R.id.myposts ->{
                    myPostsFragment = MyPostsFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, myPostsFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.profile ->{
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




}