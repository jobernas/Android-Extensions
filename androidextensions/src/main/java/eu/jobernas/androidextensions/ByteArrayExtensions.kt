package eu.jobernas.androidextensions

import android.util.Base64

/**
 * Encode base64
 *
 * @param flag
 * @return
 */
fun ByteArray.encodeBase64(flag: Int = Base64.DEFAULT): String =
        Base64.encodeToString(this, flag)
