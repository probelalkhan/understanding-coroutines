package net.simplifiedcoding.understandingcoroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.simplifiedcoding.understandingcoroutines.data.models.Quote
import net.simplifiedcoding.understandingcoroutines.data.network.MyApi

class QuoteViewModel : ViewModel() {

    val quotes: LiveData<List<Quote>?> = MutableLiveData()

    init {
        viewModelScope.launch {
            quotes as MutableLiveData
            quotes.value = getQuotes()
        }
    }

    private suspend fun getQuotes(): List<Quote>? {
        return withContext(Dispatchers.IO) {
            MyApi().getQuotes().body()?.quotes
        }
    }
}