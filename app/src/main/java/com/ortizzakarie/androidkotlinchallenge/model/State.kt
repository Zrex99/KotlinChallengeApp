package com.ortizzakarie.androidkotlinchallenge.model

//This class is for broad state management of the UI & Data.
sealed class State<T> {

    //Each of these lower classes subclass from the super State class.
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val message: String) : State<T>()

    //Each fun here returns one of the above classes,
    // that was instantiated with the correct result.
    companion object {

        //Returns Loading instance.
        fun <T> loading() = Loading<T>()

        //Returns Success instance with data retrieved.
        fun <T> success(data: T) =
            Success(data)

        //Returns Error instance with description of error.
        fun <T> error(message: String) =
            Error<T>(message)
    }
}