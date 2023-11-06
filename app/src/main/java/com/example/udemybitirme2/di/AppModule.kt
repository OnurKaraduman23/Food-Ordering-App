package com.example.udemybitirme2.di

import android.content.Context
import androidx.room.Room
import com.example.udemybitirme2.data.datasource.YemeklerDataSource
import com.example.udemybitirme2.data.repo.YemeklerRepository
import com.example.udemybitirme2.retrofit.ApiUtils
import com.example.udemybitirme2.retrofit.YemeklerDao
import com.example.udemybitirme2.room.Database
import com.example.udemybitirme2.room.FavoriYemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDataSource(yDao:YemeklerDao,fyDao:FavoriYemeklerDao) : YemeklerDataSource {
        return YemeklerDataSource(yDao,fyDao)
    }

    @Provides
    @Singleton
    fun providesYemeklerRepository(yDso:YemeklerDataSource):YemeklerRepository {
        return YemeklerRepository(yDso)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }

    @Provides
    @Singleton
    fun provideFavoriYemeklerDao(@ApplicationContext context: Context) : FavoriYemeklerDao {
        val vt = Room.databaseBuilder(context,Database::class.java,"FavoriYemekler2.sqlite")
            .createFromAsset("FavoriYemekler2.sqlite").build()
        return vt.getFavoriYemeklerDao()
    }
}