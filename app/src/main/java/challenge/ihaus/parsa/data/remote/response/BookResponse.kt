package challenge.ihaus.parsa.data.remote.response

import challenge.ihaus.parsa.data.remote.dto.BookDto

data class BookResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookDto>,
) {
    data class Pagination(private val page: Int)
}