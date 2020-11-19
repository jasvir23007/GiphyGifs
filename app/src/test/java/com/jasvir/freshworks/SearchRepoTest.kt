package com.jasvir.freshworks

import com.jasvir.freshworks.base.BaseMockServerTest
import com.jasvir.freshworks.di.viewModelModule
import com.jasvir.freshworks.diTest.networkMockedComponent
import com.jasvir.freshworks.diTest.repoMockedDBModule
import com.jasvir.freshworks.persitence.SearchDao
import com.jasvir.freshworks.repo.SearchRepo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.mockito.Mockito.mock
import retrofit2.HttpException
import java.net.HttpURLConnection


@RunWith(JUnit4::class)
class SearchRepoTest : BaseMockServerTest() {

    private val searchRepo by inject<SearchRepo>()
    var daoMocked = mock(SearchDao::class.java)

    override fun setUp() {
        super.setUp()
        startKoin {
            modules(
                listOf(
                    viewModelModule,
                    networkMockedComponent(mockServer.url("/").toString()),
                    repoMockedDBModule(daoMocked)
                )
            )
        }
    }

    @Test
    fun search_result_ok() {
        mockHttpResponse(
            "{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"type\": \"gif\",\n" +
                    "      \"id\": \"3XQatO8S5YsVi\",\n" +
                    "      \"url\": \"https://giphy.com/gifs/foxadhd-thanksgiving-turkey-art-3XQatO8S5YsVi\",\n" +
                    "      \"slug\": \"foxadhd-thanksgiving-turkey-art-3XQatO8S5YsVi\",\n" +
                    "      \"bitly_gif_url\": \"http://gph.is/1c3ydAW\",\n" +
                    "      \"bitly_url\": \"http://gph.is/1c3ydAW\",\n" +
                    "      \"embed_url\": \"https://giphy.com/embed/3XQatO8S5YsVi\",\n" +
                    "      \"username\": \"foxadhd\",\n" +
                    "      \"source\": \"www.foxadhd.com\",\n" +
                    "      \"title\": \"Fox Animation Art GIF by Animation Domination High-Def\",\n" +
                    "      \"rating\": \"pg\",\n" +
                    "      \"content_url\": \"\",\n" +
                    "      \"source_tld\": \"\",\n" +
                    "      \"source_post_url\": \"www.foxadhd.com\",\n" +
                    "      \"is_sticker\": 0,\n" +
                    "      \"import_datetime\": \"2012-11-20 17:00:49\",\n" +
                    "      \"trending_datetime\": \"2020-11-17 11:00:12\",\n" +
                    "      \"images\": {\n" +
                    "        \"original\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\",\n" +
                    "          \"mp4_size\": \"398850\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.mp4\",\n" +
                    "          \"webp_size\": \"453614\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.webp\",\n" +
                    "          \"frames\": \"51\",\n" +
                    "          \"hash\": \"dd2398c34236a12a08e100a7290009d8\"\n" +
                    "        },\n" +
                    "        \"downsized\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_large\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_medium\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_small\": {\n" +
                    "          \"height\": \"352\",\n" +
                    "          \"width\": \"352\",\n" +
                    "          \"mp4_size\": \"102602\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-downsized-small.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-downsized-small.mp4\"\n" +
                    "        },\n" +
                    "        \"downsized_still\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_height\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"229825\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.gif\",\n" +
                    "          \"mp4_size\": \"92497\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.mp4\",\n" +
                    "          \"webp_size\": \"151922\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_downsampled\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"30939\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_d.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_d.gif\",\n" +
                    "          \"webp_size\": \"28348\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_d.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_d.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_small\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"98591\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.gif\",\n" +
                    "          \"mp4_size\": \"30780\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.mp4\",\n" +
                    "          \"webp_size\": \"59730\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_small_still\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"3161\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_height_still\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"6464\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_width\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"229825\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.gif\",\n" +
                    "          \"mp4_size\": \"92497\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.mp4\",\n" +
                    "          \"webp_size\": \"151922\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_downsampled\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"30939\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_d.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_d.gif\",\n" +
                    "          \"webp_size\": \"28348\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_d.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_d.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_small\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"98591\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.gif\",\n" +
                    "          \"mp4_size\": \"30780\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.mp4\",\n" +
                    "          \"webp_size\": \"59730\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_small_still\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"3161\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_width_still\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"6464\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_s.gif\"\n" +
                    "        },\n" +
                    "        \"looping\": {\n" +
                    "          \"mp4_size\": \"1870867\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-loop.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-loop.mp4\"\n" +
                    "        },\n" +
                    "        \"original_still\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"10787\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy_s.gif\"\n" +
                    "        },\n" +
                    "        \"original_mp4\": {\n" +
                    "          \"height\": \"480\",\n" +
                    "          \"width\": \"480\",\n" +
                    "          \"mp4_size\": \"398850\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.mp4\"\n" +
                    "        },\n" +
                    "        \"preview\": {\n" +
                    "          \"height\": \"276\",\n" +
                    "          \"width\": \"276\",\n" +
                    "          \"mp4_size\": \"35273\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.mp4\"\n" +
                    "        },\n" +
                    "        \"preview_gif\": {\n" +
                    "          \"height\": \"125\",\n" +
                    "          \"width\": \"125\",\n" +
                    "          \"size\": \"49925\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.gif\"\n" +
                    "        },\n" +
                    "        \"preview_webp\": {\n" +
                    "          \"height\": \"216\",\n" +
                    "          \"width\": \"216\",\n" +
                    "          \"size\": \"49478\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.webp\"\n" +
                    "        },\n" +
                    "        \"480w_still\": {\n" +
                    "          \"height\": \"480\",\n" +
                    "          \"width\": \"480\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/480w_s.jpg?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=480w_s.jpg\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"user\": {\n" +
                    "        \"avatar_url\": \"https://media3.giphy.com/avatars/foxadhd/FIpJVXV5aO80.gif\",\n" +
                    "        \"banner_image\": \"https://media3.giphy.com/avatars/foxadhd/GifrKcAjH8gg.jpg\",\n" +
                    "        \"banner_url\": \"https://media3.giphy.com/avatars/foxadhd/GifrKcAjH8gg.jpg\",\n" +
                    "        \"profile_url\": \"https://giphy.com/foxadhd/\",\n" +
                    "        \"username\": \"foxadhd\",\n" +
                    "        \"display_name\": \"Animation Domination High-Def\",\n" +
                    "        \"description\": \"Saturdays @11p/10c on Fox\",\n" +
                    "        \"instagram_url\": \"\",\n" +
                    "        \"website_url\": \"http://www.foxadhd.com\",\n" +
                    "        \"is_verified\": true\n" +
                    "      },\n" +
                    "      \"analytics_response_payload\": \"e=Z2lmX2lkPTNYUWF0TzhTNVlzVmkmZXZlbnRfdHlwZT1HSUZfVFJFTkRJTkcmY2lkPTAwMTg4MWYyZzdieDNjN3Q0Z3V6Mm5ndmlvbWVlY3BnM2RjOWcxYzIybThtdDhlOA\",\n" +
                    "      \"analytics\": {\n" +
                    "        \"onload\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=SEEN\"\n" +
                    "        },\n" +
                    "        \"onclick\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=CLICK\"\n" +
                    "        },\n" +
                    "        \"onsent\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=SENT\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}", HttpURLConnection.HTTP_OK
        )
        runBlocking {
            val searchListMocked = searchRepo.searchWithPagination("query", 1)
            assertNotNull(searchListMocked)
            assertEquals(searchListMocked.isNullOrEmpty(), false)
        }
    }

    @Test
    fun search_result_ok_single() {
        mockHttpResponse(
            "{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"type\": \"gif\",\n" +
                    "      \"id\": \"3XQatO8S5YsVi\",\n" +
                    "      \"url\": \"https://giphy.com/gifs/foxadhd-thanksgiving-turkey-art-3XQatO8S5YsVi\",\n" +
                    "      \"slug\": \"foxadhd-thanksgiving-turkey-art-3XQatO8S5YsVi\",\n" +
                    "      \"bitly_gif_url\": \"http://gph.is/1c3ydAW\",\n" +
                    "      \"bitly_url\": \"http://gph.is/1c3ydAW\",\n" +
                    "      \"embed_url\": \"https://giphy.com/embed/3XQatO8S5YsVi\",\n" +
                    "      \"username\": \"foxadhd\",\n" +
                    "      \"source\": \"www.foxadhd.com\",\n" +
                    "      \"title\": \"Fox Animation Art GIF by Animation Domination High-Def\",\n" +
                    "      \"rating\": \"pg\",\n" +
                    "      \"content_url\": \"\",\n" +
                    "      \"source_tld\": \"\",\n" +
                    "      \"source_post_url\": \"www.foxadhd.com\",\n" +
                    "      \"is_sticker\": 0,\n" +
                    "      \"import_datetime\": \"2012-11-20 17:00:49\",\n" +
                    "      \"trending_datetime\": \"2020-11-17 11:00:12\",\n" +
                    "      \"images\": {\n" +
                    "        \"original\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\",\n" +
                    "          \"mp4_size\": \"398850\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.mp4\",\n" +
                    "          \"webp_size\": \"453614\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.webp\",\n" +
                    "          \"frames\": \"51\",\n" +
                    "          \"hash\": \"dd2398c34236a12a08e100a7290009d8\"\n" +
                    "        },\n" +
                    "        \"downsized\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_large\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_medium\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_small\": {\n" +
                    "          \"height\": \"352\",\n" +
                    "          \"width\": \"352\",\n" +
                    "          \"mp4_size\": \"102602\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-downsized-small.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-downsized-small.mp4\"\n" +
                    "        },\n" +
                    "        \"downsized_still\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_height\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"229825\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.gif\",\n" +
                    "          \"mp4_size\": \"92497\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.mp4\",\n" +
                    "          \"webp_size\": \"151922\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_downsampled\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"30939\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_d.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_d.gif\",\n" +
                    "          \"webp_size\": \"28348\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_d.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_d.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_small\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"98591\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.gif\",\n" +
                    "          \"mp4_size\": \"30780\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.mp4\",\n" +
                    "          \"webp_size\": \"59730\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_small_still\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"3161\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_height_still\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"6464\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_width\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"229825\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.gif\",\n" +
                    "          \"mp4_size\": \"92497\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.mp4\",\n" +
                    "          \"webp_size\": \"151922\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_downsampled\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"30939\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_d.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_d.gif\",\n" +
                    "          \"webp_size\": \"28348\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_d.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_d.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_small\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"98591\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.gif\",\n" +
                    "          \"mp4_size\": \"30780\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.mp4\",\n" +
                    "          \"webp_size\": \"59730\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_small_still\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"3161\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_width_still\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"6464\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_s.gif\"\n" +
                    "        },\n" +
                    "        \"looping\": {\n" +
                    "          \"mp4_size\": \"1870867\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-loop.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-loop.mp4\"\n" +
                    "        },\n" +
                    "        \"original_still\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"10787\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy_s.gif\"\n" +
                    "        },\n" +
                    "        \"original_mp4\": {\n" +
                    "          \"height\": \"480\",\n" +
                    "          \"width\": \"480\",\n" +
                    "          \"mp4_size\": \"398850\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.mp4\"\n" +
                    "        },\n" +
                    "        \"preview\": {\n" +
                    "          \"height\": \"276\",\n" +
                    "          \"width\": \"276\",\n" +
                    "          \"mp4_size\": \"35273\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.mp4\"\n" +
                    "        },\n" +
                    "        \"preview_gif\": {\n" +
                    "          \"height\": \"125\",\n" +
                    "          \"width\": \"125\",\n" +
                    "          \"size\": \"49925\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.gif\"\n" +
                    "        },\n" +
                    "        \"preview_webp\": {\n" +
                    "          \"height\": \"216\",\n" +
                    "          \"width\": \"216\",\n" +
                    "          \"size\": \"49478\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.webp\"\n" +
                    "        },\n" +
                    "        \"480w_still\": {\n" +
                    "          \"height\": \"480\",\n" +
                    "          \"width\": \"480\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/480w_s.jpg?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=480w_s.jpg\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"user\": {\n" +
                    "        \"avatar_url\": \"https://media3.giphy.com/avatars/foxadhd/FIpJVXV5aO80.gif\",\n" +
                    "        \"banner_image\": \"https://media3.giphy.com/avatars/foxadhd/GifrKcAjH8gg.jpg\",\n" +
                    "        \"banner_url\": \"https://media3.giphy.com/avatars/foxadhd/GifrKcAjH8gg.jpg\",\n" +
                    "        \"profile_url\": \"https://giphy.com/foxadhd/\",\n" +
                    "        \"username\": \"foxadhd\",\n" +
                    "        \"display_name\": \"Animation Domination High-Def\",\n" +
                    "        \"description\": \"Saturdays @11p/10c on Fox\",\n" +
                    "        \"instagram_url\": \"\",\n" +
                    "        \"website_url\": \"http://www.foxadhd.com\",\n" +
                    "        \"is_verified\": true\n" +
                    "      },\n" +
                    "      \"analytics_response_payload\": \"e=Z2lmX2lkPTNYUWF0TzhTNVlzVmkmZXZlbnRfdHlwZT1HSUZfVFJFTkRJTkcmY2lkPTAwMTg4MWYyZzdieDNjN3Q0Z3V6Mm5ndmlvbWVlY3BnM2RjOWcxYzIybThtdDhlOA\",\n" +
                    "      \"analytics\": {\n" +
                    "        \"onload\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=SEEN\"\n" +
                    "        },\n" +
                    "        \"onclick\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=CLICK\"\n" +
                    "        },\n" +
                    "        \"onsent\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=SENT\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}", HttpURLConnection.HTTP_OK
        )
        runBlocking {
            val searchListMocked = searchRepo.searchWithPagination("query", 1)
            assertNotNull(searchListMocked)
            assertEquals(searchListMocked.isNullOrEmpty(), false)
            val data = searchListMocked[0]
            assertEquals(data.id, "3XQatO8S5YsVi")
            assertEquals(
                data.url,
                "https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif"
            )

        }
    }

    @Test(expected = HttpException::class)
    fun search_result_error() {
        mockHttpResponse(
            "{\n" +
                    "  \"data\": [\n" +
                    "    {\n" +
                    "      \"type\": \"gif\",\n" +
                    "      \"id\": \"3XQatO8S5YsVi\",\n" +
                    "      \"url\": \"https://giphy.com/gifs/foxadhd-thanksgiving-turkey-art-3XQatO8S5YsVi\",\n" +
                    "      \"slug\": \"foxadhd-thanksgiving-turkey-art-3XQatO8S5YsVi\",\n" +
                    "      \"bitly_gif_url\": \"http://gph.is/1c3ydAW\",\n" +
                    "      \"bitly_url\": \"http://gph.is/1c3ydAW\",\n" +
                    "      \"embed_url\": \"https://giphy.com/embed/3XQatO8S5YsVi\",\n" +
                    "      \"username\": \"foxadhd\",\n" +
                    "      \"source\": \"www.foxadhd.com\",\n" +
                    "      \"title\": \"Fox Animation Art GIF by Animation Domination High-Def\",\n" +
                    "      \"rating\": \"pg\",\n" +
                    "      \"content_url\": \"\",\n" +
                    "      \"source_tld\": \"\",\n" +
                    "      \"source_post_url\": \"www.foxadhd.com\",\n" +
                    "      \"is_sticker\": 0,\n" +
                    "      \"import_datetime\": \"2012-11-20 17:00:49\",\n" +
                    "      \"trending_datetime\": \"2020-11-17 11:00:12\",\n" +
                    "      \"images\": {\n" +
                    "        \"original\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\",\n" +
                    "          \"mp4_size\": \"398850\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.mp4\",\n" +
                    "          \"webp_size\": \"453614\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.webp\",\n" +
                    "          \"frames\": \"51\",\n" +
                    "          \"hash\": \"dd2398c34236a12a08e100a7290009d8\"\n" +
                    "        },\n" +
                    "        \"downsized\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_large\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_medium\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.gif\"\n" +
                    "        },\n" +
                    "        \"downsized_small\": {\n" +
                    "          \"height\": \"352\",\n" +
                    "          \"width\": \"352\",\n" +
                    "          \"mp4_size\": \"102602\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-downsized-small.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-downsized-small.mp4\"\n" +
                    "        },\n" +
                    "        \"downsized_still\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_height\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"229825\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.gif\",\n" +
                    "          \"mp4_size\": \"92497\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.mp4\",\n" +
                    "          \"webp_size\": \"151922\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_downsampled\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"30939\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_d.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_d.gif\",\n" +
                    "          \"webp_size\": \"28348\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_d.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_d.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_small\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"98591\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.gif\",\n" +
                    "          \"mp4_size\": \"30780\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.mp4\",\n" +
                    "          \"webp_size\": \"59730\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_height_small_still\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"3161\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_height_still\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"6464\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_width\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"229825\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.gif\",\n" +
                    "          \"mp4_size\": \"92497\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.mp4\",\n" +
                    "          \"webp_size\": \"151922\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_downsampled\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"30939\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_d.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_d.gif\",\n" +
                    "          \"webp_size\": \"28348\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_d.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_d.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_small\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"98591\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.gif\",\n" +
                    "          \"mp4_size\": \"30780\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.mp4\",\n" +
                    "          \"webp_size\": \"59730\",\n" +
                    "          \"webp\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w.webp\"\n" +
                    "        },\n" +
                    "        \"fixed_width_small_still\": {\n" +
                    "          \"height\": \"100\",\n" +
                    "          \"width\": \"100\",\n" +
                    "          \"size\": \"3161\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/100w_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=100w_s.gif\"\n" +
                    "        },\n" +
                    "        \"fixed_width_still\": {\n" +
                    "          \"height\": \"200\",\n" +
                    "          \"width\": \"200\",\n" +
                    "          \"size\": \"6464\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/200w_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=200w_s.gif\"\n" +
                    "        },\n" +
                    "        \"looping\": {\n" +
                    "          \"mp4_size\": \"1870867\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-loop.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-loop.mp4\"\n" +
                    "        },\n" +
                    "        \"original_still\": {\n" +
                    "          \"height\": \"500\",\n" +
                    "          \"width\": \"500\",\n" +
                    "          \"size\": \"10787\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy_s.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy_s.gif\"\n" +
                    "        },\n" +
                    "        \"original_mp4\": {\n" +
                    "          \"height\": \"480\",\n" +
                    "          \"width\": \"480\",\n" +
                    "          \"mp4_size\": \"398850\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy.mp4\"\n" +
                    "        },\n" +
                    "        \"preview\": {\n" +
                    "          \"height\": \"276\",\n" +
                    "          \"width\": \"276\",\n" +
                    "          \"mp4_size\": \"35273\",\n" +
                    "          \"mp4\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.mp4?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.mp4\"\n" +
                    "        },\n" +
                    "        \"preview_gif\": {\n" +
                    "          \"height\": \"125\",\n" +
                    "          \"width\": \"125\",\n" +
                    "          \"size\": \"49925\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.gif?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.gif\"\n" +
                    "        },\n" +
                    "        \"preview_webp\": {\n" +
                    "          \"height\": \"216\",\n" +
                    "          \"width\": \"216\",\n" +
                    "          \"size\": \"49478\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/giphy-preview.webp?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=giphy-preview.webp\"\n" +
                    "        },\n" +
                    "        \"480w_still\": {\n" +
                    "          \"height\": \"480\",\n" +
                    "          \"width\": \"480\",\n" +
                    "          \"size\": \"383303\",\n" +
                    "          \"url\": \"https://media4.giphy.com/media/3XQatO8S5YsVi/480w_s.jpg?cid=001881f2g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&rid=480w_s.jpg\"\n" +
                    "        }\n" +
                    "      },\n" +
                    "      \"user\": {\n" +
                    "        \"avatar_url\": \"https://media3.giphy.com/avatars/foxadhd/FIpJVXV5aO80.gif\",\n" +
                    "        \"banner_image\": \"https://media3.giphy.com/avatars/foxadhd/GifrKcAjH8gg.jpg\",\n" +
                    "        \"banner_url\": \"https://media3.giphy.com/avatars/foxadhd/GifrKcAjH8gg.jpg\",\n" +
                    "        \"profile_url\": \"https://giphy.com/foxadhd/\",\n" +
                    "        \"username\": \"foxadhd\",\n" +
                    "        \"display_name\": \"Animation Domination High-Def\",\n" +
                    "        \"description\": \"Saturdays @11p/10c on Fox\",\n" +
                    "        \"instagram_url\": \"\",\n" +
                    "        \"website_url\": \"http://www.foxadhd.com\",\n" +
                    "        \"is_verified\": true\n" +
                    "      },\n" +
                    "      \"analytics_response_payload\": \"e=Z2lmX2lkPTNYUWF0TzhTNVlzVmkmZXZlbnRfdHlwZT1HSUZfVFJFTkRJTkcmY2lkPTAwMTg4MWYyZzdieDNjN3Q0Z3V6Mm5ndmlvbWVlY3BnM2RjOWcxYzIybThtdDhlOA\",\n" +
                    "      \"analytics\": {\n" +
                    "        \"onload\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=SEEN\"\n" +
                    "        },\n" +
                    "        \"onclick\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=CLICK\"\n" +
                    "        },\n" +
                    "        \"onsent\": {\n" +
                    "          \"url\": \"https://giphy-analytics.giphy.com/simple_analytics?response_id=g7bx3c7t4guz2ngviomeecpg3dc9g1c22m8mt8e8&event_type=GIF_TRENDING&gif_id=3XQatO8S5YsVi&action_type=SENT\"\n" +
                    "        }\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}", HttpURLConnection.HTTP_BAD_REQUEST
        )
        runBlocking {
            val searchListMocked = searchRepo.searchWithPagination("query", 1)
            assertEquals(searchListMocked.isNullOrEmpty(), true)
        }
    }


}