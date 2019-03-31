package com.example.tkwest3774.navigationdrawer

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {
    override fun onDateSet(view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val str = String.format(Locale.US, "%d/%d/%d", year, monthOfYear+1, dayOfMonth)

    }
    fun showDatePickerDialog(v: View){
        val newFragment=DatePicker()
        newFragment.show(supportFragmentManager,"datepicker")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    //ユーザーリストの取得と画面への反映
    private fun getUserlist(userlist:Menu){
        userlist.add(0,0,0,"ユーザー１")
        menuInflater.inflate(R.menu.userlist,userlist)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? =null
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home-> {
                // Handle the home action
                fragment=HomeFragment()
            }
            R.id.nav_help-> {
                fragment=HelpFragment()
            }
            R.id.nav_training -> {
                // Handle the training action
                fragment=TrainingFragment()
            }
            R.id.nav_schedule -> {
                fragment=ScheduleFragment()
            }
            R.id.nav_share -> {

            }
            R.id.nav_meal-> {
                fragment=MealFragment()

            }
        }
        //replace the fragment
        if (fragment!=null){
            val ft=supportFragmentManager.beginTransaction()
            ft.replace(R.id.contents,fragment)
            ft.commit()

        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
