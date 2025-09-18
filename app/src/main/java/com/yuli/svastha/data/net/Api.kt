package com.yuli.svastha.data.net
import com.yuli.svastha.data.net.dto.*
import retrofit2.http.GET

interface SvasthaApi {
  @GET("/biometrics/hr/timeseries") suspend fun heartRateSeries(): SeriesDto
  @GET("/biometrics/rr/timeseries") suspend fun respiratoryRateSeries(): SeriesDto
  @GET("/biometrics/summary") suspend fun summary(): SummaryDto
}
