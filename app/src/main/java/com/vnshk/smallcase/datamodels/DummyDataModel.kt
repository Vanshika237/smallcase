package com.vnshk.smallcase.datamodels

data class DummyDataModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean,
)

fun DummyDataModel.toMap(): HashMap<String, Any> {
    return hashMapOf(
        "userId" to userId,
        "id" to id,
        "title" to title,
        "completed" to completed
    )
}