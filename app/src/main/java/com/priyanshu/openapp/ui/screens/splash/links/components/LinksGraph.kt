package com.priyanshu.openapp.ui.screens.splash.links.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.AccessibilityConfig
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.priyanshu.openapp.ui.theme.blue
import com.priyanshu.openapp.ui.theme.lightGray
import com.priyanshu.openapp.ui.theme.primaryColor
import kotlin.random.Random

@Composable
fun LinksGraph(
    graphData: List<GraphData>
) {
    val steps = graphData.size
    val yPointsData = ArrayList<Float>()
    val xPointsData = ArrayList<Float>()
    graphData.map { item ->
        yPointsData.add(item.totalClicks.toFloat())
        xPointsData.add(item.monthNumber.toFloat())
    }
    yPointsData.sort()
    val pointsData = graphData.map { intraDayInfo ->
        Point(
            intraDayInfo.monthNumber.toFloat(),
            intraDayInfo.totalClicks.toFloat(),
        )
    }

    val yAxisData = AxisData.Builder().steps(steps - 1).backgroundColor(Color.Transparent)
        .labelAndAxisLinePadding(20.dp).labelData { i ->
            yPointsData[i].toString()
        }.axisLineColor(blue).axisLabelColor(blue).axisLabelFontSize(10.sp).build()

    val xAxisData = AxisData.Builder().axisStepSize(100.dp).backgroundColor(Color.Transparent)
        .steps(pointsData.size - 1).labelData { i ->
            graphData[i].month
        }.labelAndAxisLinePadding(15.dp)
        .axisLineColor(blue).axisLabelColor(blue).axisLabelFontSize(10.sp).build()


    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        lineType = LineType.SmoothCurve(isDotted = false),
                        color = blue,
                        width = 4f
                    ),
                    intersectionPoint = IntersectionPoint(color = blue, radius = 2.dp),
                    SelectionHighlightPoint(color = blue, radius = 2.dp),
                    ShadowUnderLine(
                        alpha = 0.5f, brush = Brush.verticalGradient(
                            colors = listOf(
                                blue, Color.Transparent
                            )
                        )
                    ),
                    SelectionHighlightPopUp(
                        backgroundColor = Color.Transparent, labelColor = primaryColor
                    )
                )
            )
        ),
        backgroundColor = Color.Transparent,
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(Color.Transparent),
        accessibilityConfig = AccessibilityConfig(
            dividerColor = Color.Transparent
        ),

        )

    Spacer(modifier = Modifier.height(16.dp))

    LineChart(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(16.dp)),
        lineChartData = lineChartData
    )


}


val graphData = listOf(
    GraphData(totalClicks = 0, month = "", 0),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Jan", monthNumber = 1),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Feb", monthNumber = 2),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Mar", monthNumber = 3),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Apr", monthNumber = 4),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "May", monthNumber = 5),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Jun", monthNumber = 6),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Jul", monthNumber = 7),
    GraphData(totalClicks = Random.nextInt(0, 100), month = "Aug", monthNumber = 8),
)

data class GraphData(
    val totalClicks: Int,
    val month: String,
    val monthNumber: Int
)

val monthList = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
)