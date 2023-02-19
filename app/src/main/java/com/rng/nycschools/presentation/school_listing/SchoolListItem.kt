package com.rng.nycschools.presentation.school_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rng.nycschools.data.model.SchoolResponse


@Composable
fun SchoolListItem(schoolList: SchoolResponse, modifier: Modifier = Modifier) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = schoolList.schoolName.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = schoolList.schoolCode.toString(), fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.onBackground
                )

            }

        }

    }

}