package challenge.ihaus.parsa.data.local.mapper

import challenge.ihaus.parsa.data.local.entity.BookEntity
import challenge.ihaus.parsa.data.remote.dto.BookDto
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.util.Mapper

@Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
object BookEntityMapper : Mapper<BookEntity, Book> {

    override fun toModel(entity: BookEntity) = Book(
        id = entity.id,
        title = entity.title,
        subjects = entity.subjects,
        authors = PersonEntityMapper.toModelList(entity.authors),
        translators = PersonEntityMapper.toModelList(entity.translators),
        bookshelves = entity.bookshelves,
        languages = entity.languages,
        copyright = entity.copyright,
        mediaType = entity.mediaType,
        otherInformation = entity.otherInformation,
        downloadCount = entity.downloadCount,
        imageUrl = entity.otherInformation["image/jpeg"] ?: "",
        isFavorite = entity.isFavorite
    )

    override fun toModelList(list: List<BookEntity>): List<Book> = list.map {
        toModel(it)
    }

    private fun fromDto(dto: BookDto): BookEntity {
        var firstAuthorBirthYear: Int? = null
        var firstAuthorDeathYear: Int? = null
        if (dto.authors.isNotEmpty()) {
            firstAuthorBirthYear = dto.authors[0].birthYear
            firstAuthorDeathYear = dto.authors[0].deathYear
        }

        return BookEntity(
            id = dto.id,
            title = dto.title,
            subjects = dto.subjects,
            authors = PersonEntityMapper.fromDtoList(dto.authors),
            translators = PersonEntityMapper.fromDtoList(dto.translators),
            bookshelves = dto.bookshelves,
            languages = dto.languages,
            copyright = dto.copyright,
            mediaType = dto.mediaType,
            otherInformation = dto.formats,
            downloadCount = dto.downloadCount,
            authorBirthYear = firstAuthorBirthYear,
            authorDeathYear = firstAuthorDeathYear
        )
    }

    fun fromDtoList(list: List<BookDto>): List<BookEntity> =
        list.map {
            fromDto(it)
        }
}