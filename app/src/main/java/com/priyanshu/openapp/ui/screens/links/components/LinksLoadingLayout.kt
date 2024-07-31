package com.priyanshu.openapp.ui.screens.links.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.priyanshu.openapp.ui.components.shimmerLoadingAnimation

@Composable
fun LinksLoadingLayout() {

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {
            Column {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(12.dp)
                        .shimmerLoadingAnimation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .height(18.dp)
                        .shimmerLoadingAnimation()
                )

            }
            Spacer(modifier = Modifier.height(24.dp))
        }


        item {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(200.dp)
                    .shimmerLoadingAnimation()
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            LazyRow {
                items(3) {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .size(150.dp)
                            .shimmerLoadingAnimation()
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }


        item {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(56.dp)
                    .shimmerLoadingAnimation()
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        items(5) {
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .height(120.dp)
                    .shimmerLoadingAnimation()
            )

        }


    }


}