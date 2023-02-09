package com.example.congresotfg.locationModule

import android.Manifest
import android.annotation.SuppressLint
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
import com.example.congresotfg.R
import com.example.congresotfg.common.utils.location.LocationApiService
import com.example.congresotfg.common.utils.RouteResponse
import com.example.congresotfg.databinding.FragmentLocationBinding
import com.example.congresotfg.mainModule.MainActivity
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

        map.clear()

        createMarker()

        val coordinates = LatLng(40.455062, -3.658961)
        val coordinates2 = LatLng(40.454439, -3.659287)
        val coordinates3 = LatLng(40.453928, -3.657694)
        val coordinates4 = LatLng(40.453433, -3.657464)
        val coordinates5 = LatLng(40.451414, -3.655766)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)
        val marker3 = MarkerOptions().position(coordinates3)
        val marker4 = MarkerOptions().position(coordinates4)
        val marker5 = MarkerOptions().position(coordinates5)

        map.addMarker(marker)
        map.addMarker(marker2)
        map.addMarker(marker3)
        map.addMarker(marker4)
        map.addMarker(marker5)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.455062, -3.658961))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.454439, -3.659287))

                    poly = map.addPolyline(ruta)

                }

                coordinates3 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.453928, -3.657694))

                    poly = map.addPolyline(ruta)

                }

                coordinates4 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.453433, -3.657464))

                    poly = map.addPolyline(ruta)

                }

                coordinates5 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.451414, -3.655766))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupAeropuerto() {

        map.clear()

        createMarker()

        val coordinates = LatLng(40.498703, -3.567602)

        val marker = MarkerOptions().position(coordinates)

        map.addMarker(marker)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.498703, -3.567602))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupHoteles() {

        map.clear()

        createMarker()

        val coordinates = LatLng(40.459654, -3.663128)
        val coordinates2 = LatLng(40.461518, -3.655844)
        val coordinates3 = LatLng(40.459880, -3.648536)
        val coordinates4 = LatLng(40.457184, -3.646727)
        val coordinates5 = LatLng(40.456732, -3.647800)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)
        val marker3 = MarkerOptions().position(coordinates3)
        val marker4 = MarkerOptions().position(coordinates4)
        val marker5 = MarkerOptions().position(coordinates5)

        map.addMarker(marker)
        map.addMarker(marker2)
        map.addMarker(marker3)
        map.addMarker(marker4)
        map.addMarker(marker5)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.459654, -3.663128))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.461518, -3.655844))

                    poly = map.addPolyline(ruta)

                }

                coordinates3 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.459880, -3.648536))

                    poly = map.addPolyline(ruta)

                }

                coordinates4 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.457184, -3.646727))

                    poly = map.addPolyline(ruta)

                }

                coordinates5 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.456732, -3.647800))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupHospitales() {

        map.clear()

        createMarker()

        val coordinates = LatLng(40.458034, -3.658426)
        val coordinates2 = LatLng(40.455188, -3.657655)
        val coordinates3 = LatLng(40.455171, -3.657678)
        val coordinates4 = LatLng(40.455084, -3.657859)
        val coordinates5 = LatLng(40.459938, -3.660753)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)
        val marker3 = MarkerOptions().position(coordinates3)
        val marker4 = MarkerOptions().position(coordinates4)
        val marker5 = MarkerOptions().position(coordinates5)

        map.addMarker(marker)
        map.addMarker(marker2)
        map.addMarker(marker3)
        map.addMarker(marker4)
        map.addMarker(marker5)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.458034, -3.658426))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.455188, -3.657655))

                    poly = map.addPolyline(ruta)

                }

                coordinates3 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.455171, -3.657678))

                    poly = map.addPolyline(ruta)

                }

                coordinates4 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.455084, -3.657859))

                    poly = map.addPolyline(ruta)

                }

                coordinates5 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.459938, -3.660753))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupFarmacias() {

        map.clear()

        createMarker()

        val coordinates = LatLng(40.454176, -3.659825)
        val coordinates2 = LatLng(40.459960, -3.659532)
        val coordinates3 = LatLng(40.460911, -3.662260)
        val coordinates4 = LatLng(40.451801, -3.656352)
        val coordinates5 = LatLng(40.451804, -3.656343)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)
        val marker3 = MarkerOptions().position(coordinates3)
        val marker4 = MarkerOptions().position(coordinates4)
        val marker5 = MarkerOptions().position(coordinates5)

        map.addMarker(marker)
        map.addMarker(marker2)
        map.addMarker(marker3)
        map.addMarker(marker4)
        map.addMarker(marker5)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.454176, -3.659825))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.459960, -3.659532))

                    poly = map.addPolyline(ruta)

                }

                coordinates3 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.460911, -3.662260))

                    poly = map.addPolyline(ruta)

                }

                coordinates4 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.451801, -3.656352))

                    poly = map.addPolyline(ruta)

                }

                coordinates5 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.451804, -3.656343))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupGasolineras() {

        map.clear()

        createMarker()

        val coordinates = LatLng(40.457454, -3.658100)
        val coordinates2 = LatLng(40.458697, -3.667597)
        val coordinates3 = LatLng(40.463280, -3.658131)
        val coordinates4 = LatLng(40.446781, -3.651977)
        val coordinates5 = LatLng(40.447611, -3.666193)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)
        val marker3 = MarkerOptions().position(coordinates3)
        val marker4 = MarkerOptions().position(coordinates4)
        val marker5 = MarkerOptions().position(coordinates5)

        map.addMarker(marker)
        map.addMarker(marker2)
        map.addMarker(marker3)
        map.addMarker(marker4)
        map.addMarker(marker5)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.457454, -3.658100))
                        .add(LatLng(40.455062, -3.658961))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.458697, -3.667597))

                    poly = map.addPolyline(ruta)

                }

                coordinates3 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.463280, -3.658131))

                    poly = map.addPolyline(ruta)

                }

                coordinates4 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.446781, -3.651977))

                    poly = map.addPolyline(ruta)

                }

                coordinates5 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.447611, -3.666193))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun setupTransportesPublicos() {

        map.clear()

        createMarker()

        val coordinates = LatLng(40.453698, -3.660445)
        val coordinates2 = LatLng(40.453453, -3.660526)
        val coordinates3 = LatLng(40.448593, -3.667439)
        val coordinates4 = LatLng(40.457125, -3.647044)
        val coordinates5 = LatLng(40.457814, -3.676979)

        val marker = MarkerOptions().position(coordinates)
        val marker2 = MarkerOptions().position(coordinates2)
        val marker3 = MarkerOptions().position(coordinates3)
        val marker4 = MarkerOptions().position(coordinates4)
        val marker5 = MarkerOptions().position(coordinates5)

        map.addMarker(marker)
        map.addMarker(marker2)
        map.addMarker(marker3)
        map.addMarker(marker4)
        map.addMarker(marker5)

        map.animateCamera(CameraUpdateFactory.zoomOut(), 700, null)

        map.setOnMarkerClickListener {

            poly?.remove()

            poly = null

            when(it.position) {

                coordinates -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.453698, -3.660445))

                    poly = map.addPolyline(ruta)

                }

                coordinates2 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.453453, -3.660526))

                    poly = map.addPolyline(ruta)

                }

                coordinates3 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.448593, -3.667439))

                    poly = map.addPolyline(ruta)

                }

                coordinates4 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.457125, -3.647044))

                    poly = map.addPolyline(ruta)

                }

                coordinates5 -> {

                    val ruta = PolylineOptions()
                        .add(LatLng(40.455982, -3.661534))
                        .add(LatLng(40.457814, -3.676979))

                    poly = map.addPolyline(ruta)

                }

            }

            true

        }

    }

    private fun createMarker() {

        val coordinates = LatLng(40.455982, -3.661534)

        val marker = MarkerOptions().position(coordinates).title("SEDE FEDEM Madrid")

        map.addMarker(marker)

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null)

    }

    // Devuelve True si el Permiso de Localización está activado comparandolo y False si no esta activado.
    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(fragmentContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

    @SuppressLint("MissingPermission")
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

    @SuppressLint("MissingPermission")
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

    @SuppressLint("MissingPermission")
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
                setupRestaurantes()
            }
            1L -> {
                setupAeropuerto()
            }
            2L -> {
                setupHoteles()
            }
            3L -> {
                setupHospitales()
            }
            4L -> {
                setupFarmacias()
            }
            5L -> {
                setupGasolineras()
            }
            6L -> {
                setupTransportesPublicos()
            }

        }

    }

}