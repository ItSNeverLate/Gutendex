package challenge.ihaus.parsa.domain.usecase.book

import androidx.paging.PagingData
import challenge.ihaus.parsa.domain.model.Book
import kotlinx.coroutines.flow.Flow


interface GetBooksUseCase {
    enum class FilterBy { NONE, FREE_COPY_RIGHT, CENTURY_19TH }

    operator fun invoke(filterBy: FilterBy): Flow<PagingData<Book>>
}