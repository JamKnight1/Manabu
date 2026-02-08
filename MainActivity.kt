package com.example.japanesetestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.japanesetestapp.ui.theme.JapaneseTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JapaneseTestAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding))
                        AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JapaneseTestAppTheme {
        Greeting("Android")
    }
}
@Composable
fun Question(word: String) {
    Card {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = word,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 400.dp),
                fontSize = 30.sp
            )
        }
    }
}
@Composable
fun DisplayWrongImage(modifier: Modifier = Modifier) {
    val imagePainter = painterResource(id = R.drawable.incorrect)

    Image(
        painter = imagePainter,
        contentDescription = null,
        modifier = Modifier.padding(bottom = 400.dp)

    )
}
@Composable
fun Answer1(onNavigateToDetail: () -> Unit) {
    Button(
        modifier = Modifier.padding(top = 400.dp, start = 100.dp).size(width = 250.dp, height = 50.dp),
        onClick = onNavigateToDetail

    ) {
        Text("Japan")
    }
}
@Composable
fun Answer2(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(top = 500.dp, start = 100.dp).size(width = 250.dp, height = 50.dp),
        onClick = {
        onClick()
    }) {
        Text("Japanese (language)")
    }
}
@Composable
fun Answer3(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(top = 600.dp, start = 100.dp).size(width = 250.dp, height = 50.dp),
        onClick = {
        onClick()
    }) {
        Text("Japanese man")
    }
}
@Composable
fun Answer4(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(top = 700.dp, start = 100.dp).size(width = 250.dp, height = 50.dp),
        onClick = {
        onClick()
    }) {
        Text("All of The Above")
    }
}
@Composable
fun MainScreen(navController: NavController) {
    Question(word = "nihongo")

    Answer1 {
        navController.navigate(Routes.ANSWER_SCREEN)
    }
    Answer2 { }
    Answer3 { }
    Answer4 { }
}
@Composable
fun WrongAnswer(onNavigateBack: () -> Unit){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.size(width = 250.dp, height = 50.dp),
            onClick = onNavigateBack
        ) {
            Text("Go Back")
        }
        DisplayWrongImage()
    }
}

object Routes{
    const val MAIN_SCREEN = "main screen"
    const val ANSWER_SCREEN = "answer screen"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MAIN_SCREEN){

        composable(route = Routes.MAIN_SCREEN) {
           MainScreen(navController)
        }
        composable(route = Routes.ANSWER_SCREEN) {
            WrongAnswer (
                onNavigateBack = {
                    // Navigate back to the previous screen (main screen)
                    navController.popBackStack()
                }
            )
        }
    }
}