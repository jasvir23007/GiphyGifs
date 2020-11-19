package com.jasvir.freshworks.persitence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jasvir.freshworks.model.Data


@Entity(tableName = "Freshworks_DB")
data class SearchDB(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "isFav") var isFav: Int

) {
    companion object {
        fun map(data: Data): SearchDB {
            return SearchDB(
                id = data.id,
                url = data.images.original.url, isFav = 0
            )
        }

        fun mapList(dataList: List<Data>): List<SearchDB> {
            val searchPostDBList = mutableListOf<SearchDB>()
            for (data in dataList) {
                searchPostDBList.add(map(data))
            }
            return searchPostDBList
        }
    }
}