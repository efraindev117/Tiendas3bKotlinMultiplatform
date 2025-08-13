package com.tienda3b.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.shared.ShareApp
import com.tienda3b.app.core.data.utils.INetworkMonitor
import com.tienda3b.app.core.designsystem.theme.Tiendas3bTheme
import org.koin.android.ext.android.inject
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val networkMonitor: INetworkMonitor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            Tiendas3bTheme {
                ShareApp(
                    networkMonitor = networkMonitor
                )
            }
        }
    }
}


