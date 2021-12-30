package eu.jobernas.androidextensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Tint color
 * Change Tint Color of Drawable, input ColorRes
 *
 * @param context
 * @param colorRes
 * @param mode
 * @return
 */
fun Drawable.tintColor(context: Context?,
                       @ColorRes colorRes: Int,
                       mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN): Drawable? =
    tintColor(colorRes.idToColor(context), mode)

/**
 * Tint color
 * Change Tint Color of Drawable, input Color
 *
 * @param color
 * @param mode
 * @return
 */
fun Drawable.tintColor(@ColorInt color: Int,
                       mode: PorterDuff.Mode = PorterDuff.Mode.SRC_IN): Drawable? {
    // Paint Drawable of a specific Color
    val wrapDrawable: Drawable? = this.constantState?.newDrawable()?.mutate()
    return if (wrapDrawable != null) {
        DrawableCompat.setTintMode(wrapDrawable, mode)
        DrawableCompat.setTint(wrapDrawable, color)
        DrawableCompat.unwrap(wrapDrawable)
    } else {
        null
    }
}
