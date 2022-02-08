package edu.stanford.rkpandey.rvpractice

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_contacts.*

class ContactsActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var imageView: ImageView

    companion object {
        val IMAGE_REQUEST_CODE = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        button = findViewById(R.id.btnSubmit)
        imageView = findViewById(R.id.img_save)




        button.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val newPerson = Person(name, age)
            val output = Intent()
            pickImageGallery()
            output.putExtra("person", newPerson)
            setResult(Activity.RESULT_OK, output)
            finish()
        }

        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK) {
                imageView.setImageURI(data?.data)
            }
        }
    }

    private fun pickImageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }
}