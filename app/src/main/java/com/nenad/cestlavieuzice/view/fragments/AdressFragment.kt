package com.nenad.cestlavieuzice.view.fragments

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.databinding.FragmentAdressBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AdressFragment : Fragment(), OnMapReadyCallback {
    lateinit var mBinding: FragmentAdressBinding
    var fusedLocationProviderClient: FusedLocationProviderClient? = null //last known location
    var currentMarker: Marker? = null
    private lateinit var mMap: GoogleMap
    var currentLocation: Location? = null
    lateinit var latLng: LatLng

    //lateinit var action: BurgersFragmentDirections.ActionBurgersFragmentToOverviewFragment
     lateinit var action: AdressFragmentDirections.ActionAdressFragmentToOrderFragment2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_adress, container, false)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE



        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        fetchLocation()



        val searchView = mBinding.etSearch

        searching(searchView)


        setOnClickListeners()


    }

    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val location = search.query.toString()

                var list: List<Address>? = null

                if (location != null || !location.equals("")) {
                    val geocoder = Geocoder(requireContext())
                    try {
                        list = geocoder.getFromLocationName(location, 1)

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    val address: Address = list!!.get(0)
                    latLng = LatLng(address.latitude, address.longitude)
                    mMap.addMarker(MarkerOptions().position(latLng).title(location))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))

                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fr) as SupportMapFragment

        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap



    }

    fun setOnClickListeners() {
      mBinding.btnSave.setOnClickListener {
//          if (currentLocation?.latitude != 43.8556 && currentLocation?.longitude != 19.8425) {
//              Toast.makeText(requireContext(), "Porudžbine su moguće samo u Užicu", Toast.LENGTH_SHORT).show()
//          } else {
              val bundle: Bundle? = bundleOf("address" to LatLng(latLng.latitude, latLng.longitude))
              bundle?.putString("address", getAdress(latLng.latitude, latLng.longitude))
             try {
                 bundle?.putDouble("lat", latLng.latitude)
                 bundle?.putDouble("long", latLng.longitude)
                 Log.e("Latitude saved", latLng.latitude.toString())
                 Log.e("Longitude saved", latLng.longitude.toString())


             } catch (e: Exception) {
                 e.printStackTrace()
             }

          if (bundle == null) {
              Toast.makeText(requireContext(), "Unesite adresu", Toast.LENGTH_SHORT).show()
          } else {


//              //findNavController().popBackStack(R.id.adressFragment, true)
//              findNavController().navigate(R.id.orderFragment, bundle)

              findNavController().popBackStack(R.id.adressFragment, true)
              findNavController().navigate(R.id.orderFragment, bundle)

          }



//          findNavController().popBackStack(R.id.adressFragment, true)
//          findNavController().navigate(R.id.orderFragment, bundle)





      }
        mBinding.btnClose.setOnClickListener {
            this.findNavController().popBackStack()
        }
    }

    private fun getAdress(lat: Double, long: Double): String? {
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val adresses = geoCoder.getFromLocation(lat, long, 1)
        return adresses[0].getAddressLine(0)
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ), 1000
            )
            return

        }
        val task = fusedLocationProviderClient?.lastLocation
        task?.addOnSuccessListener { location ->

            this.currentLocation = location
            if (location != null) {
                val mapFragment =
                    childFragmentManager.findFragmentById(R.id.map_fr) as SupportMapFragment

                mapFragment.getMapAsync(this)


            }

        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            }
        }
    }


}