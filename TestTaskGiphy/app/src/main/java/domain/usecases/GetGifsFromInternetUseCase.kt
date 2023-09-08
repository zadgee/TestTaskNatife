package domain.usecases
import data.model.GifsResponseModel
import data.repository.GifRepository
import retrofit2.Response
import javax.inject.Inject

class GetGifsFromInternetUseCase@Inject constructor(
    private val repository: GifRepository
) {

    suspend fun getGifsFromInternet(): Response<GifsResponseModel> {
       return repository.getGifs()
    }
}