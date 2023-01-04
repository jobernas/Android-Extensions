package eu.jobernas.androidextensions

import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream

/**
 * Layout inflater
 * @property layoutInflater - for given context
 */
val Context.layoutInflater: LayoutInflater
        get() = LayoutInflater.from(this)


/**
 * Has permissions for permissionValue
 *
 * @param permissionValue
 * @return
 */
fun Context.hasPermissionsFor(permissionValue: String): Boolean =
        ContextCompat.checkSelfPermission(this, permissionValue) == PackageManager.PERMISSION_GRANTED

/**
 * Is orientation landscape
 */
val Context.isOrientationLandscape: Boolean
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

/**
 * Is orientation portrait
 */
val Context.isOrientationPortrait: Boolean
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

/**
 * Main looper
 *
 * @return
 */
fun Context?.mainLooper(): Looper
        = this?.mainLooper ?: Looper.getMainLooper()

/**
 * Open app settings, open current App in Settings View
 *
 */
fun Context.openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", packageName, null)
        }
        ContextCompat.startActivity(this, intent, null)
}

/**
 * Open bluetooth settings
 *
 */
fun Context.openBluetoothSettings() {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        ContextCompat.startActivity(this, intent, null)
}

/**
 * Past text from clipboard
 *
 * @param itemAt
 * @return
 */
fun Context.pastTextFromClipboard(itemAt: Int = 0): CharSequence? {
        val clipboardManager: ClipboardManager? = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        val item = clipboardManager?.primaryClip?.getItemAt(itemAt)
        return item?.text
}

/**
 * Past image from clipboard
 *
 * @param itemAt
 * @return
 */
fun Context.pastImageFromClipboard(itemAt: Int = 0): Uri? {
        val clipboardManager: ClipboardManager? = getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager
        val item = clipboardManager?.primaryClip?.getItemAt(itemAt)
        return item?.uri
}

/**
 * Create Folder inside a location folder
 *
 * @param folderName
 * @return
 */
fun Context.createFolderIfNotExists(folderName: String, parentFile: File = cacheDir): File {
        val folder = File(parentFile, folderName)
        if (!folder.exists())
                folder.mkdirs()
        return folder
}

/**
 * Copy asset file to File Path Destination
 *
 * @param name
 * @param destinationFilePath
 * @return Long - Number of bytes copied
 */
fun Context.copyAssetFile(name: String, destinationFilePath: String): Long {
        val inputStream = assets.open(name)
        val outputStream = FileOutputStream(destinationFilePath)
        return inputStream.copyTo(outputStream)
}