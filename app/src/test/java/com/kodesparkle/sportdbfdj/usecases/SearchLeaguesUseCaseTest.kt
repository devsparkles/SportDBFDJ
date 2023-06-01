package com.kodesparkle.sportdbfdj.usecases

import com.kodesparkle.sportdbfdj.domain.model.LeagueItem
import com.kodesparkle.sportdbfdj.domain.model.LeagueResultItem
import com.kodesparkle.sportdbfdj.domain.repository.RemoteLeagueRepository
import com.kodesparkle.sportdbfdj.domain.usecases.SearchLeaguesUseCase
import com.kodesparkle.sportdbfdj.utils.resource.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchLeaguesUseCaseTest {

    @Test
    fun `Verify SearchLeaguesUseCase filter properly by query`() = runTest {
        // Given
        val query = "English"
        val result = Resource.of { LeagueResultItem(LEAGUE_ITEM_SAMPLE_LIST) }
        val mock = mockk<RemoteLeagueRepository>()
        coEvery { mock.getLeagues() }.returns(result)

        // When
        val sut = SearchLeaguesUseCase(mock)
        val value = sut.invoke(query)

        // Then
        assertTrue(value.size == 2)
        assertTrue(value[0].strLeague.contains(query, ignoreCase = true))
        assertTrue(value[1].strLeague.contains(query, ignoreCase = true))
    }


    @Test
    fun `Verify SearchLeaguesUseCase filter  properly by query`() = runTest {
        // Given
        val query = "France"
        val result = Resource.of { LeagueResultItem(LEAGUE_ITEM_SAMPLE_LIST) }
        val mock = mockk<RemoteLeagueRepository>()
        coEvery { mock.getLeagues() }.returns(result)

        // When
        val sut = SearchLeaguesUseCase(mock)
        val value = sut.invoke(query)

        // Then
        assertTrue(value.size == 1)
        assertTrue(value[0].strLeague.contains(query, ignoreCase = true))
    }

    companion object {

        // list of sample data of LeagueItem
        val LEAGUE_ITEM_SAMPLE_LIST = mutableListOf<LeagueItem>(
            LeagueItem(
                idLeague = "4328",
                strLeague = "English Premier League",
                strSport = "Soccer",
                strLeagueAlternate = "Premier League"
            ),
            LeagueItem(
                idLeague = "4329",
                strLeague = "France Ligue 1",
                strSport = "Soccer",
                strLeagueAlternate = "Premier League"
            ),
            LeagueItem(
                idLeague = "4330",
                strLeague = "English Second League",
                strSport = "Soccer",
                strLeagueAlternate = "Premier League"
            ),
        )

    }
}
