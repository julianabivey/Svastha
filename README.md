# Svastha · Personal Wellness Insights

Svastha is a modern Android application that helps individuals cultivate stronger
awareness of their physiological wellness patterns — starting with **heart rate** and
**respiratory rate**. The goal is to make biometric data feel clear, supportive, and
actionable, without overwhelming users.

---

## ✨ Current Features (MVP In Progress)

- 📊 Dashboard with **live biometric charting** (HR + RR)
- 🎯 Smart threshold guidance for normal vs. out-of-range values
- 🔄 Pull-to-refresh realtime syncing from wearable data sources *(mocked for now)*
- ⚡ Smooth, responsive UI powered by Jetpack Compose + Kotlin coroutines
- 🧠 Predictable state management using StateFlow + ViewModel

Upcoming sprint focus:
- Adjustable personalized threshold settings
- Persistent preferences via DataStore
- Multiple chart display polish

---

## 🧱 Tech Stack

**Mobile Platform**
- Android (minSdk 26, targetSdk 35)
- Kotlin 2.1.x

**Architecture**
- MVVM
- Repository pattern
- StateFlow for reactive UI state
- Modular layering (data → domain → ui)

**UI**
- Jetpack Compose
- Material Design 3
- Vico charts (Cartesian line charts)

**Data**
- Room Database
- Android DataStore *(work in progress)*

**Networking & Async**
- Retrofit + OkHttp
- Coroutines + structured concurrency

**Dependency Injection**
- Dagger Hilt

---

## 🧭 Project Structure

svastha/
├─ data/
│ ├─ local/ # Room DB, entities, DAO
│ ├─ remote/ # Future wearable integration
│ └─ repo/ # BiometricsRepository
├─ domain/ # Business models & use cases (growing)
└─ ui/
├─ dashboard/
├─ settings/ # Threshold editing (coming soon)
└─ components/

Organized for clarity, testability, and clean growth over time.

---

## 🚀 Running the Project

**Requirements**
- Android Studio Hedgehog or later
- JDK 17

To build from command line:
./gradlew assembleDebug

Start up the mock server from the command line:
./gradlew :server:run

Then run directly from Android Studio on an emulator or physical device.

---

## 💡 Philosophy

> Small, consistent shifts in awareness lead to meaningful improvements in wellness.

Biometric data should feel **supportive** and **empowering** —
not clinical, overwhelming, or alarm-driven.

Svastha aims to bridge that gap by making trends visible, contextual,
and aligned with each user’s personal sense of balance.

---

## 🤝 Project Status

Currently under active solo development — **v0.x**  

---

### © 2025 Svastha — A wellness project by Juliana Ivey