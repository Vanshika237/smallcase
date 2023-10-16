package com.vnshk.smallcase

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.vnshk.smallcase.datamodels.toMap
import com.vnshk.smallcase.ui.MainScreen
import com.vnshk.smallcase.ui.components.ShowDataDialog
import com.vnshk.smallcase.ui.theme.SmallcaseTheme
import com.vnshk.smallcase.viewmodels.DataViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val showDialog = mutableStateOf(false)

        val dataParams = HashMap<String, Any>()
        val intentData: Uri? = intent?.data

        val dataViewModel = ViewModelProvider(this).get(DataViewModel::class.java)

        setContent {
            val apiData by dataViewModel.data.collectAsState()
            val context = LocalContext.current
            SmallcaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (intentData != null) {
                        dataParams.clear()
                        val paramNames = intentData.queryParameterNames
                        for (name in paramNames) {
                            intentData.getQueryParameter(name)?.let { dataParams.put(name, it) }
                        }
                        showDialog.value = true
                    }

                    MainScreen(
                        dataViewModel = dataViewModel,
                        openInBrowser = {
                            val url = "https://webcode.tools/generators/html/hyperlink"
                            val intent = CustomTabsIntent.Builder()
                                .build()
                            intent.launchUrl(context, Uri.parse(url))
                        },
                        callApi = {
                            if (apiData != null) {
                                dataParams.clear()
                                dataParams.putAll(apiData!!.toMap())
                                showDialog.value = true
                            }
                        }
                    )

                    when {
                        showDialog.value -> {
                            ShowDataDialog(
                                onDismiss = {
                                    showDialog.value = false
                                },
                                dialogData = dataParams,
                            )
                        }
                    }
                }
            }
        }
    }
}