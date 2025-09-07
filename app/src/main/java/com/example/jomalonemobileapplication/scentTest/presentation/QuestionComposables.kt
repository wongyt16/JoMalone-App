package com.example.jomalonemobileapplication.scentTest.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.jomalonemobileapplication.core.theme.Cormorant
import com.example.jomalonemobileapplication.core.theme.DarkBrown
import com.example.jomalonemobileapplication.feature.scentTest.domain.model.ScentQuestion
import com.example.jomalonemobileapplication.scentTest.domain.model.QuestionType


@Composable
fun QuestionComposables(
    question : ScentQuestion,
    onOptionSelected: (Int) -> Unit,
    modifier : Modifier = Modifier
    ) {
    Column(modifier = modifier){
        question.titleResId?.let { titleResId ->
            Text(
                text = stringResource(titleResId),
                fontFamily = Cormorant,
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // question text
        Text(
            text = stringResource(question.questionResId),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(24.dp))

        when (question.questionType){
           QuestionType.BUTTON_BASED -> ButtonBasedQuestion(
               question = question,
               onOptionSelected = onOptionSelected
           )
            QuestionType.IMAGE_BUTTON -> ImageButtonQuestion(
                question = question,
                onOptionSelected = onOptionSelected
            )
        }
    }
}


@Composable
fun ButtonBasedQuestion(
    question : ScentQuestion,
    onOptionSelected: (Int) -> Unit,
){
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)){
        question.options.forEachIndexed { index, option ->
            OutlinedButton(
                onClick = { onOptionSelected(index)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                border = BorderStroke(2.dp, DarkBrown),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.Black),
                shape = MaterialTheme.shapes.medium,
            ){
                Text(
                    text = stringResource(option.textResId?: 0)
                )
            }
        }
    }
}

@Composable
fun ImageQuestion(
    question : ScentQuestion,
    onOptionSelected: (Int) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(question.options.size) { index ->
            val option = question.options[index]

            Card(
                onClick = { onOptionSelected(index) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ){
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    option.imageResId?.let {imageResId ->
                        Image(
                            painter = painterResource(imageResId),
                            contentDescription = stringResource(option.textResId?: 0),
                            modifier = Modifier
                                .size(64.dp)
                                .clip(MaterialTheme.shapes.medium)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ImageButtonQuestion(
    question: ScentQuestion,
    onOptionSelected: (Int) -> Unit) {

}