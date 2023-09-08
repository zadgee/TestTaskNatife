package data.di

import android.content.Context
import androidx.room.Room
import const.GIFS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.db.GifsDataBase
import data.db.dao.GifsDao
import data.repository.SavingGifsRepository
import data.repository.SavingGifsRepositoryImpl
import domain.usecases.GettingGifsFromDataBaseUseCase
import domain.usecases.SaveGifsToDataBaseUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDataBase(@ApplicationContext context: Context):GifsDataBase{
       return Room.databaseBuilder(
           context.applicationContext,
           GifsDataBase::class.java,
           GIFS_DATABASE
       ).build()
    }

    @Provides
    @Singleton
    fun provideGifsDao(gifsDataBase: GifsDataBase): GifsDao {
        return gifsDataBase.gifsDao()
    }

    @Provides
    @Singleton
    fun provideSavingGifsRepository(gifsDao: GifsDao): SavingGifsRepository {
        return SavingGifsRepositoryImpl(gifsDao)
    }

    @Provides
    @Singleton
    fun provideSaveGifsToDataBaseUseCase(
        savingGifsRepository: SavingGifsRepository
    ): SaveGifsToDataBaseUseCase {
        return SaveGifsToDataBaseUseCase(savingGifsRepository)
    }

    @Provides
    @Singleton
    fun provideGettingGifsFromDataBaseUseCase(
        savingGifsRepository: SavingGifsRepository
    ): GettingGifsFromDataBaseUseCase {
        return GettingGifsFromDataBaseUseCase(savingGifsRepository)
    }






}