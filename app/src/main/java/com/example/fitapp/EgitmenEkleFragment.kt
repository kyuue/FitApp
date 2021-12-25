package com.example.fitapp

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.fitapp.databinding.EgitmenEkleBinding
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.sql.Blob
import java.sql.SQLException


class EgitmenEkleFragment : Fragment(R.layout.egitmen_ekle) {

    private var binding: EgitmenEkleBinding? = null

    private var isTherePermission = false

    private var requestPermissionLauncher: ActivityResultLauncher<String>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                isTherePermission = isGranted
                if (isGranted) {

                } else {
                    // Explain to the user that the feature is unavailable because the
                    // features requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    val newFragment =
                        InfoDialogFragment("No permission for selecting photos.")
                    newFragment.show(parentFragmentManager, "info")
                }
            }

        binding = EgitmenEkleBinding.inflate(inflater, container, false)

        return binding!!.root

    }

    private var imageUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            imageUri = data?.data

            val newFragment =
                InfoDialogFragment("Resim başarıyla seçilmiştir.")
            newFragment.show(parentFragmentManager, "info")
        }
    }

    private fun startGallery() {

        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_MEDIA_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {

                val cameraIntent = Intent(Intent.ACTION_GET_CONTENT)
                cameraIntent.type = "image/*"
                if (cameraIntent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivityForResult(cameraIntent, 1000)
                }

            }
            shouldShowRequestPermissionRationale("ACCESS_MEDIA_LOCATION") -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected. In this UI,
                // include a "cancel" or "no thanks" button that allows the user to
                // continue using your app without granting the permission.
                val newFragment =
                    InfoDialogFragment("No permission for selecting photo.")
                newFragment.show(parentFragmentManager, "info")
            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher?.launch(
                    Manifest.permission.ACCESS_MEDIA_LOCATION
                )
            }
        }
    }

    fun getEgitmenContentValues(): ContentValues {

        val ims: InputStream? = context?.contentResolver?.openInputStream(imageUri!!)
        val bitmap = BitmapFactory.decodeStream(ims)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream)
        val bytesImage: ByteArray = byteArrayOutputStream.toByteArray()

        val contentValues = ContentValues()
        contentValues.put("name", binding?.isimTextBox?.text?.toString())
        contentValues.put("surname", binding?.soyIsimTextBox?.text?.toString())
        contentValues.put("tel", binding?.telefonNoTextBox?.text?.toString())
        contentValues.put("about", binding?.hakkindaTextBox?.text?.toString())
        contentValues.put("price", binding?.ucretTextBox?.text?.toString()?.toInt())
        contentValues.put("photo", bytesImage)

        return contentValues
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fotografEkleButton?.setOnClickListener {

            startGallery()

        }

        binding?.egitmenEkleButton?.setOnClickListener {

            if (binding?.isimTextBox?.text?.length!! > 20) {
                val newFragment =
                    InfoDialogFragment("İsim 20 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.isimTextBox?.text?.matches(Regex("^[a-zA-ZğüşöçıİĞÜŞÖÇ ]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("İsim sayı içeremez.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (binding?.soyIsimTextBox?.text?.length!! > 20) {
                val newFragment =
                    InfoDialogFragment("Soyisim 20 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.soyIsimTextBox?.text?.matches(Regex("^[a-zA-ZğüşöçıİĞÜŞÖÇ ]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("Soyisim sayı içeremez.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (binding?.telefonNoTextBox?.text?.length!! != 11) {
                val newFragment =
                    InfoDialogFragment("Telefon numarası 11 hane uzunluğunda olmalıdır.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (binding?.hakkindaTextBox?.text?.length!! > 250) {
                val newFragment =
                    InfoDialogFragment("Hakkında kısmı 250 karakterden uzun olamaz.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (binding?.ucretTextBox?.text?.length!! > 5) {
                val newFragment =
                    InfoDialogFragment("Ücret kısmına en fazla 5 karakter girilebilir.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (!binding?.ucretTextBox?.text?.matches(Regex("^[0-9]+$"))!!) {
                val newFragment =
                    InfoDialogFragment("Ücret kısmı sadece sayı içerebilir.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            if (imageUri == null) {
                val newFragment =
                    InfoDialogFragment("Fotoğraf seçilmesi gerekir.")
                newFragment.show(parentFragmentManager, "info")
                return@setOnClickListener;
            }

            try {
                val con = DatabaseHelper.createConnection()

                val preparedStatement = con?.prepareStatement("INSERT INTO egitmenler(name, surname, tel, about, price, photo) VALUES(?, ?, ?, ?, ?, ?)")

                val contents = getEgitmenContentValues()

                preparedStatement?.setString(1, contents["name"] as String?)
                preparedStatement?.setString(2, contents["surname"] as String?)
                preparedStatement?.setString(3, contents["tel"] as String?)
                preparedStatement?.setString(4, contents["about"] as String?)
                preparedStatement?.setInt(5, contents["price"] as Int)
                preparedStatement?.setBytes(6,  contents["photo"] as ByteArray)

                preparedStatement?.execute()

                preparedStatement?.close()

                con?.close()

                val newFragment =
                    InfoDialogFragment("Eğitmen başarıyla eklendi.")
                newFragment.show(parentFragmentManager, "info")

                /*
                if (isSuccessful == true) {
                    val newFragment =
                        InfoDialogFragment("Eğitmen başarıyla eklendi.")
                    newFragment.show(parentFragmentManager, "info")
                }
                else
                {
                    val newFragment =
                        InfoDialogFragment("Eğitmen eklenemedi.")
                    newFragment.show(parentFragmentManager, "info")
                }
                 */

            } catch (e: SQLException) {
                val newFragment = InfoDialogFragment(e.toString())
                newFragment.show(parentFragmentManager, "info")
            }

            /*
            val db = MainActivity.DatabaseObject.writableDatabase

            val rowId = db.insert("egitmenler", null, getEgitmenContentValues())


             */
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
