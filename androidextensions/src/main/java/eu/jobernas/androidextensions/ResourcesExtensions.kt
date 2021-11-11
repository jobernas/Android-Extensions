package eu.jobernas.androidextensions

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * Convert Resource to Boolean
 *
 * @param context
 * @sample R.bool.is_tablet.idToBoolean(context)
 * @return true or false, DEFAULT: false
 */
fun Int.idToBoolean(context: Context?): Boolean =
    context?.resources?.getBoolean(this) ?: false

/**
 * Convert Resource to String
 *
 * @param context
 * @sample R.string.lb_app_name.idToString(context)
 * @return String DEFAULT: ""
 */
fun Int.idToString(context: Context?): String = context?.getString(this) ?: ""

/**
 * Convert Resource to Color
 *
 * @param context
 * @sample R.color.red.idToColor(context)
 * @return @ColorInt DEFAULT: WHITE
 */
fun Int.idToColor(context: Context?): Int = context?.let {
    ContextCompat.getColor(it, this)
} ?: Color.WHITE

/**
 * Convert Resource to Drawable
 *
 * @param context
 * @sample R.drawable.ic_back.idToDrawable(context)
 * @return Resource Drawable, DEFAULT: null
 */
fun Int.idToDrawable(context: Context?): Drawable? = context?.let {
    ContextCompat.getDrawable(it, this)
}

/**
 * Convert Resource to Dimen Int DEFAULT VALUE: 1px
 *
 * @param context
 * @sample R.dimen.appMargin.idToDimen(context)
 * @return Int - Size
 */
fun Int.idToDimen(context: Context?): Int = context?.resources?.getDimensionPixelSize(this) ?: 1

/**
 * Convert Resource Font ID to Typeface
 *
 * @param context
 * @sample R.font.poppins.idToFont(context)
 * @return Typeface
 */
fun Int.idToFont(context: Context?): Typeface? = context?.let {
    ResourcesCompat.getFont(it, this)
}

/**
 * Convert Resource Layout ID to View, used in Activities
 *
 * @param inflater
 * @param container
 * @param attachToRoot
 * @sample R.font.recyclerview_b.idToLayout(context)
 * @return View - Optional if it fails
 */
fun Int.idToLayout(inflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): View? =
    inflater.inflate(this, container, attachToRoot)

/**
 * Convert Resource Layout ID to View, used in Components
 *
 * @param context
 * @param container
 * @return View -
 */
fun Int.idToLayout(context: Context, container: ViewGroup?): View =
    LayoutInflater.from(context).inflate(this, container)


/**
 * Convert Resource Layout ID to View, used in Fragments
 *
 * @param context
 * @param container
 * @param attachToRoot
 * @return
 */
fun Int.idToLayout(context: Context?, container: ViewGroup?, attachToRoot: Boolean): View =
    LayoutInflater.from(context).inflate(this, container, attachToRoot)

/**
 * Convert Resource Menu into Menu
 *
 * @param menu: Menu where to be inflated
 * @param menuInflater: MenuInflater
 */
fun Int.idToMenu(menuInflater: MenuInflater?, menu: Menu?) =
    menuInflater?.inflate(this, menu)