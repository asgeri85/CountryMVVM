package com.example.ulkelermvvm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ulkelermvvm.adapters.CountryAdapter
import com.example.ulkelermvvm.databinding.FragmentHomeBinding
import com.example.ulkelermvvm.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel:HomeViewModel
    private lateinit var adapter:CountryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding= FragmentHomeBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel= ViewModelProviders.of(this).get(HomeViewModel::class.java)
        adapter= CountryAdapter(requireContext(), arrayListOf())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.refreshData()
        binding.apply {
            rvHome.layoutManager=LinearLayoutManager(context)
            rvHome.setHasFixedSize(true)
            rvHome.adapter=adapter
            refreshlayout.setOnRefreshListener {
                rvHome.visibility=View.GONE
                viewModel.getDataAPI()
                refreshlayout.isRefreshing=false
            }
        }
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.rvHome.visibility=View.VISIBLE
                adapter.updateList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.apply {
                    textViewHomeError.visibility=View.VISIBLE
                    rvHome.visibility=View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.apply {
                    progressHome.visibility=View.VISIBLE
                    rvHome.visibility=View.GONE
                }
            }else{
                binding.apply {
                    progressHome.visibility=View.GONE
                    rvHome.visibility=View.VISIBLE
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}