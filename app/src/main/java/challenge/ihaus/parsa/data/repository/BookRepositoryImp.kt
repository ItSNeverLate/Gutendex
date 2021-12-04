package challenge.ihaus.parsa.data.repository

import androidx.paging.*
import challenge.ihaus.parsa.data.local.AppDatabase
import challenge.ihaus.parsa.data.local.mapper.BookEntityMapper
import challenge.ihaus.parsa.data.mediator.BookRemoteMediator
import challenge.ihaus.parsa.data.remote.AppApi
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.repository.BookRepository
import challenge.ihaus.parsa.domain.usecase.book.GetBooksUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class BookRepositoryImp constructor(
    private val api: AppApi,
    private val db: AppDatabase,
) : BookRepository {

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override fun getBooks(filterBy: GetBooksUseCase.FilterBy): Flow<PagingData<Book>> {
        val pagingConfig = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
        )
        val pagingSourceFactory = { db.bookDao().getAllByFilter(filterBy) }
        return Pager(
            config = pagingConfig,
            remoteMediator = BookRemoteMediator(api, db),
            pagingSourceFactory = pagingSourceFactory,
        ).flow.map { pagingData ->
            pagingData.map { entity -> BookEntityMapper.toModel(entity) }
        }
    } //can also return livedata
}
