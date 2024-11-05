package com.prapps.core.core.data.source.local

import android.util.Log
import com.prapps.core.core.data.source.local.entity.MovieEntity
import com.prapps.core.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

class LocalDataSource(private val movieDao: MovieDao) {

    suspend fun insertData(movieEntitiy: MovieEntity) = movieDao.insertData(movieEntitiy)

    suspend fun deleteData(movieEntitiy: MovieEntity) = movieDao.deleteData(movieEntitiy)

    fun getAllData(): Flow<List<MovieEntity>> {
        return movieDao.getAllData().onStart {
            Log.d("LocalDataSource", "Mengambil semua data movie dari Room.")
        }
    }



}
