package data.repository

import data.model.GifsResponseModel
import retrofit2.Response

interface GifRepository {
    suspend fun getGifs():Response<GifsResponseModel>
}