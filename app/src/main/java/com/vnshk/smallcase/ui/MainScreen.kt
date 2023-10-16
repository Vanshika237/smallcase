package com.vnshk.smallcase.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vnshk.smallcase.ui.components.FilledButtonExample
import com.vnshk.smallcase.viewmodels.DataViewModel


@Composable
fun MainScreen(openInBrowser: () -> Unit, callApi: () -> Unit, dataViewModel: DataViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        FilledButtonExample(onClick = {
            openInBrowser()
        }, text = "Open In App Browser")
        Spacer(modifier = Modifier.height(32.dp))
        FilledButtonExample(onClick = {
            callApi()
        }, text = "Call API")
    }
}
