package presentation.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtaskgiphy.databinding.FragmentGalleryBinding
import const.ALREADY_PARSED_DATA
import const.USER_PREFERENCES
import dagger.hilt.android.AndroidEntryPoint
import data.db.entity.GifsEntity
import kotlinx.coroutines.launch
// import data.db.model.GifModel
import presentation.recyclerview.GalleryRecyclerView
import presentation.viewModel.GalleryViewModel

@AndroidEntryPoint
class GalleryFragment : Fragment() {

   private var binding: FragmentGalleryBinding? = null
   private var adapter:GalleryRecyclerView? = null
   private val viewModel by viewModels<GalleryViewModel>()
   private lateinit var sharedPreferences:SharedPreferences




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGalleryBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
        adapter = GalleryRecyclerView(view)
        val galleryList = binding?.galleryList
        galleryList?.layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        galleryList?.adapter = adapter

        val isUserAlreadyParsedDataFromInternet = sharedPreferences.getBoolean(ALREADY_PARSED_DATA, false)

        if(isUserAlreadyParsedDataFromInternet){
              Log.d("TAG","DATA IS GETTING FROM DATABASE")
              viewModel.getGifsFromDataBase()
              viewModel.getGifsFromDataBaseState.observe(viewLifecycleOwner){
                      entity->
                  adapter?.createListWithGifs(entity.gifsUrls)
              }
        } else{
            viewModel.getGifsListFromInternet()
            viewModel.getGifsFromInternetState.observe(viewLifecycleOwner){ response->
                response.body()?.data?.let {
                    adapter?.createListWithGifs(it)
                    viewModel.saveGifsToDataBase(
                        GifsEntity(
                            gifsUrls = it
                        )
                    )
                    with(sharedPreferences.edit()){
                        putBoolean(ALREADY_PARSED_DATA, true)
                        apply()
                    }
                }
            }
        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        adapter = null
    }

}