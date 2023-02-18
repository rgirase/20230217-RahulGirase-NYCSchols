package com.rng.nycschools.presentation.school_listing

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@Composable
fun SchoolListsScreen(viewModel: SchoolListingViewModel = hiltViewModel()) {
    val state = viewModel.state
    val navController = rememberNavController()
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.schools.size) { i ->
                val schoolItem = state.schools[i]
                SchoolListItem(schoolList = schoolItem,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                        .padding(16.dp))

                if (i < state.schools.size) {
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }

            }
        }

    }
}