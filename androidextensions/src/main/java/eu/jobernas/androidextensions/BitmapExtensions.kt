package eu.jobernas.androidextensions

import android.annotation.TargetApi
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.util.Base64
import androidx.core.content.FileProvider
import java.io.ByteArrayOutputStream
import java.io.File

/**
 * Convert Bitmap to String Base64
 *
 * @param quality
 * @param flag
 * @return
 */
fun Bitmap.convertToPNGStringBase64(quality: Int = 100, flag: Int = Base64.DEFAULT): String {
	val imageByteArray: ByteArray = convertPngToByteArray(quality)
	return imageByteArray.encodeBase64(flag)
}

/**
 * Convert to jpeg string base64
 *
 * @param quality, image compression quality, default is 100
 * @param flag, Encoding Base Flag, default is Base64.DEFAULT
 * @return String image encoded Base64
 * @throws Error, Several possible errors on converting
 */
@Throws(Throwable::class)
fun Bitmap.convertToJpegStringBase64(quality: Int = 100, flag: Int = Base64.DEFAULT): String {
	val imageByteArray: ByteArray = convertJpegToByteArray(quality)
	return imageByteArray.encodeBase64(flag)
}

/**
 * Convert jpeg to byte array
 *
 * @param quality
 * @return ByteArray with JPEG image data else throws
 * @throws Error converting, several kind of errors
 */
@Throws(Throwable::class)
fun Bitmap.convertJpegToByteArray(quality: Int = 100): ByteArray =
	ByteArrayOutputStream().apply {
		compress(Bitmap.CompressFormat.JPEG, quality, this)
	}.toByteArray()

/**
 * Convert png to byte array
 *
 * @param quality
 * @return ByteArray with PNG image data else throws
 * @throws Error converting, several kind of errors
 */
@Throws(Throwable::class)
fun Bitmap.convertPngToByteArray(quality: Int = 100): ByteArray =
	ByteArrayOutputStream().apply {
		compress(Bitmap.CompressFormat.PNG, quality, this)
	}.toByteArray()

/**
 * Convert jpeg to file
 *
 * @param context
 * @param name
 * @param parentFile
 * @return File with path to Jpeg Bitmap file.
 * @throws Error, if Context is null
 */
@Throws(Throwable::class)
fun Bitmap.convertJpegToFile(context: Context?, name: String, parentFile: File? = context?.cacheDir): File {
	val fileDestination = File(parentFile, name)
	fileDestination.createNewFile()
	val fileOutPutStream = fileDestination.toOutputStream()
	fileOutPutStream.apply {
		write(convertJpegToByteArray())
		flush()
		close()
	}
	return fileDestination
}

/**
 * Convert png to file
 *
 * @param context
 * @param name
 * @param parentFile
 * @return
 */
@Throws(Throwable::class)
fun Bitmap.convertPngToFile(context: Context?, name: String, parentFile: File? = context?.cacheDir): File {
	val fileDestination = File(parentFile, name)
	fileDestination.createNewFile()
	val fileOutPutStream = fileDestination.toOutputStream()
	fileOutPutStream.apply {
		write(convertPngToByteArray())
		flush()
		close()
	}
	return fileDestination
}

/**
 * Create temporary image
 * Note: File Provider Configuration is required in order to use this:
 * @sample Create a provider paths file in xml like this provider_paths.xml
 * <?xml version="1.0" encoding="utf-8"?>
 * <paths>
 *     <cache-path
 *     name="shared_images"
 *     path="images/" />
 * </paths>
 * And After add it to Application Manifest:
 * <provider
 * 		android:name="androidx.core.content.FileProvider"
 * 		android:authorities="${applicationId}.fileprovider"
 * 		android:exported="false"
 * 		android:grantUriPermissions="true">
 * 		<meta-data
 * 			android:name="android.support.FILE_PROVIDER_PATHS"
 * 			android:resource="@xml/provider_paths" />
 * </provider>
 *
 * @param context
 * @param extension
 * @param folder
 * @param filePrefix
 * @return
 */
@TargetApi(Build.VERSION_CODES.N)
@Throws(Throwable::class)
fun Bitmap.createTemporaryImage(context: Context, extension: String, folder: String? = null, filePrefix: String = "image"): Uri {
	val folderFile = if (folder != null) context.createFolderIfNotExists(folder) else context.cacheDir
	val imageName = "${filePrefix}_" + System.currentTimeMillis() + ".$extension"
	val tmpFile = when(extension) {
		"jpg" -> convertJpegToFile(context, imageName, folderFile)
		"png" -> convertPngToFile(context, imageName, folderFile)
		else -> throw Throwable("Extension not recognized.")
	}
	return FileProvider.getUriForFile(context.applicationContext, "${context.applicationContext.packageName}.fileprovider", tmpFile)
}

/**
 * Copy to clipboard
 *
 * @param context
 * @param extension
 * @param folder
 * @param labelTitle
 */
fun Bitmap.copyToClipboard(context: Context?, extension: String, labelTitle: String, folder: String? = null) {
	val contextNotNull = context ?: return
	val imageTmpUri = this.createTemporaryImage(contextNotNull, extension, folder, labelTitle)
	val clipData = ClipData.newUri(contextNotNull.contentResolver, labelTitle, imageTmpUri)
	val clipboardManager: ClipboardManager? = contextNotNull.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
	clipboardManager?.setPrimaryClip(clipData)
}