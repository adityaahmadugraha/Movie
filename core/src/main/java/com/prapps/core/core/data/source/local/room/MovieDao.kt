package com.prapps.core.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prapps.core.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(movieEntitiy: MovieEntity)

    @Delete
    suspend fun deleteData(movieEntitiy: MovieEntity)

    @Query("SELECT * FROM movie")
    fun getAllData(): Flow<List<MovieEntity>>

}