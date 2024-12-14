package com.zows.hubxrecruitmentcase.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryEntity

@Dao
interface PlantCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plantCategories: List<PlantCategoryEntity>)

    @Query("SELECT * FROM plant_category_table")
    suspend fun getAllPlantCategories(): List<PlantCategoryEntity>

//    @Query("SELECT * FROM category_table WHERE title LIKE :searchedTitle")
//    suspend fun getSearchedPlantCategories(searchedTitle: String): List<PlantDomain>
}