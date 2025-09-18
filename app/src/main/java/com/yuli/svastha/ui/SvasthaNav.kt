package com.yuli.svastha.ui
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.*
import com.yuli.svastha.ui.dashboard.DashboardScreen
import com.yuli.svastha.ui.dashboard.DashboardViewModel

@Composable
fun SvasthaNav() {
  val nav = rememberNavController()
  NavHost(navController = nav, startDestination = "dashboard") {
    composable("dashboard") {
      val vm = hiltViewModel<DashboardViewModel>()
      val uiStateState = vm.state.collectAsState()
      val uiState = uiStateState.value
      DashboardScreen(state = uiState, onRefresh = vm::refresh)
    }
  }
}
