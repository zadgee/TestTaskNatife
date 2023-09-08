package data.repository
import data.api.GifAPI
import data.model.GifsResponseModel
import retrofit2.Response
import javax.inject.Inject

class GifRepositoryImpl@Inject constructor(
    private val api:GifAPI
): GifRepository {

    override suspend fun getGifs(): Response<GifsResponseModel> {
        val response = api.getGifs()
        if(response.isSuccessful){
            response.body()
        }
       return response
    }
}