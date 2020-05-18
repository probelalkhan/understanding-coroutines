package net.simplifiedcoding.understandingcoroutines.data.network

import net.simplifiedcoding.understandingcoroutines.data.models.Movie
import net.simplifiedcoding.understandingcoroutines.data.models.QuotesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("mvvm/quotes")
    suspend fun getQuotes(): Response<QuotesResponse>

    @GET("recyclerview/movies")
    suspend fun getMovies(): Response<List<Movie>>

//    @GET("quotes")
//    fun getMovies(): Call<QuotesResponse>

    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://api.simplifiedcoding.in/course-apis/")
                .build()
                .create(MyApi::class.java)
        }
    }
}
