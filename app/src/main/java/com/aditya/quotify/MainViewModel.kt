package com.aditya.quotify

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlin.random.Random
import kotlin.random.nextUInt

class MainViewModel(val context: Context) : ViewModel() {
    //Array For Quote
    private var quoteList : Array<Quote> = emptyArray()
    private var index : Int = 0
    var random = Random

    init {
        quoteList = loadQuoteFromAssets()

    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")//Open file
        val size : Int = inputStream.available()//Take size
        val buffer = ByteArray(size)//Defined a bite array of size(size)
        inputStream.read(buffer)//read inputStream and store in byteArray(Buffer)
        inputStream.close()
        //JSON IS ENCODED IN UTF_8 FORMAT
        val json = String(buffer,Charsets.UTF_8)//Convert Byte Array to String
        val gson = Gson()
        return gson.fromJson(json,Array<Quote>::class.java)//Typecast string to json

    }

    fun getQuote() = quoteList[index]

    fun nextQuote(): Quote {
        if(index == quoteList.size -1) {
            index = 0
            return quoteList[0]
        } else {
        return  quoteList[++index]
        }
    }

    fun previousQuote(): Quote {
        if(index == 0) {
            index = quoteList.size -1
            return quoteList[quoteList.size -1]
        } else {
            return quoteList[--index]
        }
    }

    fun random() = quoteList[random.nextInt(1642-1)+1]
}