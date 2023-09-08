package domain.usecases

import data.db.entity.GifsEntity
import data.repository.SavingGifsRepository
import javax.inject.Inject

class GettingGifsFromDataBaseUseCase@Inject constructor(
    private val repository: SavingGifsRepository
){

    suspend fun getGifs(): GifsEntity {
        return repository.getGifs()
    }

}