package com.priyanshu.openapp.ui.screens.links.components

sealed class LinkChipItems(val id: String) {
    data object TopLinks : LinkChipItems(id = "top_links")
    data object RecentLinks : LinkChipItems(id = "recent_links")
}