package com.example.geoquiz_lr6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.geoquiz_lr6.ui.theme.GeoQuiz_LR6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoQuizApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GeoQuizApp() {

    // Вопросы
    val questions = listOf(
        "Canberra is the capital of Australia.",
        "The Pacific Ocean is larger than the Atlantic Ocean.",
        "The Suez Canal connects the Red Sea and the Indian Ocean.",
        "The source of the Nile River is in Egypt.",
        "The Amazon River is the longest river in the Americas.",
        "Lake Baikal is the world's oldest and deepest freshwater lake."
    )

    // Правильные ответы
    val answers = listOf(
        true,
        true,
        false,
        false,
        true,
        true
    )
    var currentQuestion by remember { // Номер текущего вопроса
        mutableIntStateOf(0)
    }

    var correctAnswers by remember { // Количество правильных ответов
        mutableIntStateOf(0)
    }

    var answerSelected by remember { // Были ли уже нажаты True или False
        mutableStateOf(false)
    }

    var showResult by remember {// Показывать ли результат
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Заголовок
        Text(text = "GeoQuiz")

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // Текущий вопрос
        Text(
            text = questions[currentQuestion]
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // Кнопки True и False
        Row {

            Button(
                onClick = {
                    if (answers[currentQuestion]) {
                        correctAnswers++
                    }

                    answerSelected = true
                },
                enabled = !answerSelected
            ) {
                Text("True")
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = {
                    if (!answers[currentQuestion]) {
                        correctAnswers++
                    }

                    answerSelected = true
                },
                enabled = !answerSelected
            ) {
                Text("False")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        if (currentQuestion < questions.lastIndex) { // Кнопка Next
            Button(
                onClick = {
                    currentQuestion++
                    answerSelected = false
                },
                enabled = answerSelected
            ) {
                Text("Next")
            }
        }

        // Если отвечен последний вопрос
        if (currentQuestion == questions.lastIndex && answerSelected) {
            showResult = true // показать результат
        }

        // Всплывающий результат
        if (showResult) {

            AlertDialog(
                onDismissRequest = {showResult = false},

                title = {Text("Результат")},

                text = {Text("Правильных ответов: " + "$correctAnswers из ${questions.size}")},

                confirmButton = {
                    Button(
                        onClick = { // Запускаем тест заново
                            currentQuestion = 0
                            correctAnswers = 0
                            answerSelected = false
                            showResult = false
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}