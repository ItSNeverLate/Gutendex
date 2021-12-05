package challenge.ihaus.parsa.presentation.ui.book_detail

import androidx.lifecycle.*
import challenge.ihaus.parsa.domain.model.Book
import challenge.ihaus.parsa.domain.usecase.book.ToggleBookFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val toggleBookFavoriteUseCase: ToggleBookFavoriteUseCase,
    savedState: SavedStateHandle,
) : ViewModel() {

    val book = savedState.get<Book>("book")
    private val _isFavoriteLiveData = MutableLiveData<Boolean>(book?.isFavorite ?: false)

    val isFavoriteLiveData: LiveData<Boolean>
        get() = _isFavoriteLiveData

    private val bookDetailEventChannel = Channel<BookDetailEvent>()
    val bookDetailEvent = bookDetailEventChannel.receiveAsFlow()

    fun onBookFavoriteToggle(book: Book) = viewModelScope.launch {
        toggleBookFavoriteUseCase.invoke(book)
        _isFavoriteLiveData.value = _isFavoriteLiveData.value?.let { !it }
        if (book.isFavorite) {
            bookDetailEventChannel.send(BookDetailEvent.ShowMakeBookFavoriteMessage)
        } else {
            bookDetailEventChannel.send(BookDetailEvent.ShowMakeBookUnFavoriteMessage)
        }
    }

    sealed class BookDetailEvent {
        object ShowMakeBookFavoriteMessage : BookDetailEvent()
        object ShowMakeBookUnFavoriteMessage : BookDetailEvent()
    }
}