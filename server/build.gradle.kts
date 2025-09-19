plugins {
  kotlin("jvm") ; application
  kotlin("plugin.serialization")
}

kotlin {
  jvmToolchain(17)
}
//repositories { mavenCentral() }
dependencies {
  implementation("io.ktor:ktor-server-netty:2.3.12")
  implementation("io.ktor:ktor-server-core:2.3.12")
  implementation("io.ktor:ktor-server-content-negotiation:2.3.12")
  implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
  implementation("ch.qos.logback:logback-classic:1.5.6")
}
application { mainClass.set("com.yuli.svastha.ServerKt") }
