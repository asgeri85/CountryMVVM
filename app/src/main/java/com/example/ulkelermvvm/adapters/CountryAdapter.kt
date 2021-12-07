package com.example.ulkelermvvm.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ulkelermvvm.R
import com.example.ulkelermvvm.databinding.CardCountryBinding
import com.example.ulkelermvvm.model.Country
import com.example.ulkelermvvm.util.downloadAPI
import com.example.ulkelermvvm.util.plcProgresbar
import com.example.ulkelermvvm.view.HomeFragmentDirections

class CountryAdapter(var mContext: Context,var ulkeler:ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryHolder>() {
    inner class CountryHolder(var cardCountryBinding: CardCountryBinding):RecyclerView.ViewHolder(cardCountryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val layout=DataBindingUtil.inflate<CardCountryBinding>(LayoutInflater.from(mContext),R.layout.card_country,parent,false)
        return CountryHolder(layout)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val country=ulkeler[position]
        holder.cardCountryBinding.country=country
        holder.cardCountryBinding.imageViewCard.downloadAPI(country.flag, plcProgresbar(mContext))
        holder.cardCountryBinding.card.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(country.uuid))
        }
    }

    override fun getItemCount(): Int {
        return ulkeler.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(liste:List<Country>){
        ulkeler.clear()
        ulkeler.addAll(liste)
        notifyDataSetChanged()
    }
}