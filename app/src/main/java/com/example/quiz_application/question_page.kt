package com.example.quiz_application

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlin.math.log
import kotlin.math.max

class question_page : AppCompatActivity(), View.OnClickListener{
    //Create global variables for the views in the layout
    private var mcurrentposition:Int=1
    private var mquestionlist : ArrayList<question_structure>?=null
    private var mselectedquestionposition:Int=0
    private var musername :String?=null
    private var mcorrectanswer:Int=0

    private var progressBar: ProgressBar?=null
    private var tvProgress: TextView? = null
    private var tvQuestion:TextView? = null
    private var ivImage: ImageView? = null
    private var tvOptionOne:TextView? = null
    private var tvOptionTwo:TextView? = null
    private var tvOptionThree:TextView? = null
    private var tvOptionFour:TextView? = null
    private var btnsubmit: Button?=null
    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_question_page)
        progressBar=findViewById(R.id.pb_id)
        tvProgress = findViewById(R.id.pbside_tv_id)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_1)
        tvOptionOne = findViewById(R.id.op1)
        tvOptionTwo = findViewById(R.id.op2)
        tvOptionThree = findViewById(R.id.op3)
        tvOptionFour = findViewById(R.id.op4)
        mquestionlist = strorage.getQuestions()
        btnsubmit=findViewById(R.id.cabtn)
        musername=intent.getStringExtra("USER_NAME")

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnsubmit?.setOnClickListener(this)

        setquestion()

    }

    private fun setquestion() {
        defaultoptionview()
        val question: question_structure? =
            mquestionlist!![mcurrentposition - 1] // Getting the question from the list with the help of current position.
        progressBar?.progress =
            mcurrentposition // Setting the current progress in the progressbar using the position of question
        tvProgress?.text =
            "$mcurrentposition / ${mquestionlist?.size}" // Setting up the progress text

        // Now set the current question and the options in the UI
        tvQuestion?.text = question!!.question
        ivImage?.setImageResource(question.image)
        tvOptionOne?.text = question.option1
        tvOptionTwo?.text = question.option2
        tvOptionThree?.text = question.option3
        tvOptionFour?.text = question.option4

        if(mcurrentposition ==mquestionlist!!.size){
            btnsubmit?.text="Finish"
        }else{
            btnsubmit?.text="Submit"
        }
    }
    fun defaultoptionview(){
        var options=ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }
        for(i in options){
            i.setTextColor(Color.parseColor("#7A8089"))
            i.typeface=Typeface.DEFAULT
            i.background=ContextCompat.getDrawable(this,
                R.drawable.select_option_button)
        }
    }
    private fun selectedoptionview(tv: TextView , selectedoptionnum:Int){
        defaultoptionview()

        mselectedquestionposition=selectedoptionnum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)

        tv.background=ContextCompat.getDrawable(this,
            R.drawable.select_option_button)


    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.op1->{
                tvOptionOne?.let {
                    selectedoptionview(it,1)
                }
            }
            R.id.op2->{
                tvOptionTwo?.let {
                    selectedoptionview(it,2)
                }
            }
            R.id.op3->{
                tvOptionThree?.let {
                    selectedoptionview(it,3)
                }
            }
            R.id.op4->{
                tvOptionFour?.let {
                    selectedoptionview(it,4)
                }
            }
            R.id.cabtn->{
                if(mselectedquestionposition ==0){
                    mcurrentposition++
                    when{
                        mcurrentposition <= mquestionlist!!.size->{
                            setquestion()
                        }
                        else ->{
                            val intent=Intent(this,result_page::class.java)
                            intent.putExtra("USER_NAME",musername)
                            intent.putExtra("correct_answer",mcorrectanswer)
                            intent.putExtra("total_question",mquestionlist?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }else{
                    val question=mquestionlist?.get(mcurrentposition-1)
                    if(question!!.correct_ans != mselectedquestionposition) {
                        answerview(mselectedquestionposition, R.drawable.wrong_option_border)
                    }else{
                        mcorrectanswer++
                    }
                    answerview(question.correct_ans,R.drawable.correct_option_border)

                    if(mcurrentposition == mquestionlist!!.size){
                        btnsubmit?.text="Finish"

                    }
                    else{
                        btnsubmit?.text="Next Question"
                    }
                    mselectedquestionposition=0
                }
            }

        }

    }

    private fun answerview(answer:Int ,drawableview:Int){
        when(answer){
            1->{
                tvOptionOne?.background=ContextCompat.getDrawable(this,drawableview)
            }
            2->{
                tvOptionTwo?.background=ContextCompat.getDrawable(this,drawableview)
            }
            3->{
                tvOptionThree?.background=ContextCompat.getDrawable(this,drawableview)
            }
            4->{
                tvOptionFour?.background=ContextCompat.getDrawable(this,drawableview)
            }
        }
    }

}