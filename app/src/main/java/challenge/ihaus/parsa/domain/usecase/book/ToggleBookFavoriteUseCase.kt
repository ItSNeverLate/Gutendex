package challenge.ihaus.parsa.domain.usecase.book

import challenge.ihaus.parsa.domain.model.Book

interface ToggleBookFavoriteUseCase {
    suspend operator fun invoke(book: Book)
}