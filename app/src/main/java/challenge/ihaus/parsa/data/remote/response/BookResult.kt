package challenge.ihaus.parsa.data.remote.response

import challenge.ihaus.parsa.domain.model.Book

data class BookResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Book>
)