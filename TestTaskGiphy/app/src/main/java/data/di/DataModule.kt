package data.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.api.GifAPI
import data.repository.GifRepository
import data.repository.GifRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(api:GifAPI): GifRepository {
        return GifRepositoryImpl(
            api
        )
    }
}