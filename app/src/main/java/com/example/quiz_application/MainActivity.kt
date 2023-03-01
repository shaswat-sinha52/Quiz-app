package com.example.quiz_application

import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.example.quiz_application.strorage.USER_NAME


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonStart: Button = findViewById(R.id.start1)
        val etName: AppCompatEditText = findViewById(R.id.et_name)

        buttonStart.setOnClickListener {
            if (etName.text.toString().isEmpty()){
                Toast.makeText(this,"Please Enter Your Name",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,question_page::class.java)
                intent.putExtra("USER_NAME",etName.text.toString())
                startActivity(intent)
            }
        }
    }
}