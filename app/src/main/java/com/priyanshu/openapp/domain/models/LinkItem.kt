package com.priyanshu.openapp.domain.models

data class LinkItem(
    val image: String,
    val title: String,
    val date: String,
    val totalClicks: String,
    val link: String
)

val linkItems = listOf<LinkItem>(
    LinkItem(
        image = "https://images.unsplash.com/photo-1606787366850-de6330128bfc",
        title = "Exploring the Mountains",
        date = "2024-07-31",
        totalClicks = "123",
        link = "https://example.com/mountains"
    ),
    LinkItem(
        image = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
        title = "City Skyline",
        date = "2024-07-30",
        totalClicks = "456",
        link = "https://example.com/city"
    ),
    LinkItem(
        image = "https://images.unsplash.com/photo-1495592528446-0b4cda1c00a8",
        title = "Serene Beach",
        date = "2024-07-29",
        totalClicks = "789",
        link = "https://example.com/beach"
    ),
    LinkItem(
        image = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
        title = "Golden Gate Bridge",
        date = "2024-07-28",
        totalClicks = "1011",
        link = "https://example.com/bridge"
    ),
    LinkItem(
        image = "https://images.unsplash.com/photo-1549144510-90c38b6ea7a1",
        title = "Forest Path",
        date = "2024-07-27",
        totalClicks = "1213",
        link = "https://example.com/forest"
    )
)
