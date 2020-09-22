package com.alaizhashim.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previous_button: ImageButton
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previous_button = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)

        }


        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)

        }
        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        previous_button.setOnClickListener() {
            currentIndex = (currentIndex - 1) % questionBank.size
            if (currentIndex==-1) {
                currentIndex = questionBank.size-1
            }
            updateQuestion()
        }

        updateQuestion()
    }
        private fun updateQuestion() {
            val questionTextResId = questionBank[currentIndex].textResId
                questionTextView.setText(questionTextResId)
}
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        var toast=Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP,0,190)
        toast.show()


    }

}
