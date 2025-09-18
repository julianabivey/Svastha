package com.yuli.svastha.ui.dashboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuli.svastha.data.repo.BiometricsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

data class DashboardState(
  val loading: Boolean = true,
  val error: String? = null,
  val hrAvg: Double? = null,
  val rrAvg: Double? = null
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
    _state.value = _state.value.copy(loading = true, error = null)
    val res = repo.refresh()
    val today = LocalDate.now().toString()
    val sum = repo.getSummary(today)
    _state.value = _state.value.copy(
      loading = false,
      error = res.exceptionOrNull()?.message,
      hrAvg = sum?.hrAvg, rrAvg = sum?.rrAvg
    )
  }
}
