package com.example.jomalonemobileapplication.feature.scentTest
import com.example.jomalonemobileapplication.feature.scentTest.domain.model.ScentType


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jomalonemobileapplication.R
import com.example.jomalonemobileapplication.core.theme.Cormorant
import com.example.jomalonemobileapplication.core.theme.JoMaloneMobileApplicationTheme


@OptIn(ExperimentalMaterial3Api::class)    // for TopAppBar
@Composable
fun ScentTestScreen(modifier : Modifier = Modifier){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Text(
//            text = stringResource(R.string.scent_quiz_title),
//            modifier = modifier,
//            fontWeight = FontWeight.Bold,
//            fontSize = 24.sp,
////            fontFamily = FontFamily.Serif
//        )
        TopAppBar(
            title = {
                Text(stringResource(R.string.scent_quiz_title),
                    fontFamily = Cormorant,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.headlineSmall)
            },
            modifier = modifier.padding(bottom = 20.dp)
        )
        // progress bar
        Text(
            text = stringResource(R.string.mood_title),
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(
            modifier = modifier.padding(20.dp)
        )
        Text(
            text = stringResource(R.string.question1),
            modifier = modifier,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ScentTestScreenPreview(){
        ScentTestScreen()

}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ScentTestScreenPreview(){
//    JoMaloneMobileApplicationTheme {
//        val viewModel = rememberSaveable { ScentTestViewModel() }
//        ScentTestScreen(viewModel = viewModel)
//    }
//}