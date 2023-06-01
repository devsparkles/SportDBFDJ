package com.kodesparkle.sportdbfdj.usecases

import com.kodesparkle.sportdbfdj.domain.model.TeamItem
import com.kodesparkle.sportdbfdj.domain.model.TeamSearchResultItem
import com.kodesparkle.sportdbfdj.domain.repository.RemoteTeamRepository
import com.kodesparkle.sportdbfdj.domain.usecases.SearchTeamByLeagueUseCase
import com.kodesparkle.sportdbfdj.utils.resource.Resource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test


class SearchTeamByLeagueUseCaseTest {

    @Test
    fun `Verify SearchTeamByLeagueUseCaseTest when the repository have an error return an empty list`() = runTest {
        // Given
        val leagueName = "English Premiere League"
        val ressourceError =  Resource.Error<TeamSearchResultItem>(Exception("the network is down"))
        val mock = mockk<RemoteTeamRepository>()
        coEvery { mock.searchTeamByLeagueName(leagueName) }.returns(ressourceError)

        // When
        val sut = SearchTeamByLeagueUseCase(mock)
        val value = sut.invoke(leagueName)

        // Then
        assertTrue(value.size == 0)
    }

    @Test
    fun `Verify SearchTeamByLeagueUseCaseTest when the repository have some teams that they show up at the end of the use case`() =
        runTest {
            // Given
            val leagueName = "English Premiere League"
            val result = Resource.of { TeamSearchResultItem(emptyList()) }
            val mock = mockk<RemoteTeamRepository>()
            coEvery { mock.searchTeamByLeagueName(leagueName) }.returns(result)

            // When
            val sut = SearchTeamByLeagueUseCase(mock)
            val value = sut.invoke(leagueName)

            // Then
            assertTrue(value.size == 0)
        }


    @Test
    fun `Verify SearchLeaguesUseCase filter  properly by query`() = runTest {
        // Given
        val query = "France"
        val result = Resource.of { TeamSearchResultItem(TEAM_ITEM_SAMPLE_LIST) }
        val mock = mockk<RemoteTeamRepository>()
        coEvery { mock.searchTeamByLeagueName(query) }.returns(result)

        // When
        val sut = SearchTeamByLeagueUseCase(mock)
        val value = sut.invoke(query)

        // Then
        assertTrue(value.size == 3)
        assertTrue(value[0].name == "Paris Saint-Germain")
    }

    companion object {

        // list of sample data of LeagueItem
        val TEAM_ITEM_SAMPLE_LIST = mutableListOf<TeamItem>(
            TeamItem(
                id = "133604",
                name = "Paris Saint-Germain",
                imageUrl = "http://myimage.jpg",
            ),
            TeamItem(
                id = "133605",
                name = "Bordeaux",
                imageUrl = "http://myimage2.jpg",
            ),
            TeamItem(
                id = "133606",
                name = "Lyon",
                imageUrl = "http://myimage3.jpg",
            ),

            )

    }
}
