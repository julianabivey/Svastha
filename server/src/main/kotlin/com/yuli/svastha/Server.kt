package com.yuli.svastha

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import java.time.Instant
import java.time.temporal.ChronoUnit

@Serializable data class Sample(val ts: String, val value: Double)
@Serializable data class Series(val metric: String, val samples: List<Sample>)
@Serializable data class Summary(
  val date: String,
  val heartRate: Map<String, Double>,
  val respiratoryRate: Map<String, Double>
)

fun main() { embeddedServer(Netty, port = 8080) { module() }.start(wait = true) }

fun Application.module() {
  install(ContentNegotiation) { json() }
  routing {
    get("/biometrics/hr/timeseries") { call.respond(genSeries("heartRateBpm")) }
    get("/biometrics/rr/timeseries") { call.respond(genSeries("respiratoryRateBpm")) }
    get("/biometrics/summary") { call.respond(genSummary()) }
  }
}

private fun genSeries(metric: String): Series {
  val now = Instant.now().truncatedTo(ChronoUnit.MINUTES)
  val points = (0 until 24*6).map { i ->
    val ts = now.minus(i*10L, ChronoUnit.MINUTES).toString()
    val base = if (metric == "heartRateBpm") 68.0 else 13.5
    val jitter = listOf(-3,-2,-1,0,1,2,3).random()
    Sample(ts, (base + jitter).coerceAtLeast(1.0))
  }.reversed()
  return Series(metric, points)
}

private fun genSummary(): Summary {
  return Summary(
    date = Instant.now().toString().substring(0,10),
    heartRate = mapOf("min" to 52.0, "avg" to 68.0, "max" to 162.0, "resting" to 56.0),
    respiratoryRate = mapOf("min" to 10.0, "avg" to 13.5, "max" to 18.0)
  )
}
