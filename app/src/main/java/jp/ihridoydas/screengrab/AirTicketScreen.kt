package jp.ihridoydas.screengrab

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import jp.ihridoydas.screengrab.screenGrabber.controller.rememberScreenGrabController
import jp.ihridoydas.screengrab.screenGrabber.controller.screenGrabber
import jp.ihridoydas.screengrab.screenGrabber.storeInLocal.saveBitmapToGallery
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeApi::class)
@Composable
fun AirTicketScreen(context:Context) {

    val grabController = rememberScreenGrabController()
    val uiScope = rememberCoroutineScope()

    // This will hold captured bitmap
    // So that we can demo it
    var ticketBitmap: ImageBitmap? by remember { mutableStateOf(null) }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(24.dp)
    ) {
        // The content to be grabbed
        AirTicket(modifier = Modifier
            .screenGrabber(grabController))

        Spacer(modifier = Modifier.size(32.dp))

        // Screen Captures ticket bitmap on click
        Button(
            onClick = {
                uiScope.launch {
                    ticketBitmap = grabController.screenGrabAsync().await()
                }

            }
        ) {
            Text("Preview Ticket Image")
        }

        // When Ticket's Bitmap image is captured, show preview in dialog
        ticketBitmap?.let { bitmap ->
                Dialog(onDismissRequest = { }) {
                    Column(
                        modifier = Modifier
                            .background(Color.LightGray)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier,
                            contentAlignment = Alignment.BottomCenter
                        ){
                            Text("Preview of Ticket image \uD83D\uDC47")
                        }

                        Spacer(Modifier.size(16.dp))
                        Image(
                            bitmap = bitmap,
                            contentDescription = "Preview of ticket"
                        )
                        Spacer(Modifier.size(4.dp))
                        Button(onClick = {
                            //save image from bitmap
                            saveBitmapToGallery(context = context,bitmap.asAndroidBitmap())
                        }) {
                            Text("Save Image")
                        }
                        Spacer(modifier = Modifier.size(32.dp))
                        Button(onClick = { ticketBitmap = null }) {
                            Text("Close Preview")
                        }
                    }
                }

        }
    }
}