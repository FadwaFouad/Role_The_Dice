package com.example.rolethedice.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.rolethedice.data.MainRepository

class mainViewModel (app : Application): AndroidViewModel(app) {

   private val rep= MainRepository(app)
    var resultData = rep.resultData

}