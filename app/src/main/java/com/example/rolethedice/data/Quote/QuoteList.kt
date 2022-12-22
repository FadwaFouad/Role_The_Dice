package com.example.rolethedice.data.Quote


data class QuoteList(
    val id: Int,
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<Result>,
    val totalCount: Int,
    val totalPages: Int
)