package ru.tanom.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ru.tanom.R
import ru.tanom.base.view.BaseActivity
import ru.tanom.common.ProgressManager
import ru.tanom.common.gone
import ru.tanom.common.visible

class MainActivity : BaseActivity(), ProgressManager {

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return
        val navController = host.navController
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav?.setupWithNavController(navController)
        bottomNav?.setOnNavigationItemSelectedListener{
            item -> onNavDestinationSelected(item, Navigation.findNavController(this, R.id.nav_host_fragment))
        }
    }

    fun hideBottomNavigation(){
        bottom_navigation.gone()
    }

    fun showBottomNavigation(){
        bottom_navigation.visible()
    }

    override fun showProgress() {
        main_progress.visible()
    }

    override fun hideProgress() {
        main_progress.gone()
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = this.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
