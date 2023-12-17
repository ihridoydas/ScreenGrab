package jp.ihridoydas.screengrab.screenGrabber.controller

import android.graphics.Bitmap
import android.graphics.Picture
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawModifierNode
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DelegatingNode
import androidx.compose.ui.node.ModifierNodeElement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @param controller A [GrabController] which gives control to capture the [content].
 * @param modifier The [Modifier] to be applied to the layout.
 * @param onGrabbed The callback which gives back [ImageBitmap] after composable is captured.
 * If any error is occurred while capturing bitmap, [Throwable] is provided.
 * @param content [Composable] content to be captured.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Deprecated(
    "This Composable method has been deprecated & will be removed in the future releases. " +
            "Use Modifier `ScreenGrabber()` directly.",
    level = DeprecationLevel.WARNING
)
@Composable
fun ScreenGrabber(
    controller: GrabController,
    modifier: Modifier = Modifier,
    onGrabbed: (ImageBitmap?, Throwable?) -> Unit,
    content: @Composable () -> Unit
) {
    val updatedOnGrabbed by rememberUpdatedState(newValue = onGrabbed)

    Column(modifier = modifier.screenGrabber(controller)) {
        content()
    }

    LaunchedEffect(key1 = controller) {
        controller.grabRequests.collect { request ->
            try {
                val imageBitmap = request.imageBitmapDeferred.await()
                updatedOnGrabbed(imageBitmap, null)
            } catch (error: Throwable) {
                updatedOnGrabbed(null, error)
            }
        }
    }
}

/**
 *
 * @param controller A [GrabController] which gives control to capture the Composable content.
 */
@ExperimentalComposeUiApi
fun Modifier.screenGrabber(controller: GrabController): Modifier {
    return this then GrabbableModifierNodeElement(controller)
}

/**
 * Modifier implementation of Grabbable
 */
private data class GrabbableModifierNodeElement(
    private val controller: GrabController
) : ModifierNodeElement<GrabbableModifierNode>() {
    override fun create(): GrabbableModifierNode {
        return GrabbableModifierNode(controller)
    }

    override fun update(node: GrabbableModifierNode) {
        node.controller = controller
    }
}

/**
 * Grabbable Modifier node which delegates task to the [CacheDrawModifierNode] for drawing
 * Composable UI to the Picture and then helping it to converting picture into a Bitmap.
 */
@Suppress("unused")
private class GrabbableModifierNode(
    var controller: GrabController
) : DelegatingNode(), DelegatableNode {

    val picture = Picture()

    /**
     * Delegates the drawing to [CacheDrawModifierNode] in order to draw content rendered on the
     * canvas directly to the [picture].
     */
    val drawModifierNode = delegate(
        CacheDrawModifierNode {
            // Example that shows how to redirect rendering to an Android Picture and then
            // draw the picture into the original destination
            val width = this.size.width.toInt()
            val height = this.size.height.toInt()

            onDrawWithContent {
                val pictureCanvas = Canvas(picture.beginRecording(width, height))

                draw(this, this.layoutDirection, pictureCanvas, this.size) {
                    this@onDrawWithContent.drawContent()
                }
                picture.endRecording()

                drawIntoCanvas { canvas -> canvas.nativeCanvas.drawPicture(picture) }
            }
        }
    )

    override fun onAttach() {
        super.onAttach()
        coroutineScope.launch {
            controller.grabRequests.collect { request ->
                val completable = request.imageBitmapDeferred
                try {
                    val bitmap = withContext(Dispatchers.Default) {
                        picture.asBitmap(request.config)
                    }
                    completable.complete(bitmap.asImageBitmap())
                } catch (error: Throwable) {
                    completable.completeExceptionally(error)
                }
            }
        }
    }
}

/**
 * Creates a [Bitmap] from a [Picture] with provided [config]
 */
private fun Picture.asBitmap(config: Bitmap.Config): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        Bitmap.createBitmap(this@asBitmap)
    } else {
        val bitmap = Bitmap.createBitmap(
            /* width = */
            this@asBitmap.width,
            /* height = */
            this@asBitmap.height,
            /* config = */
            config
        )
        val canvas = android.graphics.Canvas(bitmap)
        canvas.drawColor(android.graphics.Color.WHITE)
        canvas.drawPicture(this@asBitmap)
        bitmap
    }
}