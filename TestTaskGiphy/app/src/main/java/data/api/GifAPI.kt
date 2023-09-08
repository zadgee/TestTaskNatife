package data.api
import const.API_KEY
import data.model.GifsResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface GifAPI {
    @GET("search?api_key=$API_KEY&q=sticker&limit=30")
    suspend fun getGifs():Response<GifsResponseModel>
}