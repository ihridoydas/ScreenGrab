package jp.ihridoydas.screengrab.screenGrabber.storeInLocal

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Save bitmap to gallery
fun saveBitmapToGallery(context: Context, bitmap: Bitmap) {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val displayName = "screenGrab_image_$timeStamp.png"
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
    }

    val contentResolver = context.contentResolver
    val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        try {
            val outputStream: OutputStream? = contentResolver.openOutputStream(uri)
            outputStream?.use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.clear()
                contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
                contentResolver.update(uri, contentValues, null, null)
            }

            Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}