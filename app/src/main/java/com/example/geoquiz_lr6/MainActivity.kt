package com.example.geoquiz_lr6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


}