package com.ortizzakarie.androidkotlinchallenge.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.ortizzakarie.androidkotlinchallenge.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception

//TODO: Make this below comment green.
//This repository will provide the resources from the local db as well as the remote API.
// RESULT is the type for the database.
// REQUEST is the type for the network.
@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<State<RESULT>> {

        //Emit loading state
        emit(State.loading())

        try {
            //Emit database content first as a precaution.
            emit(State.success(fetchFromLocal().first()))

            //Fetch most up to date data from remote API.
            val apiResponse = fetchFromRemote()

            //Parse body of response.
            val remoteData = apiResponse.body()

            if(apiResponse.isSuccessful && remoteData != null) {
                saveRemoteData(remoteData)
            } else {
                //Error occurred, emit state.
                emit(State.error(apiResponse.message()))
            }
        } catch (e: Exception) {
            //Exception occurred, use the State.error to emit an error.
            emit(State.error("Network error! Can't reach servers."))
            e.printStackTrace()
        }

        //Retrieve data from persistence/local storage and emit
        emitAll(
            fetchFromLocal().map {
                State.success<RESULT>(it)
            }
        )
    }

    //Saves retrieved data from remote into the persistence/local storage.
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    //Retrieves all data from persistence/local storage.
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    //Fetches data from the remote API.
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>
}