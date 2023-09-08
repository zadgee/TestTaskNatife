package data.db
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import data.db.dao.GifsDao
import data.db.entity.GifsEntity
import data.db.typeconverter.GifsResponseModelTypeConverter

@Database(entities = [GifsEntity::class], version = 4, exportSchema = true)
@TypeConverters(GifsResponseModelTypeConverter::class)
abstract class GifsDataBase:RoomDatabase(){
    abstract fun gifsDao(): GifsDao
}