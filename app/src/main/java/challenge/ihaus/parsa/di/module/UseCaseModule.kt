package challenge.ihaus.parsa.di.module

import challenge.ihaus.parsa.domain.repository.BookRepository
import challenge.ihaus.parsa.domain.usecase.book.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetBooksUseCase(bookRepository: BookRepository): GetBooksUseCase =
        GetBooksUseCaseImp(bookRepository)

    @Provides
    @Singleton
    fun provideToggleBookFavoriteUseCase(bookRepository: BookRepository): ToggleBookFavoriteUseCase =
        ToggleBookFavoriteUseCaseImp(bookRepository)
}