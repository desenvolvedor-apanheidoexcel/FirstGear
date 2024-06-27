package com.ffcs.primeiramarcha.utils

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar

object Utilitarios {

    // convert Image bitmap to Base64
    fun BitmaptoBase64(bm: Bitmap): String? {
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
    // convert Base64 to Image bitmap
    fun Base64toBitmap(base64String: String): Bitmap {
        val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun pinLocationMap(latitude: String, longitude: String): Intent {
        val mapUri = Uri.parse("https://maps.google.com/maps/search/$latitude,$longitude")
        return Intent(Intent.ACTION_VIEW, mapUri)
    }

    fun actualDateFormated(mask: String): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat(mask)
        val current = formatter.format(time)
        return current
    }

}