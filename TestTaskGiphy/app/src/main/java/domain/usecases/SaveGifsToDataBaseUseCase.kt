package domain.usecases

import data.db.entity.GifsEntity
import data.repository.SavingGifsRepository
import javax.inject.Inject

class SaveGifsToDataBaseUseCase@Inject constructor(
    private val savingGifsRepository: SavingGifsRepository
) {

   suspend fun saveGifs(gifsEntity: GifsEntity){
       return savingGifsRepository.saveGifs(gifsEntity)
   }

}