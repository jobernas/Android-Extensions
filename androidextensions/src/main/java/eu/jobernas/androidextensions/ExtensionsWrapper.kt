package eu.jobernas.androidextensions

import android.content.Context
import android.util.Log

/**
 * Extensions wrapper
 *
 * @property density
 * @property fontDensity
 * @constructor Create empty Extensions wrapper
 */
class ExtensionsWrapper private constructor(
    private var density: Float = 1f,
    private var fontDensity: Float = 1f) {

    // Not needed for now
//    class Builder {
//
//        fun build(context: Context?): ExtensionsWrapper {
//            val density = context?.resources?.displayMetrics?.scaledDensity ?: 1f
//            val fontDensity = context?.resources?.displayMetrics?.scaledDensity ?: 1f
//            return ExtensionsWrapper(density, fontDensity)
//        }
//    }

    /**
     * Static Methods
     */
    companion object {

        // Const's
        private const val TAG = "EXTENSIONS_WRAPPER"

        lateinit var instance: ExtensionsWrapper

        @Synchronized
        fun init(context: Context?) {
            if (Companion::instance.isInitialized) {
                Log.w(TAG, "$TAG was already created.")
                return
            }
            val density = context?.resources?.displayMetrics?.density ?: 1f
            val fontDensity = context?.resources?.displayMetrics?.scaledDensity ?: 1f
            instance = ExtensionsWrapper(density, fontDensity)
        }

        val density: Float
            get() = instance.density

        val fontDensity: Float
            get() = instance.fontDensity

        /**
         * On Config Changed update Global Wrapper Configurations
         *
         * @param context
         */
        fun onConfigChanged(context: Context?) {
            instance.updateDensities(context)
        }
    }

    /**
     * Private Methods
     **/
    private fun updateDensities(context: Context?) {
        density = context?.resources?.displayMetrics?.density ?: 1f
        fontDensity = context?.resources?.displayMetrics?.scaledDensity ?: 1f
    }
}