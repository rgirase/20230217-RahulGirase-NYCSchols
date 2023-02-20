package com.rng.nycschools.presentation.school_Info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rng.nycschools.R
import com.rng.nycschools.presentation.viewmodel.SchoolDetailViewModel

@Composable
fun SchoolScoresScreen(
    schoolId: String,
    viewModel: SchoolDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.stateSchoolSatScore
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.nyc_schools)) })
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
        if (state.schoolDetails != null || state.schoolItem != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalAlignment = CenterHorizontally,
            ) {
                val schoolItemSatScore = state.schoolDetails
                val schoolItem = state.schoolItem
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(CenterHorizontally)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = schoolItem?.schoolName.toString(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = stringResource(R.string.school_details),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.email))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = schoolItem?.schoolEmail.toString())
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.address))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = schoolItem?.location.toString())
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.phone_number))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = schoolItem?.phoneNumber.toString())
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text(text = stringResource(R.string.website))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = schoolItem?.schoolWebsite.toString())
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.sat_score_requirements),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onBackground
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .align(CenterHorizontally)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.reading),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = schoolItemSatScore?.readingAvgScore.toString())
                    }
                    Divider(
                        modifier = Modifier.width(2.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.math),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = schoolItemSatScore?.mathAvgScore.toString())
                    }

                    Divider(
                        modifier = Modifier.width(2.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.writing),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = schoolItemSatScore?.writingAvgScore.toString())
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        } else {
            if (state.errorMessage?.isNotBlank() == true) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_error_outline_24),
                        contentDescription = stringResource(R.string.error_logo),
                        alignment = Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = state.errorMessage.toString()
                    )
                }
            }

        }
    }


}