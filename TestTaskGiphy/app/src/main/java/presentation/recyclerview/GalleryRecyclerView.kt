package presentation.recyclerview
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.testtaskgiphy.R
import data.model.GifData
import presentation.screens.GalleryFragmentDirections
import javax.inject.Inject

class GalleryRecyclerView @Inject constructor(
    private val view:View
): RecyclerView.Adapter<GalleryRecyclerView.GalleryItemsViewHolder>() {

    class GalleryItemsViewHolder(view: View):RecyclerView.ViewHolder(view)
    private var listWithPhotos = ArrayList<GifData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GalleryItemsViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(
           R.layout.gallery_list_layout,parent,false
       )
        return GalleryItemsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: GalleryItemsViewHolder,
        position: Int
    ) {
        val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(view)
            .load(listWithPhotos[position].images.smallGif.url)
            .apply(requestOptions)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("TAG_GLIDE", "Glide load failed: ${e?.message}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(holder.itemView.findViewById(R.id.gif_image))
        holder.itemView.findViewById<ImageView>(R.id.gif_image).setOnClickListener{
            val gif = listWithPhotos[position].images.fullscreenGif.url
            val action = GalleryFragmentDirections.actionGalleryFragmentToFullSizeGifFragment(gif)
            view.findNavController().navigate(action)
        }
    }


    override fun getItemCount(): Int {
        return listWithPhotos.size
    }

    fun createListWithGifs(list: List<GifData>){
        listWithPhotos.addAll(list)
        notifyDataSetChanged()
    }


}