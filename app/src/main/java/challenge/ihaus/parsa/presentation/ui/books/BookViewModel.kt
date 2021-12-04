package challenge.ihaus.parsa.presentation.ui.books

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import challenge.ihaus.parsa.domain.usecase.book.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    getBooksUseCase: GetBooksUseCase,
) : ViewModel() {

    val books = getBooksUseCase.invoke(GetBooksUseCase.FilterBy.NONE).cachedIn(viewModelScope)
}