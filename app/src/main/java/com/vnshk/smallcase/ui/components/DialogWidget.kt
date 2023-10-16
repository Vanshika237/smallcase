package com.vnshk.smallcase.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.vnshk.smallcase.viewmodels.DataViewModel


@Composable
fun ShowDataDialog(
    onDismiss: () -> Unit,
    dialogData: HashMap<String, Any>,
) {
    val context = LocalContext.current

    var text = ""
    val keys = dialogData.keys
    for (key in keys) {
        val jsonData = dialogData[key]
        text += key + "  (${jsonData?.javaClass?.simpleName})" + "\n" + jsonData + "\n\n"

    }

    AlertDialog(onDismissRequest = { onDismiss() }, confirmButton = {
        TextButton(
            onClick = {
                val clipboard =
                    context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("data", text)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
            }) {
            Text("Copy")
        }
    }, text = { Text(text) })
}