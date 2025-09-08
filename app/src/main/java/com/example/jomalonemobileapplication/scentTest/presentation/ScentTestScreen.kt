package com.example.jomalonemobileapplication.scentTest.presentation
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import com.example.jomalonemobileapplication.feature.scentTest.domain.model.ScentType


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jomalonemobileapplication.R
import com.example.jomalonemobileapplication.core.theme.Background
import com.example.jomalonemobileapplication.core.theme.Cormorant
import com.example.jomalonemobileapplication.core.theme.DarkBrown
import com.example.jomalonemobileapplication.core.theme.LightBrown
import com.example.jomalonemobileapplication.scentTest.data.repository.ScentTestRepository
import com.example.jomalonemobileapplication.scentTest.presentation.ScentTestViewModel


@OptIn(ExperimentalMaterial3Api::class)    // for TopAppBar
@Composable
fun ScentTestScreen(
    modifier: Modifier = Modifier,
    viewModel: ScentTestViewModel = viewModel(),
    onTestComplete : (ScentType) -> Unit = {} ,   // add this for navigation

){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val questions = remember { ScentTestRepository.getQuestions()}
    val currentQuestion = questions[uiState.currentQuestionIndex]
    val isFirstQuestion = uiState.currentQuestionIndex == 0
    val isLastQuestion = uiState.currentQuestionIndex == questions.size -1

    LaunchedEffect(uiState.isTestCompleted){
        if (uiState.isTestCompleted){
            uiState.result?.let { result ->
                onTestComplete(result)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ){
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        // if got title then show
        currentQuestion.titleResId?.let { titleResId ->
            TopAppBar(
                title = {
                    Text(stringResource(titleResId),
                        fontFamily = Cormorant,
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center)
                },
                modifier = modifier.padding(bottom = 20.dp),
                colors = TopAppBarDefaults.topAppBarColors( containerColor = Background)
            )
        }
        // progress bar
        LinearProgressIndicator(
            progress = {(uiState.currentQuestionIndex + 1).toFloat() / questions.size},
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        )
        Text(
            text = "Question ${uiState.currentQuestionIndex + 1} of ${questions.size}",
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic
        )

        currentQuestion.titleResId?.let {titleResId ->
            Text(
                text = stringResource(titleResId),
                fontFamily = Cormorant,
                modifier = modifier.align(Alignment.CenterHorizontally),
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(
                modifier = modifier.padding(20.dp)
            )
        }
//        Text(
//            text = stringResource(R.string.mood_title),
//            fontFamily = Cormorant,
//            modifier = modifier.align(Alignment.CenterHorizontally),
//            textDecoration = TextDecoration.Underline,
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(
//            modifier = modifier.padding(20.dp)
//        )
//        Text(
//            text = stringResource(R.string.question1),
//            fontFamily = Cormorant,
//            modifier = modifier,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold
//        )
        Text(
            text = stringResource(currentQuestion.questionResId),
            fontFamily = Cormorant,
            modifier = modifier,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.padding(16.dp))

        // options
        currentQuestion.options.forEach { option ->
            Button(
                onClick = {
                    viewModel.selectOption(currentQuestion.id, option.scentType)
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(50.dp)
                    .border(width = 2.dp, color = DarkBrown, shape = MaterialTheme.shapes.medium),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(containerColor = LightBrown, contentColor = Color.Black)
            ){
                Text(stringResource(option.textResId?:0), fontSize = 16.sp)    // dunno how if option is image
            }
        }
        Spacer(modifier = modifier.padding(40.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            if (!isFirstQuestion){
                Button(
                    onClick = { viewModel.moveToPreviousQuestion()},
                    colors = ButtonDefaults.buttonColors(containerColor = LightBrown, contentColor = Color.Black),
                ){
                    Text(stringResource(R.string.previous_button))
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Button(
                onClick = {
                    if (isLastQuestion) {
                        viewModel.completeTest()
                    } else {
                        viewModel.moveToNextQuestion()
                    }
                },
                enabled = uiState.userSelections.containsKey(currentQuestion.id)
            ) {
                Text(if (isLastQuestion) "Confirm" else stringResource(R.string.next_button))
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScentTestScreenPreview(){
    val viewModel = remember {ScentTestViewModel()}
    ScentTestScreen(viewModel = viewModel)

}

//@Composable
//fun ScentTestScreen(
//    viewModel: ScentTestViewModel,
//    modifier: Modifier = Modifier
//) {
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val questions = remember { ScentTestRepository.getQuestions() }
//
//    val currentQuestion = questions[uiState.currentQuestionIndex]
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Progress and header...
//
//        QuestionRenderer(
//            question = currentQuestion,
//            onOptionSelected = { selectedIndex ->
//                val selectedScentType = currentQuestion.options[selectedIndex].scentType
//                viewModel.selectOption(currentQuestion.id, selectedScentType)
//            },
//            modifier = Modifier.weight(1f)
//        )
//    }
//}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ScentTestScreenPreview(){
//    JoMaloneMobileApplicationTheme {
//        val viewModel = rememberSaveable { ScentTestViewModel() }
//        ScentTestScreen(viewModel = viewModel)
//    }
//}