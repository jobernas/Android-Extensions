package eu.jobernas.demo

import android.app.Application
import android.content.res.Configuration
import eu.jobernas.androidextensions.ExtensionsWrapper

class DemoApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Setup Extensions Module
        ExtensionsWrapper.init(applicationContext)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        ExtensionsWrapper.onConfigChanged(newConfig)
    }
}