package com.example.congresotfg.common.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CorrutinaClass {

    fun executeAction(context: Context, block: suspend () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main){
                try {
                    block()
                }catch (e: UnknownHostException){
                    Log.i("conexion","No tienes conexión")
                    Toast.makeText(context,"No tienes conexión a internet",Toast.LENGTH_LONG).show()
                }catch (e2 : SocketException){
                    Toast.makeText(context,"Error en el servidor",Toast.LENGTH_LONG).show()
                } catch (e3: SocketTimeoutException) {
                    Toast.makeText(context,"Se ha pasado el tiempo de espera",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}