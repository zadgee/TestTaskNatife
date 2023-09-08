package domain.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import data.repository.GifRepository
import domain.usecases.GetGifsFromInternetUseCase


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

   @Provides
   fun provideGetGifsFromInternetUseCase(repository: GifRepository): GetGifsFromInternetUseCase {
       return GetGifsFromInternetUseCase(
           repository
       )
   }

}