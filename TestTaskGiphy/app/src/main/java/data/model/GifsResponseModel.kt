package data.model
import com.google.gson.annotations.SerializedName

data class GifsResponseModel(
    val data:ArrayList<GifData>
)

data class GifData(
    val images:GifDetails
)

data class GifDetails(
    @SerializedName("downsized")
    val fullscreenGif:DownSizedGif,
    @SerializedName("fixed_height_small")
    val smallGif:SmallSizeGif

)

data class DownSizedGif(
    @SerializedName("url")
    val url:String
)

data class SmallSizeGif(
    @SerializedName("url")
    val url:String
)
