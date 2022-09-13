package com.veripark.phonebook

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentActivity
import com.veripark.phonebook.ui.theme.PhonebookTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhonebookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(this, "Showing message from Phonebook library")
                }
            }
        }
    }
}

@Composable
fun Greeting(context: Context, name: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Click Me!",
            modifier = Modifier
                .clickable {
                   getContact(context as FragmentActivity)
                },
        )
    }
}

private fun getContact(activity: FragmentActivity) {
    val contactPicker: ContactPicker? = ContactPicker.create(
        activity = activity,
        onContactPicked = { Toast.makeText(activity,"${it.name}: ${it.number}",Toast.LENGTH_LONG).show() },
        onFailure = {Toast.makeText(activity,it.localizedMessage,Toast.LENGTH_LONG).show() })

    contactPicker?.pick()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhonebookTheme {

    }
}