package com.example.sixpchat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.sixpchat.adapters.FragViewpager
import com.example.sixpchat.databinding.ActivityMainBinding
import com.example.sixpchat.ui.Calls
import com.example.sixpchat.ui.Chats
import com.example.sixpchat.ui.Status
import com.google.firebase.auth.FirebaseAuth


@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var mauth :FirebaseAuth
    private lateinit var array:ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mauth = FirebaseAuth.getInstance()
        val viewPager=binding.fragviewpage

        array.add(Chats())
        array.add(Status())
        array.add(Calls())

        val adapter=FragViewpager(this,array,supportFragmentManager)
        viewPager.adapter=adapter

        binding.tab.setupWithViewPager(viewPager)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = MenuInflater(this)
        inflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        when(item.itemId){
            R.id.setting-> Toast.makeText(this,"Setting is clicked",Toast.LENGTH_SHORT).show()
            R.id.groupchat-> {
                val intent = Intent(this,GroupChat::class.java)
                startActivity(intent)

            }
            R.id.logout->{
                mauth.signOut()
                val intent = Intent(this,SignIn::class.java)
                startActivity(intent)
                finish()
            }

        }

    }
}
