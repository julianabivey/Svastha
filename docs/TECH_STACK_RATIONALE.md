# Tech Stack Rationale

## UI Layer
- **Jetpack Compose**
  - Declarative UI → less boilerplate, reactive updates via state/recomposition.
  - Replaces XML layouts and ViewBinding for modern apps.
  - Future-focused: Google is prioritizing Compose development.

- **Vico (Charts)**
  - Compose-first charting library.
  - Lightweight, modern, avoids legacy `AndroidView` shims.
  - Active community; simpler customization compared to MPAndroidChart.

## Dependency Injection
- **Hilt**
  - Officially supported DI framework for Android.
  - Integrates smoothly with Compose and Android lifecycle.
  - Reduces boilerplate vs. Dagger setup.

## Networking
- **Retrofit + OkHttp**
  - Retrofit for declarative API interfaces.
  - OkHttp for robust, low-level HTTP client.
  - Together, they simplify and standardize REST calls.

## Data Layer
- **Room**
  - Modern SQLite wrapper with Kotlin extensions.
  - Built-in support for coroutines + Flow.
  - Strong compile-time checks for queries.

- **DataStore**
  - Replaces SharedPreferences.
  - Coroutine/Flow friendly, type-safe storage for key-value pairs.

## Serialization
- **Kotlinx Serialization**
  - Native Kotlin serialization/deserialization.
  - Works with Retrofit when combined with `asConverterFactory`.
  - Avoids third-party dependencies like Gson.

## Async / Concurrency
- **Kotlin Coroutines + Flow**
  - Simplifies threading and async work.
  - Seamless integration with Compose recomposition.

## Architecture
- **MVVM with StateFlow**
  - ViewModel holds immutable `UiState`.
  - Compose UI observes state with `collectAsState`.
  - Clean separation of concerns, testable.
