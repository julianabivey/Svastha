package com.yuli.svastha.ui.dashboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuli.svastha.data.db.SampleEntity
import com.yuli.svastha.data.db.SummaryEntity
import com.yuli.svastha.data.repo.BiometricsRepository
import com.yuli.svastha.domain.DefaultThresholds
import com.yuli.svastha.domain.Threshold
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class DashboardState(
  val loading: Boolean = true,
  val error: String? = null,
  val hrAvg: Float? = null,
  val rrAvg: Float? = null,
  val heartRateSeries: List<Float> = emptyList(),
  val respRateSeries: List<Float> = emptyList(),
  val hrThreshold: Threshold = DefaultThresholds.HR,
  val rrThreshold: Threshold = DefaultThresholds.RR

)

//Todo: Collect the StateFlow before passing it to the composable.
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.collectAsState
//
//@Composable
//fun SvasthaNav() {
//  val nav = rememberNavController()
//  NavHost(navController = nav, startDestination = "dashboard") {
//    composable("dashboard") {
//      val vm = hiltViewModel<com.yuli.svastha.ui.dashboard.DashboardViewModel>()
//      val uiState by vm.state.collectAsState()
//      DashboardScreen(state = uiState, onRefresh = vm::refresh)
//    }
//  }
//}
@HiltViewModel
class DashboardViewModel @Inject constructor(
  private val repo: BiometricsRepository
) : ViewModel() {
  private val _state = MutableStateFlow(DashboardState())
  val state: StateFlow<DashboardState> = _state

  init { refresh() }

  fun refresh() = viewModelScope.launch {
    _state.update { it.copy(loading = true, error = null) }

    // runCatching executes the block and catches any exception.
    // The Result will contain our fetched data on success, or a Throwable on failure.
    val result: Result<Triple<SummaryEntity?, List<SampleEntity>, List<SampleEntity>>> = runCatching {
      // Switch to the IO dispatcher for all background work
      withContext(Dispatchers.IO) {
        // This will throw an exception if repo.refresh() fails,
        // which will then be caught by runCatching.
        repo.refresh().getOrThrow()

        val today = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)

        // Fetch other data in parallel
        val summaryDef = async { repo.getSummary(today) }      // Returns SummaryEntity?
        val hrDef      = async { repo.getHeartSeries() }       // Returns List<SampleEntity>
        val rrDef      = async { repo.getRespSeries() }        // Returns List<SampleEntity>

        // Await all results
        val summary = summaryDef.await()
        val heart   = hrDef.await()
        val resp    = rrDef.await()

        // Return all three results together in a Triple
        Triple(summary, heart, resp)
      }
    }

    // This is now the single point where we update our UI state
    _state.update { currentState ->
      result.fold(
        onSuccess = { (summary, heart, resp) ->
          // Destructuring the Triple for easy access
          currentState.copy(
            loading = false,
            error = null,
            hrAvg = summary?.hrAvg,
            rrAvg = summary?.rrAvg,
            // Assuming SampleEntity has a 'value' property
            heartRateSeries = heart.map { it.value },
            respRateSeries = resp.map { it.value }
          )
        },
        onFailure = { throwable ->
          // This block runs if repo.refresh() fails or any other exception occurs
          currentState.copy(
            loading = false,
            error = throwable.message ?: "Unknown error"
          )
        }
      )
    }
  }




}
