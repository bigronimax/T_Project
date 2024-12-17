package com.example.t_project.di.module

import android.content.Context
import androidx.room.Room
import com.example.t_project.data.datasource.database.JokeDao
import com.example.t_project.data.datasource.database.JokesDataBase
import com.example.t_project.data.datasource.internet.Api
import com.example.t_project.data.datasource.internet.ApiDataSource
import com.example.t_project.data.datasource.internet.RequestInterceptor
import com.example.t_project.data.mapper.JokeMapper
import com.example.t_project.data.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.repos.JokesRemoteDataSource
import com.example.t_project.domain.repos.JokesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(RequestInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): JokesDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            JokesDataBase::class.java,
            "jokes_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideJokeDao(dataBase: JokesDataBase): JokeDao {
        return dataBase.jokeDao()
    }

    @Provides
    @Singleton
    fun provideJokesRemoteDataSource(api: Api): JokesRemoteDataSource {
        return ApiDataSource(api)
    }

    @Provides
    @Singleton
    fun provideJokeMapper(): JokeMapper {
        return JokeMapper()
    }

    @Provides
    @Singleton
    fun provideJokesRepository(repositoryImpl: JokesRepositoryImpl) : JokesRepository {
        return repositoryImpl
    }


    private companion object {

        private const val BASE_URL = "https://v2.jokeapi.dev/joke/"

    }
}