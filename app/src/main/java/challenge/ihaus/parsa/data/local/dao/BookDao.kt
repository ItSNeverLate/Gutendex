package challenge.ihaus.parsa.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import challenge.ihaus.parsa.data.local.entity.BookEntity
import challenge.ihaus.parsa.domain.usecase.book.GetBooksUseCase

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BookEntity>)

    fun getAllByFilter(filterBy: GetBooksUseCase.FilterBy): PagingSource<Int, BookEntity> =
        when (filterBy) {
            GetBooksUseCase.FilterBy.NONE -> getAll()
            GetBooksUseCase.FilterBy.CENTURY_19TH -> getAll19thCentury()
            GetBooksUseCase.FilterBy.FREE_COPY_RIGHT -> getAllFreeCopyRight()
        }

    @Query("SELECT * FROM books")
    fun getAll(): PagingSource<Int, BookEntity>

    @Query("SELECT * FROM books WHERE copyright == 0")
    fun getAllFreeCopyRight(): PagingSource<Int, BookEntity>

    @Query("SELECT *  FROM books WHERE authorBirthYear BETWEEN '1801' AND '1900' ")
    fun getAll19thCentury(): PagingSource<Int, BookEntity>

    @Query("DELETE FROM books")
    suspend fun clearAll()

    @Query("SELECT COUNT(id) FROM books")
    suspend fun count(): Int
}