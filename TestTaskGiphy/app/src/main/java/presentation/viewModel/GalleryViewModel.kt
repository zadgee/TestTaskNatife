package presentation.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import data.db.entity.GifsEntity
import data.model.GifsResponseModel
import domain.usecases.GetGifsFromInternetUseCase
import domain.usecases.GettingGifsFromDataBaseUseCase
import domain.usecases.SaveGifsToDataBaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel@Inject constructor(
    private val getGifsFromInternetUseCase: GetGifsFromInternetUseCase,
    private val gettingGifsFromDataBaseUseCase: GettingGifsFromDataBaseUseCase,
    private val saveGifsToDataBaseUseCase: SaveGifsToDataBaseUseCase
):ViewModel() {

    private val _gettingGifsFromInternetState = MutableLiveData<Response<GifsResponseModel>>()
    val getGifsFromInternetState:LiveData<Response<GifsResponseModel>> = _gettingGifsFromInternetState

   private val _gettingGifsFromDataBaseState = MutableLiveData<GifsEntity>()
    val getGifsFromDataBaseState:LiveData<GifsEntity> = _gettingGifsFromDataBaseState

    fun getGifsListFromInternet(){
        viewModelScope.launch{
           _gettingGifsFromInternetState.value = getGifsFromInternetUseCase.getGifsFromInternet()
        }
    }

    fun getGifsFromDataBase(){
        viewModelScope.launch{
            _gettingGifsFromDataBaseState.value = gettingGifsFromDataBaseUseCase.getGifs()
        }
    }

    fun saveGifsToDataBase(gifsEntity: GifsEntity){
        viewModelScope.launch{
            saveGifsToDataBaseUseCase.saveGifs(gifsEntity)
        }
    }


}