package com.priyanshu.openapp.domain.models

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.priyanshu.openapp.R
import com.priyanshu.openapp.ui.theme.blue
import com.priyanshu.openapp.ui.theme.purple
import com.priyanshu.openapp.ui.theme.softRed

data class OverviewItem(
    @DrawableRes
    val icon: Int,
    val iconTint: Color,
    val title: String,
    val value: String
)

val overviewItems = listOf<OverviewItem>(
    OverviewItem(icon = R.drawable.ic_click, iconTint = purple, title = "Today's clicks", value = "123"),
    OverviewItem(icon = R.drawable.ic_location, iconTint = blue, "Top Location", "Ahamedabad"),
    OverviewItem(icon = R.drawable.ic_web, iconTint = softRed, "Top Sources", "Instagram")
)
