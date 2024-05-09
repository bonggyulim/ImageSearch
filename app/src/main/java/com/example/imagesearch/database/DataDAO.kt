package com.example.imagesearch.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data: EntityData)

    @Query("SELECT * FROM data_table")
    fun getAllData(): LiveData<List<EntityData>>
}