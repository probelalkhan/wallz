package net.simplifiedcoding.data.responses

data class SearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<PhotoResponseItem>
)