package com.br.moviefy.util


import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView

class Spannable {


    fun customText(fullText: String, textView: TextView) {
        val contrastText = "Gênero: "
        val _fullText = "${contrastText}${fullText}"
        val start = _fullText.indexOf(contrastText)
        val end = start + contrastText.length
        val spannableText = SpannableString(_fullText)
        val foregroundColorSpanWhite = ForegroundColorSpan(Color.WHITE)
        spannableText.setSpan(foregroundColorSpanWhite, start, end, 0)
        textView.text = spannableText
    }


}


