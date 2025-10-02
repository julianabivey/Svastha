# 🧘‍♀️ Svastha Project – Sprint 1 Retrospective

**Date:** Wednesday October 1
**Sprint Goal:** Establish working baseline app + server with end-to-end communication.

---

## ✅ Achievements (Done)

- **Server Setup (Ktor):**
  - Created mock backend serving biometric data (`/biometrics/summary`).
  - Added Kotlin Serialization for JSON encoding/decoding.
  - Verified server runs locally (`http://0.0.0.0:8080`) and responds to client requests.

- **Android App Setup:**
  - Configured Jetpack Compose UI with Material3 components.
  - Built starter DashboardScreen with state placeholders (`loading`, `error`, `hrAvg`, `rrAvg`).
  - Integrated `ViewModel` + `StateFlow` for reactive state management.
  - Wired dependencies with Hilt (ViewModel, Repository, Retrofit client).

- **Networking & Communication:**
  - Connected app to server using Retrofit + OkHttp.
  - Confirmed round-trip data flow from server → app UI.

- **Testing & Validation:**
  - Host `curl` successfully fetched data from server.
  - Emulator browser accessed mock endpoint (`http://10.0.2.2:8080`).
  - Created and versioned **CHECKLIST.md** documenting round-trip verification.

---

## 🛠️ Tech Stack (Tools & Purpose)

- **Kotlin (JVM + Android):** Main language for app + server.  
- **Jetpack Compose:** Declarative UI framework.  
- **Compose Material3:** Material Design components.  
- **ViewModel + StateFlow:** State management.  
- **Hilt (Dagger):** Dependency injection.  
- **Retrofit + OkHttp:** Networking.  
- **Kotlinx Serialization:** JSON handling.  
- **Room (planned):** Local persistence.  
- **WorkManager:** Background tasks.  
- **Datastore:** Lightweight settings storage.  
- **Ktor:** Mock backend server.  
- **Gradle (KTS + Version Catalog):** Build + dependency management.  

---

## 🚧 Challenges / Fixes

- Resolved build issues with mismatched Compose + Kotlin versions.  
- Fixed `@Serializable` error by ensuring Kotlin serialization plugin applied.  
- Allowed cleartext traffic (debug only) for `10.0.2.2`.  
- Increased Gradle memory settings to avoid GC thrashing.  
- Cleaned `.gitignore` to remove unwanted build artifacts from VCS.  

---

## 🎯 Next Sprint Goals

- Add **charts** to visualize biometric data in dashboard.  
- Define and display **thresholds** (e.g., HR > 100 → red highlight).  
- Persist mock data locally (Room integration).  
- Add unit tests for ViewModel + Repository.  
