package zw.co.mobility.gads.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface GadsService {
    @GET("api/hours")
    fun learningLeaders(): Call<List<Learner?>?>?
}

class Learner(val name: String, val hours: Int, val country: String, val badgeUrl: String) {

}

fun createGadsService (): GadsService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://gadsapi.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: GadsService = retrofit.create<GadsService>(GadsService::class.java)
    return service
}