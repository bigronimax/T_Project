package com.example.t_project.presentation.jokeDetails

import android.content.Context
import android.widget.Toast
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
import com.example.t_project.domain.usecases.jokesRepository.GetJokeItemUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class JokeDetailsViewModel (
    private val jokesRepository: JokesRepository
): ViewModel() {
    private val getJokeItemUseCase by lazy { GetJokeItemUseCase(jokesRepository = jokesRepository) }

    val jokesLiveData = MutableLiveData<Joke>()

    fun getJokeItem(jokeId: String?, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            if (jokeId == null) {
                Toast.makeText(context, "Invalid joke data!", Toast.LENGTH_SHORT).show()
            } else {
                jokesLiveData.postValue(getJokeItemUseCase.execute(jokeId))
            }
        }
    }

    companion object {
        fun provideFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                JokeDetailsViewModel(
                    jokesRepository = JokesRepositoryImpl(
                        ApiDataSource(),
                        JokesDataBase.INSTANCE.jokeDao(),
                        JokeMapper()
                    ),
                )
            }
        }
    }

}