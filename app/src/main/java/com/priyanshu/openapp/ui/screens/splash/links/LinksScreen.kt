package com.priyanshu.openapp.ui.screens.splash.links

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.priyanshu.openapp.R
import com.priyanshu.openapp.ui.screens.splash.links.components.LinkChipItems
import com.priyanshu.openapp.ui.screens.splash.links.components.LinksGraph
import com.priyanshu.openapp.ui.screens.splash.links.components.LinksLoadingLayout
import com.priyanshu.openapp.ui.screens.splash.links.components.graphData
import com.priyanshu.openapp.ui.screens.splash.links.viewmodel.LinksViewModel
import com.priyanshu.openapp.ui.theme.blue
import com.priyanshu.openapp.ui.theme.gray
import com.priyanshu.openapp.ui.theme.lightGray
import com.priyanshu.openapp.ui.theme.primaryColor
import com.priyanshu.openapp.ui.theme.white
import com.priyanshu.openapp.utils.AppUtils
import com.priyanshu.openapp.utils.isScrollingUp

@Composable
fun LinksScreen(
    innerPaddingValues: PaddingValues,
    viewModel: LinksViewModel = hiltViewModel()
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(blue)
    val lazyColumnState = rememberLazyListState()
    val topBarVisibility = lazyColumnState.isScrollingUp()
    var chipItemSelected by remember {
        mutableStateOf(LinkChipItems.TopLinks.id)
    }
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val appResult by viewModel.appResultState.collectAsState()
    val topLinks = appResult?.data?.top_links ?: emptyList()
    val recentLinks = appResult?.data?.recent_links ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(blue)
            .padding(top = if (topBarVisibility) 16.dp else 0.dp)
    ) {

        AnimatedVisibility(
            visible = topBarVisibility,
            exit = shrinkVertically(),
            enter = expandVertically()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dashboard",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = white,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp
                    )
                )
                Icon(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White.copy(alpha = 0.2f))
                        .padding(all = 8.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings icon",
                    tint = white
                )
            }

        }

       if (topBarVisibility)
           Spacer(modifier = Modifier.height(16.dp))

        if (loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = innerPaddingValues.calculateBottomPadding(),
                        top = 24.dp
                    ),
            ) {
                LinksLoadingLayout()
            }

        } else if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = innerPaddingValues.calculateBottomPadding(),
                        top = 24.dp
                    ), contentAlignment = Alignment.Center
            ) {

                Text(
                    text = error.toString(),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = lightGray,
                        textAlign = TextAlign.Center
                    )
                )

            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        if (topBarVisibility) RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp
                        ) else RectangleShape
                    )
                    .background(MaterialTheme.colorScheme.background)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = innerPaddingValues.calculateBottomPadding(),
                        top = if (topBarVisibility) 24.dp else 0.dp
                    ),
                state = lazyColumnState
            ) {

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = AppUtils.getGreetText(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Priyanshu Verma",
                            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 20.sp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp), contentAlignment = Alignment.Center
                    ) {
                        LinksGraph(graphData = graphData)
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(viewModel.getOverviewItems()) {
                            ItemOverview(
                                icon = it.icon,
                                iconTint = it.iconTint,
                                title = it.title,
                                value = it.value
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = lightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(42.dp),
                            painter = painterResource(id = R.drawable.ic_graph),
                            contentDescription = "Graph Icon",
                            tint = primaryColor
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "View Analytics", style = MaterialTheme.typography.bodyLarge)

                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Row {
                            LinkCipItem(chipTitle = "Top Links", onItemClick = {

                                chipItemSelected = LinkChipItems.TopLinks.id

                            }, isItemSelected = chipItemSelected == LinkChipItems.TopLinks.id)

                            Spacer(modifier = Modifier.width(16.dp))

                            LinkCipItem(chipTitle = "Recent Links", onItemClick = {

                                chipItemSelected = LinkChipItems.RecentLinks.id

                            }, isItemSelected = chipItemSelected == LinkChipItems.RecentLinks.id)
                        }

                        Icon(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(
                                    width = 1.dp,
                                    color = lightGray,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(all = 8.dp),
                            imageVector = Icons.Default.Search,
                            contentDescription = "Settings icon",
                            tint = lightGray
                        )

                    }
                }

                if (chipItemSelected == LinkChipItems.TopLinks.id) {

                    items(topLinks) { item ->
                        ItemLink(
                            image = item.original_image,
                            title = item.title,
                            date = AppUtils.formatDateString(item.created_at),
                            totalClicks = item.total_clicks.toString(),
                            link = item.smart_link
                        )
                    }
                } else {

                    items(recentLinks) { item ->
                        ItemLink(
                            image = item.original_image,
                            title = item.title,
                            date = AppUtils.formatDateString(item.created_at),
                            totalClicks = item.total_clicks.toString(),
                            link = item.smart_link
                        )
                    }

                }



                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 1.dp,
                                color = lightGray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.drawable.ic_link),
                            contentDescription = "Graph Icon",
                            tint = primaryColor
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "View all Links", style = MaterialTheme.typography.bodyLarge)

                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    ItemFooterOption(
                        icon = R.drawable.whatsapp,
                        iconTint = Color.Green,
                        title = "Talk to us"
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    ItemFooterOption(
                        icon = R.drawable.question,
                        iconTint = blue,
                        title = "Frequently Asked Questions"
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }


            }

        }

    }


}

@Composable
fun ItemOverview(
    @DrawableRes
    icon: Int,
    iconTint: Color,
    title: String,
    value: String
) {

    Column(
        modifier = Modifier
            .padding(end = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .size(150.dp)
            .background(gray)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = iconTint.copy(alpha = 0.2f))
                .padding(6.dp),
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = iconTint
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )

    }

}

@Composable
fun LinkCipItem(
    chipTitle: String,
    isItemSelected: Boolean = false,
    onItemClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .background(
                if (isItemSelected) blue else MaterialTheme.colorScheme.background,
                shape = CircleShape
            )
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clickable {
                onItemClick()
            }
            .clip(CircleShape),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = chipTitle,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.sp,
                color = if (isItemSelected) white else lightGray,
                fontWeight = FontWeight.Medium
            )
        )

    }


}

@Composable
fun ItemLink(
    image: String,
    title: String,
    date: String,
    totalClicks: String,
    link: String
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(gray)
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .size(50.dp),
                        model = image,
                        contentDescription = title,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            text = title,
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = date, style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = totalClicks,
                        style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.End)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Clicks", style = MaterialTheme.typography.bodyMedium)
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                    .background(
                        blue.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    )
                    .padding(all = 16.dp)
                    .clickable {

                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    text = link,
                    style = MaterialTheme.typography.bodyLarge.copy(color = blue),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_copy),
                    contentDescription = "Copy to clipboard icon",
                    tint = blue
                )

            }

        }

    }

}

@Composable
fun ItemFooterOption(
    @DrawableRes
    icon: Int,
    iconTint: Color,
    title: String
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(iconTint.copy(alpha = 0.3f), shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = iconTint, shape = RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = icon),
            contentDescription = title
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, style = MaterialTheme.typography.bodyLarge)
    }

}