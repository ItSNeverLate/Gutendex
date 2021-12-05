package challenge.ihaus.parsa.data.local.dao


import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult.Page
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import challenge.ihaus.parsa.data.local.AppDatabase
import challenge.ihaus.parsa.data.local.entity.BookEntity
import challenge.ihaus.parsa.data.local.entity.FavoriteEntity
import challenge.ihaus.parsa.data.local.entity.PersonEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class BookDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var bookDao: BookDao
    private lateinit var favoriteDao: FavoriteDao

    // Dummy Data
    private val book1 = BookEntity(
        1,
        "title 1",
        listOf("Subject 1", "Subject 2"),
        listOf(
            PersonEntity(name = "author", birthYear = 1525, deathYear = 1600)),
        authorBirthYear = 1525,
        authorDeathYear = 1600,
        bookshelves = listOf(),
        languages = listOf("en"),
        mediaType = "text",
        translators = listOf(),
        otherInformation = HashMap(),
        copyright = false,
        downloadCount = 10,
    )
    private val book2 = BookEntity(
        2,
        "title 2",
        listOf("Subject 10", "Subject 20"),
        listOf(
            PersonEntity(name = "author", birthYear = 1825, deathYear = 1900)),
        authorBirthYear = 1825,
        authorDeathYear = 1900,
        bookshelves = listOf(),
        languages = listOf("en"),
        mediaType = "text",
        translators = listOf(),
        otherInformation = HashMap(),
        copyright = true,
        downloadCount = 10,
    )
    private val bookList = listOf(book1, book2)
    private val emptyList = listOf<BookEntity>()

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        bookDao = db.bookDao()
        favoriteDao = db.favoriteDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertAll() = runBlocking {
        bookDao.insertAll(bookList)

        val pagingSource = bookDao.getAll()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = bookList,
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getAll() = runBlocking {
        bookDao.insertAll(bookList)

        val pagingSource = bookDao.getAll()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = bookList,
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getAll19thCentury() = runBlocking {
        bookDao.insertAll(bookList)

        val pagingSource = bookDao.getAll19thCentury()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = listOf(book2),
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getAllFreeCopyRight() = runBlocking {
        bookDao.insertAll(bookList)

        val pagingSource = bookDao.getAllFreeCopyRight()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = listOf(book1),
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun getAllFavorites() = runBlocking {
        bookDao.insertAll(bookList)
        favoriteDao.insert(FavoriteEntity(2))

        val pagingSource = bookDao.getAllFavorites()

        val result = pagingSource.load(
            LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        val expected = Page(
            data = listOf(book2),
            prevKey = null,
            nextKey = null,
            itemsAfter = 0,
            itemsBefore = 0,
        )

        assertThat(expected).isEqualTo(result)
    }

    @Test
    fun deleteAll() = runBlocking {
        bookDao.insertAll(bookList)

        assertThat(bookDao.count()).isEqualTo(2)

        bookDao.clearAll()
        assertThat(bookDao.count()).isEqualTo(0)
    }

    @Test
    fun count() = runBlocking {
        bookDao.insertAll(bookList)

        val count = bookDao.count()
        assertThat(count).isEqualTo(2)
    }
}