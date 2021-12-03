package challenge.ihaus.parsa.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import challenge.ihaus.parsa.data.local.AppDatabase
import challenge.ihaus.parsa.data.local.entity.BookEntity
import challenge.ihaus.parsa.data.local.entity.RemoteKeyEntity
import challenge.ihaus.parsa.data.local.mapper.BookEntityMapper
import challenge.ihaus.parsa.data.remote.AppApi
import retrofit2.HttpException
import java.io.IOException

const val DEFAULT_PAGE_INDEX = 0

@ExperimentalPagingApi
class BookRemoteMediator(
    private val api: AppApi,
    private val db: AppDatabase,
) : RemoteMediator<Int, BookEntity>() {

    private val bookDao = db.bookDao()
    private val remoteKeyDao = db.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>,
    ): MediatorResult {
        val key = when (loadType) {
            REFRESH -> {
                if (bookDao.count() > 0) return MediatorResult.Success(false)
                null
            }
            PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            APPEND -> {
                if (state.lastItemOrNull() == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = false
                    )
                }
                getKey()
            }
        }

        try {
            if (key != null) {
                if (key.isEndReached) return MediatorResult.Success(endOfPaginationReached = true)
            }

            val page: Int = key?.nextKey ?: DEFAULT_PAGE_INDEX
            val pageSize = state.config.pageSize
            val offset = page * pageSize
            val apiResponse = api.getBooks(page = page)

            val books = apiResponse.results

            val endOfPaginationReached = page * pageSize >= apiResponse.count

            db.withTransaction {

                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                remoteKeyDao.insert(
                    RemoteKeyEntity(
                        0,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        isEndReached = endOfPaginationReached
                    )
                )
                bookDao.insertAll(BookEntityMapper.fromDtoList(books))
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKey(): RemoteKeyEntity? {
        return remoteKeyDao.getKeyByRepoId(0)
    }
}