package challenge.ihaus.parsa.domain.usecase.book

import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.repository.BookRepository
import javax.inject.Inject

class ToggleBookFavoriteUseCaseImp @Inject constructor(private val bookRepository: BookRepository) :
    ToggleBookFavoriteUseCase {

    override suspend fun invoke(book: Book) {
        if (book.isFavorite) {
            bookRepository.makeBookUnFavorite(book)
        } else {
            bookRepository.makeBookFavorite(book)
        }
    }

}