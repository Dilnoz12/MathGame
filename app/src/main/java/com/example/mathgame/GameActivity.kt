package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import kotlinx.android.synthetic.main.activity_game.*
import java.lang.Math.random
import java.util.*
import kotlin.Int.Companion as Int1
import kotlin.jvm.java as java1

class GameActivity : AppCompatActivity() {
    companion object {
        const val LEVEL_COUNT = 10
        const val RIGHT_ANSWERS_COUNT = "rightAnswersCount"
    }
    private val random = Random()
    private var res: Int = 0
    private var currentLevelCount=1
    private var rightAnswers=0
    private var wrongAnswers=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        generateQuestion()

    }

    fun onClick(view: View) {
        val clickedVariant: Int = (view as Button).text.toString().toInt()
        if(clickedVariant==res){
            rightAnswers++
        }
        else{ wrongAnswers++}
        if(currentLevelCount== LEVEL_COUNT){
            val intent = Intent(this, GameOverActivity::class.java1)
            intent.putExtra(RIGHT_ANSWERS_COUNT,rightAnswers)
            startActivity(intent)
            finish()

        }else{
            generateQuestion()

        }

    }


    fun generateQuestion() {
        var firstNumber = random.nextInt(100)
        val secondNumber = random.nextInt(100)
        val temp = random.nextInt(4)
        when (temp) {
            0 -> {
                tvQuestion.text = (firstNumber + secondNumber).toString()
                res = firstNumber + secondNumber
            }

            1 -> {
                tvQuestion.text = (firstNumber - secondNumber).toString()
                res = firstNumber - secondNumber
            }

            2 -> {
                tvQuestion.text = (firstNumber * secondNumber).toString()
                res = firstNumber * secondNumber
            }

            3 -> {
                res = random.nextInt(100)
                firstNumber = secondNumber * random.nextInt(100)
                tvQuestion.text = (firstNumber * secondNumber).toString()

            }

        }
        val correctAnswerPosition: Int = random.nextInt(4)
        generateWrongAnswers(btn1)
        generateWrongAnswers(btn2)
        generateWrongAnswers(btn3)
        generateWrongAnswers(btn4)
        when (correctAnswerPosition) {
            0 -> btn1.text = res.toString()
            1 -> btn2.text = res.toString()
            2 -> btn3.text = res.toString()
            3 -> btn4.text = res.toString()
        }

    }


    private fun generateWrongAnswers(button: Button) {
        when (random.nextBoolean()) {
            true -> {
                button.text = (res + random.nextInt(10) + 1).toString()
            }
            false ->
                button.text = (res - random.nextInt(10) + 1).toString()


        }
    }
}







