package com.navband.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import android.content.Intent
import androidx.compose.material3.MaterialTheme
import org.osmdroid.config.Configuration
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.navband.app.datastore.UserViewModel
import com.navband.app.datastore.UserViewModelFactory
import com.navband.app.models.NfcViewModel
import com.navband.app.services.NfcReader
import com.navband.app.ui.theme.NavbandAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var nfcReader: NfcReader
    private val viewModel by viewModels<NfcViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nfcReader = NfcReader(this) { scannedId ->
            viewModel.onTagScanned(scannedId)
        }

        Configuration.getInstance().userAgentValue = applicationContext.packageName
        enableEdgeToEdge()
        setContent {
            NavbandAppTheme {
                val userViewModel: UserViewModel = viewModel(
                    factory = UserViewModelFactory(LocalContext.current)
                )

                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    Navegacao(
                        modifier = Modifier
                            .systemBarsPadding()
                            .padding(innerPadding)
                            .background(color = MaterialTheme.colorScheme.primary),
                        viewModel = userViewModel,
                        nfcModel = viewModel
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.nfcEnabled.value) {
            nfcReader.enableForegroundDispatch()
        }
    }

    override fun onPause() {
        super.onPause()
        nfcReader.disableForegroundDispatch()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (viewModel.nfcEnabled.value) {
            nfcReader.handleIntent(intent)
        }
    }
}
