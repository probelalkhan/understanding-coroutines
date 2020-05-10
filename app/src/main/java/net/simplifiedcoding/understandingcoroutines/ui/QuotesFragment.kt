package net.simplifiedcoding.understandingcoroutines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import net.simplifiedcoding.understandingcoroutines.data.models.QuotesResponse
import net.simplifiedcoding.understandingcoroutines.data.network.MyApi
import net.simplifiedcoding.understandingcoroutines.databinding.FragmentQuotesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuotesFragment : Fragment() {

    private val quotesAdapter by lazy { QuotesAdapter() }
    private lateinit var binding: FragmentQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.recyclerviewQuotes.adapter = quotesAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            getQuotes()
        }
    }

    private suspend fun getQuotes() {
        val response = MyApi().getMovies()
        quotesAdapter.quotes = response.body()?.quotes
    }

//    private fun getQuotes() {
//        MyApi().getMovies().enqueue(object : Callback<QuotesResponse> {
//            override fun onFailure(call: Call<QuotesResponse>, t: Throwable) {
//                //@Todo handle failure here
//            }
//
//            override fun onResponse(
//                call: Call<QuotesResponse>,
//                response: Response<QuotesResponse>
//            ) {
//                quotesAdapter.quotes = response.body()?.quotes
//            }
//        })
//    }
}
