package com.rng.nycschools.presentation.school_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rng.nycschools.presentation.navigation.Screen
import com.rng.nycschools.presentation.viewmodel.SchoolListingViewModel

/**
 * School List Home Screen
 */
@Composable
fun SchoolListHomeScreen(
    viewModel: SchoolListingViewModel = hiltViewModel(),
    navController: NavController
) {
    val swipeRefreshState =
        rememberSwipeRefreshState(isRefreshing = viewModel.stateSchoolList.isRefreshing)
    val state = viewModel.stateSchoolList
    Scaffold(
        topBar = {
            // Tool Bar
            TopAppBar(title = { Text(text = "NYC Schools") })
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = state.searchQuery, onValueChange = {
                    viewModel.onEvent(SchoolListingEvents.OnSearchQueryChange(it))
                }, modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), placeholder = {
                    Text(text = "Search... zipcode or city")
                }, maxLines = 1,
                singleLine = true
            )

            // Swipe to refresh to refresh the list on swipe
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.onEvent(SchoolListingEvents.Refresh) }) {

                //Loading the School List
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.schools.size) { i ->
                        val schoolItem = state.schools[i]
                        SchoolListItem(schoolList = schoolItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(
                                        Screen.SchoolScoresScreen.withArgs(
                                            schoolItem.schoolCode.toString()
                                        )
                                    )
                                }
                                .padding(16.dp))

                        if (i < state.schools.size) {
                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }

                    }
                }
            }

        }

    }
}