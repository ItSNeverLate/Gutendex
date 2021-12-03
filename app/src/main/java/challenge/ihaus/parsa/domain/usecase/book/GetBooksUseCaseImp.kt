package challenge.ihaus.parsa.domain.usecase.book

import androidx.paging.PagingData
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetBooksUseCaseImp (private val bookRepository: BookRepository):GetBooksUseCase{
    override fun invoke(filterBy: GetBooksUseCase.FilterBy): Flow<PagingData<Book>> =bookRepository.getBooks(filterBy)
}