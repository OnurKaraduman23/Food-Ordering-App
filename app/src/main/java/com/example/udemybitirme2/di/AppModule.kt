package com.example.udemybitirme2.di

import com.example.udemybitirme2.data.datasource.YemeklerDataSource
import com.example.udemybitirme2.data.repo.YemeklerRepository
import com.example.udemybitirme2.retrofit.ApiUtils
import com.example.udemybitirme2.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideYemeklerDataSource(yDao:YemeklerDao) : YemeklerDataSource {
        return YemeklerDataSource(yDao)
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
}