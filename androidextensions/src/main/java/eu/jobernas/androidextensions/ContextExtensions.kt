package eu.jobernas.androidextensions

import android.content.Context
import android.view.LayoutInflater

/**
 * Layout inflater
 * @property layoutInflater - for given context
 */
val Context.layoutInflater: LayoutInflater
        get() = LayoutInflater.from(this)