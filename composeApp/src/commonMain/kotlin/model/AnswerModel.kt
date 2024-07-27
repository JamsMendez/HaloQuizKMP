package model

data class AnswerModel(
    val content: String = "",
    val correct: Boolean = false,

    var selected: Boolean = false
)