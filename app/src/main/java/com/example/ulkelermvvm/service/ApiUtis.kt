package com.example.ulkelermvvm.service

class ApiUtis {
    companion object{
        private const val baseURl="https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/"

        fun getCountryDAO():CountryAPI{
            return RetrofitClient.getClient(baseURl).create(CountryAPI::class.java)
        }
    }
}