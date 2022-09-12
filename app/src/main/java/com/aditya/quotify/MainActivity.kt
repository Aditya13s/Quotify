package com.aditya.quotify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private  val quoteText : TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuthor : TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this ,
            MainViewModelFactory(application))[MainViewModel::class.java]

        setQuote(mainViewModel.getQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT , mainViewModel.getQuote().text)
        startActivity(intent)
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.nextQuote())
    }
    fun onPrevious(view: View) {
        setQuote(mainViewModel.previousQuote())
    }

   fun setQuote(quote: Quote) {
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

    fun random(view: View) {
        setQuote((mainViewModel.random()))
    }
}