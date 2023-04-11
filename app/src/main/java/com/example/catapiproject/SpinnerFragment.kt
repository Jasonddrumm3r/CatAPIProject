package com.example.catapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import com.example.catapi.databinding.SpinnerFragmentBinding

class SpinnerFragment : Fragment() {


    private lateinit var binding: SpinnerFragmentBinding
    var activityCallback : SpinnerFragment.SpinnerListener ?= null

    interface SpinnerListener {
        fun onSpinnerClick(text : String)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SpinnerFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onAttach(context : Context) {
        super.onAttach(context)

        try {
            activityCallback = context as MainActivity
            Log.i("SpinnerFragment", activityCallback.toString())
            Log.i("SpinnerFragment", "In the try after setting callback")
        }
        catch (e : ClassCastException) {
            throw ClassCastException(context.toString() + " must implement SpinnerListener")
        }
    }


}
