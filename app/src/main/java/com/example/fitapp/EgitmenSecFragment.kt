package com.example.fitapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fitapp.databinding.EgitmenEkleBinding
import com.example.fitapp.databinding.EgitmenListElementBinding
import com.example.fitapp.databinding.EgitmenSecBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception
import androidx.core.app.ActivityCompat.startActivityForResult

import android.provider.ContactsContract





class EgitmenSecFragment : Fragment(R.layout.egitmen_sec) {

    private var binding: EgitmenSecBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = EgitmenSecBinding.inflate(inflater, container, false)

        return binding!!.root

    }


    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //EgitmenListElementBinding.inflate (layoutInflater, binding!!.EgitmenContainer, true)

        try {

            /*
            val db = MainActivity.DatabaseObject.readableDatabase

            val cursor = db.query(
                "egitmenler",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
            )
            */

            val con = DatabaseHelper.createConnection()

            val stmt = con?.prepareStatement("SELECT * FROM egitmenler")

            val cursor = stmt?.executeQuery()


            while(cursor?.next() == true) {
                val listElementBinding = EgitmenListElementBinding.inflate(layoutInflater, binding!!.EgitmenContainer, true)

                val name = cursor.getString(cursor.findColumn("name")) + " " + cursor.getString(cursor.findColumn("surname"))

                listElementBinding.nameOfEgitmen.text = name

                listElementBinding.descriptionOfEgitmen.text = cursor.getString(cursor.findColumn("about"))

                listElementBinding.ucretOfEgitmen.text = cursor.getInt(cursor.findColumn("price")).toString() + " TL"

                val bytesImage = cursor.getBlob(cursor.findColumn("photo")).binaryStream.readBytes()
                val bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.size)
                listElementBinding.circularImageView.setImageBitmap(bitmapImage)

                val telephone = cursor.getString(cursor.findColumn("tel"))

                listElementBinding.egitmeniAraButton.setOnClickListener{
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:$telephone")
                    startActivity(dialIntent)
                }
            }

            cursor?.close()

            con?.close()

        } catch (e: Exception) {
            val newFragment = InfoDialogFragment(e.toString())
            newFragment.show(parentFragmentManager, "info")
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
