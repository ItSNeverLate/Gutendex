package challenge.ihaus.parsa.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import challenge.ihaus.parsa.data.local.entity.BookEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<BookEntity>)

    @Query("SELECT * FROM books")
    fun getAll(): PagingSource<Int, BookEntity>

    @Query("DELETE FROM books")
    suspend fun clearAll()
}