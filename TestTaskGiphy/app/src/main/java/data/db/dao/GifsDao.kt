package data.db.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.db.entity.GifsEntity

@Dao
interface GifsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGifs(gifsList: GifsEntity)

    @Query("SELECT * FROM gifs_table ORDER BY ID ASC")
    suspend fun getGifs():GifsEntity
}