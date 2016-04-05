package me.sargunvohra.android.diningcourts.data.misc

import com.squareup.moshi.Json

data class TimeRange(
        @Json(name = "StartTime")
        val startTime: String,
        @Json(name = "EndTime")
        val endTime: String
)