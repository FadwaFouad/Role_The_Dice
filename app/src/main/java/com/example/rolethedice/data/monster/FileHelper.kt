package com.example.rolethedice.data.monster

import android.app.Application
import android.content.Context
import java.io.File
import java.nio.charset.Charset

class FileHelper {
    companion object {
        fun getTextFromRes (context: Context, resId : Int): String{
            return context.resources.openRawResource(resId).use {
                it.bufferedReader().use {
                    it.readText()
                }
            }
        }

        fun saveTextToFile (app : Application, text: String){
            val file = File(app.filesDir, "fadwa.json")
            file.writeText(text, Charset.defaultCharset())
        }
    }
}