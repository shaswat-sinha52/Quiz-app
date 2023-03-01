package com.example.quiz_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class result_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_page)
        val tvname:TextView=findViewById(R.id.tv_name)
        val tvscore:TextView=findViewById(R.id.score)
        val finish:Button=findViewById(R.id.finalbtn)

        tvname.text=intent.getStringExtra("USER_NAME")
        val total_question=intent.getIntExtra("total_question",0)
        val correct_answer=intent.getIntExtra("correct_answer",0)

        tvscore.text="final score $correct_answer"

        finish.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}