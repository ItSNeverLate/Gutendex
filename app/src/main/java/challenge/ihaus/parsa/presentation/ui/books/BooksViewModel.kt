package challenge.ihaus.parsa.presentation.ui.books

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.usecase.book.GetBooksUseCase
import challenge.ihaus.parsa.domain.usecase.book.ToggleBookFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    getBooksUseCase: GetBooksUseCase,
    savedState: SavedStateHandle,
) : ViewModel() {

    private val filterByLiveData = savedState.getLiveData("filterBy", GetBooksUseCase.FilterBy.NONE)

    var books = filterByLiveData.asFlow().flatMapLatest { filterBy ->
        getBooksUseCase.invoke(filterBy = filterBy)
            .cachedIn(viewModelScope)
    }

    fun onRemoveFilters() {
        filterByLiveData.value = GetBooksUseCase.FilterBy.NONE
    }

    fun onFilterByFreeCopyRight() {
        filterByLiveData.value = GetBooksUseCase.FilterBy.FREE_COPY_RIGHT
    }

    fun onFilterBy19thCentury() {
        filterByLiveData.value = GetBooksUseCase.FilterBy.CENTURY_19TH
    }

    fun onFilterByFavorites() {
        filterByLiveData.value = GetBooksUseCase.FilterBy.FAVORITES
    }
}