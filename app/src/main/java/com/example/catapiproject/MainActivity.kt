package com.example.catapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.example.catapi.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), SpinnerFragment.SpinnerListener, OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var spinnerData = ArrayList<String>()
        spinnerData.add("Cat1")
        spinnerData.add("Cat2")
        spinnerData.add("Cat3")
        spinnerData.add("Cat4")
        spinnerData.add("Cat5")

        binding = ActivityMainBinding.inflate(layoutInflater)

        var arrayAdapter = ArrayAdapter<String>(this,
            R.layout.activity_main, R.id.theTextView,
            spinnerData)

        binding.theSpinner.adapter = arrayAdapter

        setContentView(binding.root)

        binding.theSpinner.onItemSelectedListener = this
        
        supportActionBar?.hide()


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        binding.theTextView.text = binding.theSpinner.selectedItem.toString()
        printCatData()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onSpinnerClick(value : String) {
        val displayFragment = supportFragmentManager.findFragmentById(R.id.outputFragmentView) as DisplayFragment
        displayFragment.updateDisplay(value)
    }

    // method to interact with API
    fun printCatData() {
        var catUrl = "https://api.thecatapi.com/v1/breeds" +
                "?api_key=live_TtTUXgkBvL1j5aJP5B9dVD44hilKHHvJhetLptxeifoUveVzXcDQi6fOY5DpavSA"

        val queue = Volley.newRequestQueue(this)

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, catUrl,
            Response.Listener<String> { response ->
                var catsArray : JSONArray = JSONArray(response)

                //indices from 0 through catsArray.length()-1
                for(i in 0 until catsArray.length()) {
                    //${} is to interpolate the string /
                    // uses a string template
                    var theCat : JSONObject = catsArray.getJSONObject(i)

                    //now get the properties we want:  name, temperament, and origin
                    Log.i("MainActivity",
                        "Cat name: ${theCat.getString("name")}")
                    Log.i("MainActivity",
                        "Cat temperament: ${theCat.getString("temperament")}")
                    Log.i("MainActivity",
                        "Cat origin: ${theCat.getString("origin")}")
                }//end for
            },
            Response.ErrorListener {
                Log.i("MainActivity", "That didn't work!")
            })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
    }//end printCatData

}
