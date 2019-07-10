package com.kzaf.weatherforecastmvvm.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.kzaf.weatherforecastMVVM.R
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

private const val MY_PERMISSON_ACCESS_COARSE_LOCATION = 1

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()
    private val fusedLocationProviderClient: FusedLocationProviderClient by instance()

    private val locationCallback = object: LocationCallback(){
        override fun onLocationResult(p0: LocationResult?) {
            super.onLocationResult(p0)
        }
    }

    private lateinit var navController: NavController


/*
    fun onomaSynarthshs (x: Int) : Int
    {
        val e = x + 1;
        return e
    }

    var a = onomaSynarthshs(1)

    var k : (Int) -> Int =  { x -> x + 1 }
    val t = k(1)
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar) // Set out custom toolbar

        // Set our custom bottom navigation bar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)

        requestLocationPermission()

        if (hasLocationPermission()){
            bindLocationManager()
        } else {
            requestLocationPermission()
        }

    }

    private fun bindLocationManager(){
        LifecycleBoundLocationManager(this, fusedLocationProviderClient, locationCallback)
    }

    // Setup the action of the back arrow (Top left in toolbar)
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null, navController) // This will navigate back
    }

    private fun requestLocationPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), MY_PERMISSON_ACCESS_COARSE_LOCATION)
    }

    private fun hasLocationPermission(): Boolean{
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == MY_PERMISSON_ACCESS_COARSE_LOCATION){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                bindLocationManager()
            } else {
                Toast.makeText(this, "Please set location manually in settings", Toast.LENGTH_LONG).show()
            }
        }
    }

}
