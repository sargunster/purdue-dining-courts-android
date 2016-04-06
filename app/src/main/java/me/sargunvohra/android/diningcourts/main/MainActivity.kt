package me.sargunvohra.android.diningcourts.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import me.sargunvohra.android.diningcourts.R
import me.sargunvohra.android.diningcourts.base.BaseActivity
import me.sargunvohra.android.diningcourts.config.dagger.MainModule
import org.jetbrains.anko.onClick
import java.util.*

class MainActivity : BaseActivity() {

    override fun getLayoutId() = R.layout.activity_main

    override fun getActivityScopeModules() = listOf(MainModule())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup action bar
        setSupportActionBar(toolbar)

        // setup view pager
        viewPager.adapter = MainPagerAdapter(supportFragmentManager, Date())
        tabLayout.setupWithViewPager(viewPager)

        // setup fab toolbar
        fabToolbar.setFab(fab)
        fab.onClick {
            fabToolbar.expandFab()
        }

        // setup fab toolbar with view pager
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) = fabToolbar.slideInFab()
        })
    }
}