package com.yuli.svastha
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yuli.svastha.ui.SvasthaNav
import com.yuli.svastha.ui.theme.SvasthaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent { SvasthaTheme { SvasthaNav() } }
  }
}
