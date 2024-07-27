package model

data class QuestionModel(
    val id: String = "",
    val content: String = "",
    var options: List<AnswerModel> = listOf(),

    var answered: Boolean = false
)