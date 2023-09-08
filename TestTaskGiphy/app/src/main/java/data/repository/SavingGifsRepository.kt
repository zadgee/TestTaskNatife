package data.repository
import data.db.entity.GifsEntity

interface SavingGifsRepository {
    suspend fun saveGifs(gifsList: GifsEntity)
    suspend fun getGifs(): GifsEntity
}