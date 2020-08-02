package com.example.mynews.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mynews.NewsApplication
import com.example.mynews.R
import com.example.mynews.factory.ViewModelProviderFactory
import com.example.mynews.utils.Resource
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : DaggerFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val TAG = HomeFragment.javaClass.simpleName

    private lateinit var homeViewModel: HomeViewModel
    @set:Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpDagger()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProviders.of(this, providerFactory)[HomeViewModel::class.java]
        observeViewModel()
    }

    private fun observeViewModel() {
        homeViewModel.getHomeRP().observe(this, Observer {
            when(it.status){
                Resource.AuthStatus.LOADING-> Log.e(TAG, "loading.....")
                Resource.AuthStatus.AUTHENTICATED->{
                    val response = it.data
                    Log.e(TAG, response?.results.toString())
                }
                Resource.AuthStatus.ERROR-> Log.e(TAG, "error.....")
            }
        })
    }

    private fun setUpDagger() {

    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}