package com.example.catapi

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.android.volley.Response
import kotlin.random.Random

import com.example.catapi.databinding.DisplayFragmentBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.jar.Attributes

class DisplayFragment() : Fragment() {

    private lateinit var binding : DisplayFragmentBinding

    private var Name = "null"
    private var temperament = " "
    private var origin = " "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DisplayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    fun updateDisplay(value : String) {
        //if value is =, then clear input, and put result in display
        Log.i("DisplayFragment", value)
        var catValue = Random.nextInt(1, 5)
        var imageName = "@drawable/cat" + catValue
        var resourceID = resources.getIdentifier(imageName, "drawable", context?.packageName)


        Response.Listener<String> { response ->
            var catsArray : JSONArray = JSONArray(response)

            //indices from 0 through catsArray.length()-1
            for(i in 0 until catsArray.length()) {
                //${} is to interpolate the string /
                // uses a string template
                var theCat: JSONObject = catsArray.getJSONObject(i)
                when (catValue) {
                    1 -> {
                        Log.i("MainActivity",
                            "Cat name: ${theCat.getString("name")}")
                        binding.CatName.setText(theCat.toString() + value)
                        binding.CatImage.setImageResource(resourceID)
                        binding.CatTemperament.setText(binding.CatTemperament.text.toString() + value)
                        binding.CatOrigin.setText(binding.CatOrigin.text.toString() + value)
                    }
                    2 -> {
                        Log.i("MainActivity",
                            "Cat name: ${theCat.getString("name")}")
                        binding.CatName.setText(theCat.toString() + value)
                        binding.CatImage.setImageResource(resourceID)
                        binding.CatTemperament.setText(binding.CatTemperament.text.toString() + value)
                        binding.CatOrigin.setText(binding.CatOrigin.text.toString() + value)
                    }
                    3 -> {
                        binding.CatName.setText(binding.CatName.text.toString() + value)
                        binding.CatImage.setImageResource(resourceID)
                        binding.CatTemperament.setText(binding.CatTemperament.text.toString() + value)
                        binding.CatOrigin.setText(binding.CatOrigin.text.toString() + value)
                    }
                    4 -> {
                        binding.CatName.setText(binding.CatName.text.toString() + value)
                        binding.CatImage.setImageResource(resourceID)
                        binding.CatTemperament.setText(binding.CatTemperament.text.toString() + value)
                        binding.CatOrigin.setText(binding.CatOrigin.text.toString() + value)
                    }
                    5 -> {
                        binding.CatName.setText(binding.CatName.text.toString() + value)
                        binding.CatImage.setImageResource(resourceID)
                        binding.CatTemperament.setText(binding.CatTemperament.text.toString() + value)
                        binding.CatOrigin.setText(binding.CatOrigin.text.toString() + value)
                    }
                }
            }
        }

    }
}
