package com.example.myapplicationrecyclerview.Services

import android.os.NetworkOnMainThreadException
import android.os.StrictMode
import com.example.myapplicationrecyclerview.Model.State
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class DataService {
    private val arrState: ArrayList<State> = ArrayList<State>()

    fun getNstateFromBstate(stateCode: String?): State? {
        val sURL =
            "https://restcountries.com/v2/alpha/$stateCode?fields=name,nativeName" // gets a state by code
        var s: State? = null
        // Connect to the URL using java's native library
        var url: URL? = null
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            url = URL(sURL)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            val request = url!!.openConnection() as HttpURLConnection
            request.connect()
            val jp = JsonParser() //from gson
            val root: JsonElement =
                jp.parse(InputStreamReader(request.content as InputStream)) //Convert the input stream to a json element
            val rootobj: JsonObject = root.getAsJsonObject()
            val entriesname: JsonElement = rootobj.get("name")
            val entriesnative: JsonElement = rootobj.get("nativeName")
            val nameR: String = entriesname.toString().replace("\"", "") // convert to pure string
            val nativen: String = entriesnative.toString().replace("\"", "")
            s = State(nameR, nativen)
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return s
    }

    fun getArrState(): ArrayList<State> {
        val sURL =
            "https://restcountries.com/v2/all?fields=name,nativeName,borders,flag" // get all states

        // Connect to the URL using java's native library
        var url: URL? = null
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            url = URL(sURL)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            val request = url!!.openConnection() as HttpURLConnection
            request.connect()

            // Convert to a JSON object to print data
            val jp = JsonParser() //from gson
            val root =
                jp.parse(InputStreamReader(request.content as InputStream)) //Convert the input stream to a json element
            val rootobj = root.asJsonArray //May be an array, may be an object.
            for (je in rootobj) {
                val obj = je.asJsonObject //since you know it's a JsonObject
                val entriesname = obj["name"] //will return members of your object
                val entriesnative = obj["nativeName"]
                val entriesborders = obj["borders"]
                val entriesflag = obj["flag"]
                val name = entriesname.toString().replace("\"", "")
                val nativen = entriesnative.toString().replace("\"", "")
                val flag = entriesflag.toString().replace("\"", "")
                val arrBorders = java.util.ArrayList<String>()
                var entriesbordersArray: JsonArray
                entriesbordersArray = if (entriesborders == null) {
                    JsonArray()
                } else {
                    entriesborders.asJsonArray
                }
                for (j in entriesbordersArray) {
                    val s = j.toString().replace("\"", "")
                    arrBorders.add(s)
                }
                arrState.add(State(name, arrBorders, nativen, flag))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NetworkOnMainThreadException) {
            e.printStackTrace()
        }
        return arrState
    }
}