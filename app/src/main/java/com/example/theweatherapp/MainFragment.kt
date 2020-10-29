package com.example.theweatherapp

import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import im.delight.android.location.SimpleLocation
import kotlinx.android.synthetic.main.fragment_main.view.*
import java.util.jar.Manifest


class MainFragment : Fragment(),View.OnClickListener{


    var location: SimpleLocation? = null
    var latitude: Double? = null
    var longitude: Double? = null
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentView = inflater.inflate(R.layout.fragment_main, container, false)
        val layout=fragmentView.mainLyout as MotionLayout
        layout.transitionToEnd()

        fragmentView.btnSelectCity.setOnClickListener {
            location = SimpleLocation(context)
            if (!location!!.hasLocationEnabled()) {
                Toast.makeText(context, "You have to open GPS", Toast.LENGTH_SHORT).show()
                SimpleLocation.openSettings(context)
            } else {
                if (ContextCompat.checkSelfPermission(
                        this.requireContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this.requireActivity(),
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        60
                    )
                } else {
                    location = SimpleLocation(context)
                    latitude = location?.latitude
                    longitude = location?.longitude



                }
            }
        }
        return fragmentView

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==60)
        {
            if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                location= SimpleLocation(context)
                latitude=location?.latitude
                longitude=location?.longitude


            }else{
                Toast.makeText(context,"Please, allow to GPS",Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btnSelectCity).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnSelectCity -> navController.navigate(R.id.action_mainFragment_to_cityFragment)
        }
    }

}