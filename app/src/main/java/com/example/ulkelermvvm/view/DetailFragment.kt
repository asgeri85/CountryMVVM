package com.example.ulkelermvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.example.ulkelermvvm.R
import com.example.ulkelermvvm.databinding.FragmentDetailBinding
import com.example.ulkelermvvm.util.downloadAPI
import com.example.ulkelermvvm.util.plcProgresbar
import com.example.ulkelermvvm.viewModel.DetailViewModel

class DetailFragment : Fragment() {
    private var _binding:FragmentDetailBinding?=null
    private val binding get() = _binding!!
    private val arguments :DetailFragmentArgs by navArgs()
    private var uuid=0
    private lateinit var  viewModel:DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uuid=arguments.id
        viewModel=ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDataRoom(uuid)
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it.let {
                binding.apply {
                    country=it
                    context?.let { c->
                        imageDetail.downloadAPI(it.flag, plcProgresbar(c))
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}