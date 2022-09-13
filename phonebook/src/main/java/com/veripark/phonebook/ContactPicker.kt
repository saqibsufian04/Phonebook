package com.veripark.phonebook

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class ContactPicker : Fragment() {

    private lateinit var onContactPicked: (Contact) -> Unit
    private lateinit var onFailure: (Throwable) -> Unit

    companion object {

        private const val TAG = "ContactPicker"

        fun create(
            activity: FragmentActivity,
            onContactPicked: (Contact) -> Unit,
            onFailure: (Throwable) -> Unit
        ): ContactPicker? {

            return try {
                val picker = ContactPicker()
                picker.onContactPicked = onContactPicked
                picker.onFailure = onFailure
                activity.supportFragmentManager.beginTransaction()
                    .add(picker, TAG)
                    .commitNowAllowingStateLoss()

                picker
            } catch (e: Exception) {
                onFailure(e)
                null
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return null
    }

    fun pick() {
        try {
            Intent().apply {
                data = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                action = Intent.ACTION_PICK
                resultLauncher.launch(this)
            }
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            var cursor: Cursor? = null
            try {
                cursor = data?.data.let { uri ->
                    uri as Uri
                    activity?.contentResolver?.query(uri, null, null, null, null)
                }
                cursor?.let {
                    it.moveToFirst()
                    val phoneNumber =
                        it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    val name =
                        it.getString(it.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                    it.close()
                    onContactPicked(Contact(phoneNumber, name))
                }

            } catch (e: Exception) {
                onFailure(e)
                cursor?.close()
            }
        }
    }
}