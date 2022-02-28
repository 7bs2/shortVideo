package com.example.video3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mianViewPage.apply {
            adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount() = 3

                override fun createFragment(position: Int): Fragment = when (position) {
                    1 -> VideoFragment()
                    else -> ForFragment()
                }
            }

           setCurrentItem(1, false)
        }

        TabLayoutMediator(tabLayout, mianViewPage, object : TabLayoutMediator.TabConfigurationStrategy {
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
               tab.text = when (position) {
                   1 -> "video"
                   else -> "foo"
               }
            }
        }).attach()
    }
}