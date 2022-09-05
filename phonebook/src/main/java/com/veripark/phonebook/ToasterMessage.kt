package com.veripark.phonebook

import android.content.Context
import android.widget.Toast

class ToasterMessage {

    fun show(c: Context?, message: String?) {
        Toast.makeText(c, message, Toast.LENGTH_LONG).show()
    }
}