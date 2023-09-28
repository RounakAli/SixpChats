package com.example.sixpchat.adapters

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class FragViewpager(private val context: Context,
                    val list: ArrayList<Fragment>,
                    fm:FragmentManager):
    FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return TAB_TTILES[position]
    }

    companion object{
        val TAB_TTILES = arrayOf("Chats","Status","Calls")


}



}