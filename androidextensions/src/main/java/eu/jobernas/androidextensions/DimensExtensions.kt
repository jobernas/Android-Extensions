package eu.jobernas.androidextensions

import kotlin.math.floor

val Int.dp: Int get() = if (this == 0) 0 else floor(ExtensionsWrapper.density * this.toDouble()).toInt()

val Float.dp: Float get() = if (this == 0f) 0f else floor(ExtensionsWrapper.density * this.toDouble()).toFloat()

val Float.dpF: Float get() = if (this == 0f) 0f else floor(ExtensionsWrapper.density * this.toDouble()).toFloat()

val Int.sp: Int get() = if (this == 0) 0 else floor(ExtensionsWrapper.fontDensity * this.toDouble()).toInt()

val Float.sp: Float get() = if (this == 0f) 0f else floor(ExtensionsWrapper.fontDensity * this.toDouble()).toFloat()

val Float.spInt: Int get() = this.sp.toInt()