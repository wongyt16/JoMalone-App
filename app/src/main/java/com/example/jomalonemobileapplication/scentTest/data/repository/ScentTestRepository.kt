package com.example.jomalonemobileapplication.scentTest.data.repository

import com.example.jomalonemobileapplication.R
import com.example.jomalonemobileapplication.feature.scentTest.domain.model.ScentOption
import com.example.jomalonemobileapplication.feature.scentTest.domain.model.ScentQuestion
import com.example.jomalonemobileapplication.feature.scentTest.domain.model.ScentType
import com.example.jomalonemobileapplication.scentTest.domain.model.QuestionType

object ScentTestRepository {
    fun getQuestions(): List<ScentQuestion> {
        return listOf(
            ScentQuestion(
                id = 1,
                titleResId = R.string.mood_title,
                questionResId = R.string.question1,
                questionType = QuestionType.BUTTON_BASED,
                options = listOf(
                    ScentOption(R.string.q1_opt1, ScentType.CITRUS),
                    ScentOption(R.string.q1_opt2, ScentType.FLORAL),
                    ScentOption(R.string.q1_opt3, ScentType.WOODY),
                    ScentOption(R.string.q1_opt4, ScentType.SPICY),
            )
        ),
            ScentQuestion(
                id = 2,
                titleResId = R.string.preference_title,
                questionResId = R.string.question6,
                questionType = QuestionType.IMAGE_BUTTON,
                options = listOf(
                    // dunno if this is a bad practice （set textResId to null）
                    ScentOption(null, ScentType.CITRUS, R.drawable.pink),
                    ScentOption(null, ScentType.FLORAL, R.drawable.blue),
                    ScentOption(null, ScentType.WOODY, R.drawable.gold),
                    ScentOption(null, ScentType.SPICY, R.drawable.green),
                )
            )
        )
    }
}