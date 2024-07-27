package viewmodel

import model.QuestionModel

data class QuestionListState(
    val isLoading: Boolean = false,
    val questions: List<QuestionModel> = emptyList(),
    val error: String = ""
)