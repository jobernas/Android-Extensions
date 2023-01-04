package eu.jobernas.androidextensions

import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Tag
 */
val File.TAG: String
    get() = "FILE_EXTENSIONS"

/**
 * Create directory or directories to match Directory Path if Directory does not exists.
 *
 * @throws Error if directory is not possible to create
 */
@Throws(IOException::class)
fun File.createDirectory() {
    if (exists()) {
        if (!isDirectory) throw IOException("$TAG, Can't create directory, a file is in the way")
    } else {
        mkdirs()
        if (!isDirectory) throw IOException("$TAG, Unable to create directory")
    }
}

/**
 * Convert File object to FileOutputStream object
 *
 * @return FileOutputStream
 * @throws Error if file is not found
 */
@Throws(FileNotFoundException::class)
fun File.toOutputStream(): FileOutputStream
        = FileOutputStream(this)