package challenge.ihaus.parsa.data.remote

import challenge.ihaus.parsa.data.remote.response.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AppApi {

    companion object {
        const val BASE_URL = "http://gutendex.com"
    }

    @GET("books/")
    suspend fun getBooks(
        @Query(value = "page") page: Int,
    ): BookResponse
}