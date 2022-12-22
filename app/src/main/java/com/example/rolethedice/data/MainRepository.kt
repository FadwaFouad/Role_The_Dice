package com.example.rolethedice.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.rolethedice.R
import com.example.rolethedice.data.Quote.QuoteList
import com.example.rolethedice.data.Quote.QuotesApi
import com.example.rolethedice.data.Quote.Result
import com.example.rolethedice.data.Quote.ResultDatabase
import com.example.rolethedice.data.monster.FileHelper
import com.example.rolethedice.data.monster.Monster
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainRepository(val app: Application) {
    var resultData = MutableLiveData<List<Result>>()

    val resultDao = ResultDatabase.getDatabase(app).resultDao()

    private val listType = Types.newParameterizedType(
        List::class.java, Monster::class.java
    )

    init {
        networkAvailable()
        Log.i(LOG_TAG, networkAvailable().toString())

        CoroutineScope(Dispatchers.IO).launch {
            val data = resultDao.getAll()
            if (data.isEmpty())
                callWebService()
            else {
                resultData.postValue(data)
                withContext(Dispatchers.Main) {
                    Toast.makeText(app, "Using local data", Toast.LENGTH_LONG).show()
                }
            }

        }

        //geMonsterData()
    }

    @WorkerThread
    suspend fun callWebService() {
        withContext(Dispatchers.Main) {
            Toast.makeText(app, "Using remote data", Toast.LENGTH_LONG).show()
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(WEB_URl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(QuotesApi::class.java)
        val serviceData: QuoteList = service.getQuotes().body()!!
        resultData.postValue(serviceData.results)
        Log.i("data", serviceData.toString())

       //  insert into database
        resultDao.deleteAll()
        resultDao.insertResults(resultData.value!!)


    }

    private fun geMonsterData() {
        val text = FileHelper.getTextFromRes(app, R.raw.monster_data)
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)
        // monsterData.value = adapter.fromJson(text)
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val ConnectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = ConnectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false

    }
}