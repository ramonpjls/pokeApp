package com.example.pokeapp.data.repository

import android.app.Application
import com.example.pokeapp.R
import com.example.pokeapp.data.api.PokeApi
import com.example.pokeapp.data.mapper.toPokeItem
import com.example.pokeapp.domain.model.PokeItem
import com.example.pokeapp.domain.repository.PokeRepository
import com.example.pokeapp.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApi,
    private val application: Application
): PokeRepository {

    override suspend fun getPokeResult(word: String): Flow<Result<PokeItem>> {
        return flow {
            emit(Result.Loading(true))

            val remotePokeResultDto = try {
                pokeApi.getApiResults(word)
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_catch_up)))
                emit(Result.Loading(false))
                return@flow
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_catch_up)))
                emit(Result.Loading(false))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(application.getString(R.string.can_t_catch_up)))
                emit(Result.Loading(false))
                return@flow
            }

            emit(Result.Success(remotePokeResultDto.toPokeItem()))
            emit(Result.Error(application.getString(R.string.can_t_catch_up)))
            emit(Result.Loading(false))
        }
    }
}
