package data.repository

import android.util.Log
import data.db.dao.GifsDao
import data.db.entity.GifsEntity
import javax.inject.Inject

class SavingGifsRepositoryImpl@Inject constructor(
    private val dao: GifsDao
) : SavingGifsRepository {

    override suspend fun saveGifs(gifsList: GifsEntity) {
         try {
             dao.saveGifs(gifsList)
         }catch (e:Exception){
             e.printStackTrace()
             Log.d("TAG","Error occurred :${e.message}")
         }
    }

    override suspend fun getGifs(): GifsEntity {
        return dao.getGifs()
}
}