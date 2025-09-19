# Round-Trip Checklist (Ktor ↔ Emulator ↔ App)

This doc explains how to run the mock server locally and verify end-to-end communication with the Android emulator.

---

## A) Start the server
1. From project root:
   ```bash
   ./gradlew :server:run
   ```
2. Confirm log shows:
   ```
   Responding at http://0.0.0.0:8080
   ```
   > Leave this process running in its terminal tab.

---

## B) Sanity-check from your Mac (host)
1. In a new terminal:
   ```bash
   curl http://localhost:8080/
   curl http://localhost:8080/biometrics/summary
   ```
   Expected response (example):
   ```json
   {"hrAvg":72,"rrAvg":16}
   ```
2. If errors occur:
   - Ensure `kotlin("plugin.serialization")` in `server/build.gradle.kts`
   - Confirm `install(ContentNegotiation) { json() }` in `Application`
   - Check firewall or port conflicts

---

## C) Sanity-check from the emulator
1. Make sure the emulator is running.  
2. Open the **Browser/Chrome app** inside the emulator.  
3. Navigate to:  
   ```
   http://10.0.2.2:8080/
   http://10.0.2.2:8080/biometrics/summary
   ```
4. You should see raw JSON if the server is running, e.g.:  
   ```json
   {"hrAvg":72,"rrAvg":16}
   ```

> `10.0.2.2` maps the emulator → host machine.

---

## D) App prerequisites
1. Retrofit base URL:
   ```kotlin
   "http://10.0.2.2:8080/"
   ```
2. AndroidManifest permissions:
   ```xml
   <uses-permission android:name="android.permission.INTERNET"/>
   <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
   ```
3. Debug-only cleartext config:
   - `app/src/debug/AndroidManifest.xml`:
     ```xml
     <application android:networkSecurityConfig="@xml/network_security_config"/>
     ```
   - `app/src/debug/res/xml/network_security_config.xml`:
     ```xml
     <network-security-config>
       <domain-config cleartextTrafficPermitted="true">
         <domain>10.0.2.2</domain>
         <domain>localhost</domain>
       </domain-config>
     </network-security-config>
     ```
4. Remove Compose plugin/dependencies from `:server` module.

---

## E) Troubleshooting
- **CLEARTEXT not permitted** → Add debug `network_security_config`
- **SocketException: EPERM** → Missing `INTERNET` permission
- **Unhandled GET /biometrics/summary** → Route missing/typo, test via curl
- **Serializer not found** → Add `@Serializable` and serialization plugin
- **Server hangs at 83%** → It’s running; test with curl
- **Compose compiler error in server** → Ensure server has no Compose deps

---

## F) (Optional) Physical device
- Use LAN IP: `http://<your-mac-ip>:8080/` and add to `network_security_config`
- Or tunnel via ADB:
  ```bash
  adb reverse tcp:8080 tcp:8080
  ```
  Then use:
  ```bash
  http://127.0.0.1:8080/
  ```
