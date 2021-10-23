package com.example.contactapp.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.example.contactapp.R
import com.example.contactapp.databinding.ActivityContactDetailBinding
import com.example.contactapp.models.Contact
import com.example.contactapp.viewModel.ContactsViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
@AndroidEntryPoint
class ContactDetailActivity : AppCompatActivity() {
    var contactId= 0

    val contactsViewModel : ContactsViewModel by viewModels()
    lateinit var  photoFile: File
    lateinit var myContact : Contact
    lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        contactId= intent.getIntExtra("CONTACT_ID",0)
        contactsViewModel.getContactById(contactId)
    }

    override fun onResume() {
        super.onResume()
        contactsViewModel.contactLiveData.observe(this, { contact->
            binding.tvContactName.text= contact.name
            binding.tvContactPhoneNumber.text= contact.phoneNumber
            binding.tvContactEmail.text= contact.email

            if (contact.imgUrl?.isNotEmpty()!!) {
                binding.ivCamera.setImageBitmap(BitmapFactory.decodeFile(contact.imgUrl))
            }
            myContact = contact
        })
        binding.ivCamera.setOnClickListener {
            val takePictureIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile= getPhotoFile(("photo_${System.currentTimeMillis()}"))
            val  fileProvider= FileProvider
                .getUriForFile(this, "com.example.contactapp.provider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            cameraLauncher.launch(takePictureIntent)

        }
        binding.ivGallery.setOnClickListener {
            clickPickPhoto()
        }
    }
    fun clickPickPhoto() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.
                READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                pickImageFromGallery()
            } else {
                val permissions = arrayOf(Manifest.permission.
                READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, 111)
            }
        } else {
            pickImageFromGallery()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions,
            grantResults)
        when (requestCode) {
            111 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(baseContext, "Permission denied", Toast.
                    LENGTH_LONG)
                }
            }
        }
    }
    private fun pickImageFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        galleryLauncher.launch(galleryIntent)
    }
    var galleryLauncher =
        registerForActivityResult(ActivityResultContracts.
        StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                var imageUri = result.data?.data
                val bitmap = MediaStore.Images.Media.getBitmap(this.
                contentResolver, imageUri)
                binding.imgContact.setImageBitmap(bitmap)
            }
        }

    fun getPhotoFile(fileName: String): File{
        var storageDir= getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName,"jpg",storageDir)
    }

    var cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
        if(result.resultCode == Activity.RESULT_OK){
            var takenPhoto = BitmapFactory.decodeFile(photoFile.absolutePath)
            binding.ivCamera.setImageBitmap(takenPhoto)
        }
    }
}