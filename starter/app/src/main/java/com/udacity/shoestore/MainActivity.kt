package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.utils.log.ReleaseLog
import com.udacity.shoestore.view.login.LoginViewModel
import timber.log.Timber
import timber.log.Timber.DebugTree


class MainActivity : AppCompatActivity() {
    companion object {
        private val HIDE_MENU: Int = 1
        private val SHOW_MENU: Int = 0
    }

    private var mState: Int = SHOW_MENU
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
        setSupportActionBar(findViewById(R.id.my_toolbar))
        navController = this.findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
//        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
//            if (nd.id == nc.graph.startDestination) {
//                mState = HIDE_MENU; // setting state
//                invalidateOptionsMenu();
//            } else {
//                mState = SHOW_MENU
//                invalidateOptionsMenu();
//            }
//        }

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        if (mState == HIDE_MENU){
//            return false
//        }
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.menu_app_bar, menu)
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            ReleaseLog.log(priority, tag, message, t)
            if (t != null) {
                if (priority == Log.ERROR) {
                    ReleaseLog.logError(t)
                } else if (priority == Log.WARN) {
                    ReleaseLog.logWarning(t)
                }
            }
        }
    }
}
