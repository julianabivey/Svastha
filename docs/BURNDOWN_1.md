# 📊 Svastha Project – Sprint 1 Burndown Summary

**Date:** October 1
**Sprint Goal:** Establish working baseline app + server with end-to-end communication.

---

## 🔥 Sprint Scope Overview

| Item                                    | Status       | Notes                                                                 |
|-----------------------------------------|--------------|-----------------------------------------------------------------------|
| Setup Ktor mock server                  | ✅ Done       | Responds at `/biometrics/summary` with mock JSON.                     |
| Add Kotlin serialization                | ✅ Done       | Fixed missing `@Serializable` issue.                                  |
| Basic Android app scaffold              | ✅ Done       | Jetpack Compose + Material3 baseline.                                 |
| Wire up ViewModel + StateFlow           | ✅ Done       | Reactive state hooked into Dashboard UI.                              |
| Integrate Retrofit networking           | ✅ Done       | Connected to mock server, data flows end-to-end.                      |
| Emulator ↔ Server testing               | ✅ Done       | Verified via curl + emulator browser at `http://10.0.2.2:8080`.       |
| Documentation (CHECKLIST.md)            | ✅ Done       | Round-trip testing workflow documented.                               |
| Fix Gradle memory & version issues      | ✅ Done       | Updated `gradle.properties`, aligned Compose + Kotlin versions.       |
| Allow cleartext for debug               | ✅ Done       | Configured `network_security_config.xml`.                             |
| Charts for dashboard                    | 🚧 Deferred   | Planned for Sprint 2.                                                 |
| Threshold-based highlights              | 🚧 Deferred   | Planned for Sprint 2.                                                 |
| Room persistence                        | 🚧 Deferred   | Planned for Sprint 2.                                                 |
| Unit tests                              | 🚧 Deferred   | Planned for Sprint 2.                                                 |

---

## 📉 Burndown Snapshot

- **Planned items:** 13  
- **Completed:** 9  
- **Deferred / Carried Forward:** 4  

Completion ratio: **~70%**

---

## 🎯 Sprint 2 Focus

- Add charts to dashboard.  
- Implement threshold highlights.  
- Persist biometric data locally with Room.  
- Begin unit test coverage.  
