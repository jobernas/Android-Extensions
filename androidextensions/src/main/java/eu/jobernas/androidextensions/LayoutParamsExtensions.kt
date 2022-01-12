package eu.jobernas.androidextensions

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Tries to cast layoutParams as ConstraintLayout.LayoutParams
 */
val ViewGroup.LayoutParams.asConstraintLayoutParams: ConstraintLayout.LayoutParams?
    get() = this as? ConstraintLayout.LayoutParams

/**
 * Tries to cast layoutParams as RelativeLayout.LayoutParams
 */
val ViewGroup.LayoutParams.asRelativeLayoutParams: RelativeLayout.LayoutParams?
    get() = this as? RelativeLayout.LayoutParams

/**
 * Tries to cast layoutParams as linear layout layout params
 */
val ViewGroup.LayoutParams.asLinearLayoutLayoutParams: LinearLayout.LayoutParams?
    get() = this as? LinearLayout.LayoutParams