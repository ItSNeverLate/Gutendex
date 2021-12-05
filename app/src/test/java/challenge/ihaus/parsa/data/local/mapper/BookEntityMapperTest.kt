package challenge.ihaus.parsa.data.local.mapper

import challenge.ihaus.parsa.data.local.entity.BookEntity
import challenge.ihaus.parsa.data.local.entity.PersonEntity
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.model.Person
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class BookEntityMapperTest {

    @Test
    fun `convert Book Entity to Book Model`() {
        val bookEntity = BookEntity(
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

        val bookModel = BookEntityMapper.toModel(bookEntity)

        assertThat(bookModel.title == bookModel.title)
    }

    @Test
    fun `convert Books Entity List to Books Model List`() {
        val bookEntity = BookEntity(
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

        val booksModelList = BookEntityMapper.toModelList(listOf(bookEntity,bookEntity))

        assertThat(booksModelList.size == 2)
    }

}
