package challenge.ihaus.parsa.data.repository

import challenge.ihaus.parsa.data.remote.AppApi
import challenge.ihaus.parsa.data.remote.response.BookResponse
import challenge.ihaus.parsa.util.JsonUtil
import com.google.gson.Gson

class FakeAppApi : AppApi {
    private val gson = Gson()

    override suspend fun getBooks(page: Int?): BookResponse {
        return gson.fromJson(JsonUtil.getJsonFileContent("resources/json/book/books_response.json"),
            BookResponse::class.java)
    }
}