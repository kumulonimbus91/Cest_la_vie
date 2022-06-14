package com.nenad.cestlavieuzice.view.fragments

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isEmpty
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.nenad.cestlavieuzice.R
import com.nenad.cestlavieuzice.adapter.DishesAdapter
import com.nenad.cestlavieuzice.database.model.Dish
import com.nenad.cestlavieuzice.database.model.Order
import com.nenad.cestlavieuzice.databinding.FragmentOrderBinding
import com.nenad.cestlavieuzice.viewmodel.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class OrderFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mBinding: FragmentOrderBinding
    lateinit var dishesAdapter: DishesAdapter
    lateinit var orderL: MutableList<Dish>
    val viewModel: ViewModel by activityViewModels<ViewModel>()
    var orderAg: Order? = arguments?.getParcelable<Order>("order")

    private lateinit var mMap: GoogleMap
    var fusedLocationProviderClient: FusedLocationProviderClient? = null //last known location
    var currentMarker: Marker? = null
    var currentLocation: Location? = null

    var mAddress: String? = arguments?.getString("address")
    var mLat: Double? = arguments?.getDouble("lat")
    var mLong: Double? = arguments?.getDouble("long")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_order, container, false)
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE






        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpRv()
        setUpClickListeners()
        setUpClickListenerRv()




        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()











        orderAg = arguments?.getParcelable("order")

        mAddress = arguments?.getString("address")

        mLat = arguments?.getDouble("lat")

        mLong = arguments?.getDouble("long")


        if (orderAg?.dishes == null) {
            viewModel.dishes.observe(viewLifecycleOwner, Observer { dishes ->
                dishesAdapter.differ.submitList(dishes)
                var total: Int = 0
                for (i in 0 until dishes.size) {

                    if (dishes[i].priceSmall != null) {
                        total += dishes[i].priceSmall!!
                    } else if (dishes[i].priceBig != null) {
                        total += dishes[i].priceBig!!
                    }






                }



                orderL = dishes.toMutableList()
                mBinding.fullPrice.text = total.toString()






                if (dishes.isEmpty()) {
                    mBinding.etEmpty.visibility = View.VISIBLE
                    mBinding.sadSmiley.visibility = View.VISIBLE
                    mBinding.rvBasket.visibility = View.INVISIBLE
                } else {
                    mBinding.etEmpty.visibility = View.INVISIBLE
                    mBinding.sadSmiley.visibility = View.INVISIBLE
                    mBinding.rvBasket.visibility = View.VISIBLE
                }


            })


        } else {
            viewModel.deleteAllDishes()

            for (i in 0 until orderAg!!.dishes.size) {
                viewModel.insertDish(orderAg!!.dishes[i])
            }

            viewModel.dishes.observe(viewLifecycleOwner, Observer { dishes ->
                dishesAdapter.differ.submitList(dishes)

                var total: Int = 0
                for (i in 0 until dishes.size) {

                    if (dishes[i].priceSmall != null) {
                        total += dishes[i].priceSmall!!
                    } else if (dishes[i].priceBig != null) {
                        total += dishes[i].priceBig!!
                    }


                }

                orderL = orderAg!!.dishes.toMutableList()
                mBinding.fullPrice.text = total.toString()


            })


        }









        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE


    }


    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<ViewGroup>(R.id.ll_go).visibility = View.GONE
    }

    fun setUpRv() {
        dishesAdapter = DishesAdapter()
        mBinding.rvBasket.apply {
            adapter = dishesAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    fun setUpClickListeners() {
        mBinding.deleteAll.setOnClickListener {
            alertDialog()


        }
        mBinding.btnClose.setOnClickListener {
            if (orderAg?.dishes != null) {
                viewModel.deleteAllDishes()
                this.findNavController().popBackStack()
            } else if (mLat != 0.0 && mLong != 0.0 && mAddress != null) {
                //findNavController().popBackStack(R.id.adressFragment, true)
                    findNavController().popBackStack(R.id.orderFragment, true)
                this.findNavController().popBackStack()
            } else {
                this.findNavController().popBackStack()
            }

        }

        mBinding.btnChangeAdress.setOnClickListener {
            //   findNavController().popBackStack(R.id.orderFragment, true)
            this.findNavController().navigate(R.id.adressFragment)
        }

        mBinding.orderBtn.setOnClickListener {
            viewModel.deleteAllDishes()

            val order = Order(null, orderL, mBinding.fullPrice.text.toString().toInt())

            if (mBinding.rvBasket.isEmpty()) {
                Toast.makeText(requireContext(), "Korpa je prazna", Toast.LENGTH_SHORT).show()
            } else if (orderAg?.dishes == null) {

                viewModel.insertOrder(order)

            } else if (mBinding.rvBasket.size == orderAg?.dishes?.size) {

                viewModel.insertOrder(order)
                viewModel.deleteOrder(orderAg!!)
                Toast.makeText(requireContext(), "Porudzbina je spremna", Toast.LENGTH_SHORT).show()

            } else {
                for (i in 0 until dishesAdapter.differ.currentList.size) {
                    orderL.removeAll(orderAg!!.dishes)
                    orderL.add(dishesAdapter.differ.currentList[i])

                }

                viewModel.insertOrder(order)

                Toast.makeText(requireContext(), "Porudzbina je spremna", Toast.LENGTH_SHORT).show()
            }


            Toast.makeText(requireContext(), "Porudzbina je spremna", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpClickListenerRv() {
        dishesAdapter.setOnClickListener(object : DishesAdapter.MyClickListener {

            override fun onDelete(p: Dish) {

                viewModel.deleteDish(p)


            }


            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }


        })


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (mLat != 0.0 && mLong != 0.0 && mAddress != null) {
            val latlong = LatLng(mLat!!, mLong!!)
            drawMarker(latlong)

        } else {
            val latlong = LatLng(currentLocation?.latitude!!, currentLocation?.longitude!!)
            drawMarker(latlong)

        }


    }

    private fun drawMarker(latLng: LatLng) {
        val markerOption = MarkerOptions().position(latLng).title("I am here")
            .snippet(getAdress(latLng.latitude, latLng.longitude)).draggable(true)
        mBinding.adressTv.text = getAdress(latLng.latitude, latLng.longitude).toString()
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
        currentMarker = mMap.addMarker(markerOption)
        currentMarker?.showInfoWindow()


    }

    private fun getAdress(lat: Double, long: Double): String? {
        val geoCoder = Geocoder(requireContext(), Locale.getDefault())
        val adresses = geoCoder.getFromLocation(lat, long, 1) // problem is somewhere here
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

        if (mLat != 0.0 && mLong != 0.0 && mAddress != null) {
            val task = fusedLocationProviderClient?.lastLocation
            task?.addOnSuccessListener { location ->
                currentLocation?.latitude = mLat!!
                currentLocation?.longitude = mLong!!
                this.currentLocation = location
                if (location != null) {
                    val mapFragment =
                        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

                    mapFragment.getMapAsync(this)


                }
            }

        } else {
            val task = fusedLocationProviderClient?.lastLocation
            task?.addOnSuccessListener { location ->

                this.currentLocation = location
                if (location != null) {
                    val mapFragment =
                        childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

                    mapFragment.getMapAsync(this)


                }

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

    fun alertDialog() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity())
        builder.setTitle("Da li želite da ispraznite korpu?")
        builder.setIcon(R.drawable.ic_baseline_delete_24)

        builder.setPositiveButton("Da") { dialogInterface, _ ->
            viewModel.deleteAllDishes()

        }
        //performing negative action
        builder.setNegativeButton("Ne") { dialogInterface, which ->
            dialogInterface.dismiss() // Dialog will be dismissed
        }

        // Create the AlertDialog
        val alertDialog: androidx.appcompat.app.AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false) // Will not allow user to cancel after clicking on remaining screen area.
        alertDialog.show()  // show the dialog to UI


    }
    fun <T> hasDuplicates(arr: List<Dish>): Boolean {
        return arr.size != arr.distinct().count();


    }


}


