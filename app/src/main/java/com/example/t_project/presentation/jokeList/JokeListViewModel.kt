package com.example.t_project.presentation.jokeList

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.t_project.data.datasource.database.JokesDataBase
import com.example.t_project.data.datasource.internet.ApiDataSource
import com.example.t_project.domain.entity.Joke
import com.example.t_project.data.mapper.JokeMapper
import com.example.t_project.domain.repos.JokesRepository
import com.example.t_project.data.reposImpl.JokesRepositoryImpl
import com.example.t_project.domain.usecases.jokesRepository.LoadJokesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class JokeListViewModel @Inject constructor(
    private val jokesRepository: JokesRepository,
): ViewModel() {

    private val loadJokesUseCase by lazy { LoadJokesUseCase(jokesRepository = jokesRepository) }

    val jokesLiveData = MutableLiveData<List<Joke>>()
    val progressLiveData = MutableLiveData<Boolean>(false)

    fun loadJokes(remoteLoad: Boolean = true, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            progressLiveData.postValue(true)
            val jokes = loadJokesUseCase.execute(remoteLoad, checkForInternet(context))
            progressLiveData.postValue(false)
            jokesLiveData.postValue(jokes)
        }
    }

    fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }

    }

    companion object {
//        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                JokeListViewModel(
//                    jokesRepository = JokesRepositoryImpl(
//                        ApiDataSource(),
//                        JokesDataBase.INSTANCE.jokeDao(),
//                        JokeMapper()
//                    ),
//                )
//            }
//        }
    }

}