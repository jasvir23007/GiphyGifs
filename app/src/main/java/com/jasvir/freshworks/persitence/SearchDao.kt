package com.jasvir.freshworks.persitence

import androidx.room.*
import com.jasvir.freshworks.utils.Constants

@Dao
interface SearchDao {

    @Query("SELECT * FROM Freshworks_DB")
    suspend fun findAll(): List<SearchDB>

    @Query("SELECT count(*) FROM ${Constants.TABLE_Freshworks}")
    suspend fun getCount(): Int

    @Query("SELECT * FROM Freshworks_DB WHERE id = :id")
    suspend fun findById(id: String): SearchDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchDB: SearchDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(searches: ArrayList<SearchDB>)

    @Delete
    suspend fun delete(searchDB: SearchDB)

    @Query("DELETE FROM ${Constants.TABLE_Freshworks}")
    suspend fun deleteAllData()

    @Query("DELETE FROM Freshworks_DB WHERE id = :userId")
    fun deleteById(userId: String)


}