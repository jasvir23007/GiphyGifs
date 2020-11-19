package com.jasvir.freshworks

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jasvir.freshworks.persitence.AppDataBase
import com.jasvir.freshworks.persitence.SearchDB
import com.jasvir.freshworks.persitence.SearchDao
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var searchDao: SearchDao
    private lateinit var db: AppDataBase
    private lateinit var searchDB: SearchDB
    private lateinit var searchDB2: SearchDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()



        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        searchDao = db.getDao()
        searchDB = SearchDB("Recipe1", "https://media3.giphy.com/media/YqnXSeq7AFSYjAAhpU/200w_s.gif"
            , 1

        )
        searchDB2 = SearchDB("Recipe2", "https://media3.giphy.com/media/YqnXSeq7AFSYjAAhpU/200w_s.gif", 0)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun should_Insert_Item() {

        runBlocking {
            searchDao.insert(searchDB)
            val searchDBTest = getValue<SearchDB>(searchDao.findById(searchDB.id))
            Assert.assertEquals(searchDBTest.id, searchDB.id)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Get_All_Posts() {
        runBlocking {
            searchDao.insert(searchDB)
            searchDao.insert(searchDB2)
            val searchDBTest = getValue<List<SearchDB>>(searchDao.findAll())
            Assert.assertEquals(searchDBTest.size, 2)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Replace_Post_Post() {
        runBlocking {
            searchDao.insert(searchDB)
            searchDao.insert(searchDB)
            val searchDBTest = getValue<List<SearchDB>>(searchDao.findAll())
            Assert.assertEquals(searchDBTest.size, 1)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_Post() {
        runBlocking {
            searchDao.insert(searchDB)
            searchDao.delete(searchDB)
            val itemDBTest = getValue<SearchDB>(searchDao.findById(searchDB.id))
            Assert.assertNull(itemDBTest)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_All_Data() {
        runBlocking {
            searchDao.insert(searchDB)
            searchDao.deleteAllData()
            Assert.assertEquals(searchDao.getCount(), 0)
        }
    }
}