package activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.natchanon.pscmenu.R

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var maintoolbar: Toolbar
    private lateinit var mainDrawerLayout: DrawerLayout
    private lateinit var mainNavigationView: NavigationView
    private lateinit var mainBottomNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        maintoolbar = findViewById(R.id.main_toolbar)
        mainDrawerLayout = findViewById(R.id.drawer_layout)
        mainNavigationView = findViewById(R.id.main_navigation_view)
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_view)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        val topLevelDestinationIds = setOf(
            R.id.homeFragment,
            R.id.addProductFragment,
            R.id.notificationFragment,
            R.id.accountFragment
        )

        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinationIds)
            .setDrawerLayout(mainDrawerLayout)
            .build()

        setSupportActionBar(maintoolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        mainNavigationView.setupWithNavController(navController)
        mainBottomNavigationView.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}