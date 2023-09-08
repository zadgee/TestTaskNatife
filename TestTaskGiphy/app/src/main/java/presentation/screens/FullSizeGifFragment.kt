package presentation.screens
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.testtaskgiphy.databinding.FragmentFullSizeGifBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullSizeGifFragment : Fragment() {
private var binding : FragmentFullSizeGifBinding? = null
private val args:FullSizeGifFragmentArgs by navArgs()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFullSizeGifBinding.inflate(layoutInflater)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gifUrl = args.gifUrl
        Glide.with(this).load(gifUrl).into(binding?.fullGifId!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}