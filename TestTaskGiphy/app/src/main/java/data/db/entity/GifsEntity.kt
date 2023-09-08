package data.db.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import data.model.GifData



@Entity("gifs_table")
data class GifsEntity(
    @PrimaryKey
    val id: Int = 0,
    val gifsUrls: List<GifData>
)
