package challenge.ihaus.parsa.domain.repository

import androidx.paging.PagingData
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.usecase.book.GetBooksUseCase
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBooks(filterBy: GetBooksUseCase.FilterBy): Flow<PagingData<Book>>
    suspend fun makeBookFavorite(book: Book)
    suspend fun makeBookUnFavorite(book: Book)
}