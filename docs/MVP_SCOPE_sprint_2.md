# Svastha — MVP Scope (Sprint 2)

## Overview
Svastha is an Android app that visualizes biometric wellness data (Heart Rate and Respiratory/Breathing Rate) with intuitive charts and basic insights. This MVP establishes a solid technical and UX foundation for future Ayurvedic personalization.

## Core Goal
Deliver a functional dashboard that fetches mock biometric data, renders two charts (HR & RR), and visualizes healthy thresholds so users can quickly spot out-of-range values.

## MVP Features
1) **Biometric Dashboard (DONE)**
- Shows today’s HR/RR averages.
- Loading & error states.
- Compose UI with Vico charts.
- Data flow: Repository → ViewModel (StateFlow) → UI.

2) **Threshold Visualization (IN PROGRESS)**
- Default healthy ranges for HR & RR shown as lines/bands on charts.
- Out-of-range points visually emphasized (e.g., color or markers).
- Static defaults (no user personalization yet).

3) **Data Refresh (DONE)**
- User-initiated refresh triggers repository fetch.
- Concurrent loads with coroutines (`async/await`), single atomic UI update via `runCatching`.

## Non-Goals (Deferred)
- Customizable thresholds / profiles (Ayurveda-based personalization).
- Notifications, coaching, or recommendations.
- Real device integrations (Apple Watch / WearOS).
- Data export, summaries over longer periods, or advanced analytics.

## Acceptance Criteria
- App builds and runs on emulator (API 26+ min, target 35).
- Dashboard displays HR & RR averages and two charts without crashes.
- Thresholds visible on both charts; outliers clearly distinguishable.
- Network stack hits mock server at `http://10.0.2.2:8080/*` and renders returned data.
- Error/loading states are user-visible and recoverable (retry/refresh).

## Tech Stack (Purpose)
- **Android + Kotlin (2.0.20)**: app language & coroutines.
- **Jetpack Compose**: modern declarative UI.
- **Vico (2.0.0)**: Compose-first charting (Cartesian line charts).
- **Hilt (2.52)**: DI for ViewModels/repo wiring.
- **Retrofit + OkHttp**: HTTP client for mock API.
- **kotlinx.serialization**: JSON (client & server).
- **Room (KSP, 2.8.1)**: local storage (entities/DAO if needed).
- **Ktor (server module)**: local mock API for repeatable data.
- **Gradle/AGP 8.7.0**: build system aligned with Kotlin/Compose.

## Risks & Mitigations
- **Version drift (Kotlin/Compose/Room)** → Lock versions via `libs.versions.toml`; run dependency report when updating.
- **Chart performance with large series** → Start with small windows (e.g., daily/weekly); paginate later.
- **Cleartext restrictions** → Network security config limited to `10.0.2.2` in debug only.

## Deliverables for Sprint 2
- Threshold rendering for HR & RR charts in the dashboard.
- Updated docs if behavior/UX changes (no checklist updates required this sprint).

## Timeline
- **Sprint 2:** Wed Oct 1 → Sun Oct 5 (shortened).
- Future sprints: Mon → Sun cadence.

## Version
- Document version: `MVP_SCOPE v1.0` (created this sprint).
