package challenge.ihaus.parsa.di.module

import androidx.paging.ExperimentalPagingApi
import challenge.ihaus.parsa.data.local.AppDatabase
import challenge.ihaus.parsa.data.remote.AppApi
import challenge.ihaus.parsa.data.repository.BookRepositoryImp
import challenge.ihaus.parsa.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @ExperimentalPagingApi
    @Provides
    @Singleton
    fun provideBookRepository(api: AppApi, db: AppDatabase): BookRepository =
        BookRepositoryImp(api, db)
}