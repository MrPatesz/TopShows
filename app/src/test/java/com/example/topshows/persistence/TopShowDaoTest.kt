package com.example.topshows.persistence

import com.example.topshows.MainCoroutinesRule
import com.example.topshows.model.TopShow
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.hamcrest.core.Is.`is`

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class TopShowDaoTest : LocalDatabase() {
    private lateinit var topShowDao: TopShowDao

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    @Before
    fun init() {
        topShowDao = db.topShowDao()
    }

    @Test
    fun insertAndLoadTopShowsTest() = runTest {
        // Arrange
        val mockData1 = TopShow.mock1()
        val mockData2 = TopShow.mock2()
        val mockDataList = listOf(mockData1, mockData2)

        // Act
        topShowDao.insertTopShows(mockDataList)
        val loadFromDB = topShowDao.getTopShows()

        // Assert
        MatcherAssert.assertThat(loadFromDB.size, `is`(mockDataList.size))
        MatcherAssert.assertThat(loadFromDB.first{it.id == mockData1.id}.toString(), `is`(mockData1.toString()))
        MatcherAssert.assertThat(loadFromDB.first{it.id == mockData2.id}.toString(), `is`(mockData2.toString()))
    }

    @Test
    fun insertAndLoadSingleTopShowTest() = runTest {
        // Arrange
        val mockData = TopShow.mock1()

        // Act
        topShowDao.insertTopShow(mockData)
        val loadFromDB = topShowDao.getTopShow(mockData.id)

        // Assert
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(mockData.toString()))
    }

    @Test
    fun insertAndUpdateAndLoadSingleTopShowTest() = runTest {
        // Arrange
        val mockData1 = TopShow.mock1()
        val mockData2 = TopShow.mock1("A törvény nevében")

        // Act
        topShowDao.insertTopShow(mockData1)
        topShowDao.updateTopShow(mockData2)
        val loadFromDB = topShowDao.getTopShow(mockData1.id)

        // Assert
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(mockData2.toString()))
    }

    @Test
    fun insertTopShowsAndDeleteAllTest() = runTest {
        // Arrange
        val mockData1 = TopShow.mock1()
        val mockData2 = TopShow.mock2()
        val mockDataList = listOf(mockData1, mockData2)

        // Act
        topShowDao.insertTopShows(mockDataList)
        topShowDao.deleteAllTopShows()
        val loadFromDB = topShowDao.getTopShows()

        // Assert
        MatcherAssert.assertThat(loadFromDB.size, `is`(0))
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(listOf<TopShow>().toString()))
    }

    @Test
    fun insertTopShowsAndDeleteSingleTest() = runTest {
        // Arrange
        val mockData1 = TopShow.mock1()
        val mockData2 = TopShow.mock2()
        val mockDataList = listOf(mockData1, mockData2)

        // Act
        topShowDao.insertTopShows(mockDataList)
        topShowDao.deleteTopShow(mockData1)
        val loadFromDB = topShowDao.getTopShows()

        // Assert
        MatcherAssert.assertThat(loadFromDB.size, `is`(1))
        MatcherAssert.assertThat(loadFromDB.toString(), `is`(listOf(mockData2).toString()))
        MatcherAssert.assertThat(loadFromDB.firstOrNull{it.id == mockData1.id}.toString(), `is`(null.toString()))
        MatcherAssert.assertThat(loadFromDB.firstOrNull{it.id == mockData2.id}.toString(), `is`(mockData2.toString()))
    }
}