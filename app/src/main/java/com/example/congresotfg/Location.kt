package com.example.congresotfg

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.congresotfg.databinding.FragmentLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Location : Fragment(), OnMapReadyCallback, OnMyLocationButtonClickListener, OnMyLocationClickListener,
    AdapterView.OnItemClickListener {

    companion object {

        const val REQUEST_CODE_LOCATION = 0

    }

    private lateinit var binding: FragmentLocationBinding

    private lateinit var fragmentContext: Context

    private var mainActivity: MainActivity? = null

    private lateinit var map: GoogleMap

    private var start: String = ""
    private var end: String = "-3.661534,40.455982"

    private var poly: Polyline? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentLocationBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        mainActivity = activity as? MainActivity

        setupDropDown()

        val mapFragment = childFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment

        setupRoutes()

        mapFragment.getMapAsync(this)

        return binding.root

    }

    private fun setupDropDown() {

        val lugares = resources.getStringArray(R.array.array_places)

        val arrayAdapter = ArrayAdapter(fragmentContext, R.layout.dropdown_item, lugares)

        binding.actv.setAdapter(arrayAdapter)
        binding.actv.onItemClickListener = this@Location

    }

    private fun setupRoutes() {

        binding.btnCalcularRuta.setOnClickListener {

            start = ""

            poly?.remove()

            poly = null

            Toast.makeText(fragmentContext, "Selecciona punto de salida", Toast.LENGTH_SHORT).show()

            val salida = binding.tietSalida

            if (::map.isInitialized) {

                map.setOnMapClickListener {

                    if (start.isEmpty()) {

                        start = "${it.longitude},${it.latitude}"

                        salida.setText(start)

                        createRoute()

                    }

                }

            }

        }

    }

    override fun onMapReady(googleMap: GoogleMap) {

        map = googleMap

        createMarker()

        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)

        enabledLocation()

    }

    private fun setupRestaurantes() {

        val coordinates = LatLng(40.455062, -3.658961)
        val coordinates2 = LatLng(40.454439, -3.659287)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)

        map.addMarker(marker)
        map.addMarker(marker2)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.455062, -3.658961))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.454439, -3.659287))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupAeropuerto() {



    }

    private fun setupHoteles() {



    }

    private fun createMarker() {

        val coordinates = LatLng(40.455982, -3.661534)

        val marker = MarkerOptions().position(coordinates).title("SEDE FEDEM Madrid")

        map.addMarker(marker)

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null)

    }

    // Devuelve True si el Permiso de Localización está activado comparandolo y False si no esta activado.
    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(fragmentContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    private fun enabledLocation() {

        if(!::map.isInitialized) return

        if (isLocationPermissionGranted()) {

            // Sí

            map.isMyLocationEnabled = true

        } else {

            // No

            requestLocationPermission()

        }

    }

    // Pedir Permisos
    private fun requestLocationPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity!!, Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(fragmentContext, "Ve a Ajustes y activa los Permisos", Toast.LENGTH_SHORT).show()

        } else {

            ActivityCompat.requestPermissions(mainActivity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode) {

            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                map.isMyLocationEnabled = true

            } else {

                Toast.makeText(fragmentContext, "Para activar la Localización ve a Ajustes y activa los Permisos", Toast.LENGTH_SHORT).show()

            }

            else -> {}

        }

    }

    override fun onResume() {

        super.onResume()

        if(!::map.isInitialized) return

        if (!isLocationPermissionGranted()) {

            map.isMyLocationEnabled = false

            Toast.makeText(fragmentContext, "Para activar la Localización ve a Ajustes y activa los Permisos", Toast.LENGTH_SHORT).show()

        }

    }

    override fun onMyLocationButtonClick(): Boolean {

        Toast.makeText(fragmentContext, "Botón Pulsado", Toast.LENGTH_SHORT).show()

        return false

    }

    override fun onMyLocationClick(p0: Location) {

        Toast.makeText(fragmentContext, "Estás en: ${p0.latitude}, ${p0.longitude}", Toast.LENGTH_SHORT).show()

    }

    private fun createRoute() {

        CoroutineScope(Dispatchers.IO).launch {

            val call = getRetrofit().create(LocationApiService::class.java).getRoute("5b3ce3597851110001cf6248ba97966649f4424b892d14d39384208a", start, end)

            if (call.isSuccessful) {

                Log.i("Route", "OK")

                drawRoute(call.body())

            } else {

                Log.i("Route", "KO")

            }

        }

    }

    private fun drawRoute(routeResponse: RouteResponse?) {

        val polylineOptions = PolylineOptions()

        routeResponse?.features?.first()?.geometry?.coordinates?.forEach {

            polylineOptions.add(LatLng(it[1], it[0]))

        }

        mainActivity?.runOnUiThread {

            poly = map.addPolyline(polylineOptions)

        }

    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder().baseUrl("https://api.openrouteservice.org/").addConverterFactory(GsonConverterFactory.create()).build()

    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        val item = parent?.getItemIdAtPosition(position)

        when(item) {

            0L -> {
                Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()
                setupRestaurantes()
            }
            1L -> Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()
            2L -> Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()
            3L -> Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()
            4L -> Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()
            5L -> Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()
            6L -> Toast.makeText(fragmentContext, "OK", Toast.LENGTH_SHORT).show()

        }

    }

}