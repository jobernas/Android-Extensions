package eu.jobernas.androidextensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import java.io.File
import java.io.FileOutputStream

/**
 * TAG
 */
val String.TAG: String
	get() = "STRING_EXTENSIONS"

/**
 * Decode Base64
 *
 * @param flag
 * @return
 */
fun String.decodeBase64(flag: Int = Base64.DEFAULT): ByteArray =
		Base64.decode(this, flag)

/**
 * Base64to bitmap
 *
 * @return
 */
fun String.base64ToBitmap(): Bitmap? {
	val imageAsBytes: ByteArray = Base64.decode(this.toByteArray(), Base64.DEFAULT)
	return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
}

/**
 * Convert to image png
 *
 * @param context
 * @param name
 * @return Bitmap
 */
fun String.convertToImagePNG(context: Context?, name: String): Bitmap? {
	val contextNotNull = context ?: return null
	val decodedString: ByteArray = decodeBase64()
	val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
	val file = File(contextNotNull.cacheDir, "$name.png")
	return try {
		FileOutputStream(file).apply {
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
			flush()
			close()
		}
		bitmap
	} catch (e: Exception) {
		Log.e(TAG, "Error", e)
		e.printStackTrace()
		null
	}
}

/**
 * Convert to image jpeg
 *
 * @param context
 * @param name
 * @return
 */
fun String.convertToImageJPEG(context: Context?, name: String): Bitmap? {
	val contextNotNull = context ?: return null
	val decodedString: ByteArray = decodeBase64()
	val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
	val file = File(contextNotNull.cacheDir, "$name.jpg")
	return try {
		FileOutputStream(file).apply {
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, this)
			flush()
			close()
		}
		bitmap
	} catch (e: Exception) {
		Log.e(TAG, "Error", e)
		e.printStackTrace()
		null
	}
}

/**
 * Sanitize int
 *
 * @return
 */
fun String.sanitizeInt(): Int? =
		this.replace("[^0-9]+".toRegex(), "")
			.toIntOrNull()

/**
 * Sanitize double
 *
 * @return
 */
fun String.sanitizeDouble(): Double? =
		this.replace("[^0-9.,]+".toRegex(), "")
			.replace(",", ".")
			.toDoubleOrNull()

/**
 * Copy to clipboard
 *
 * @param context
 * @param labelTitle
 */
fun String.copyToClipboard(context: Context?, labelTitle: String) {
	val clipboard: ClipboardManager = context?.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager ?: return
	val clip = ClipData.newPlainText(labelTitle, this)
	clipboard.setPrimaryClip(clip)
}