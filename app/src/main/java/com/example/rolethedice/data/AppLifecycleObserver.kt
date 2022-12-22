package com.example.rolethedice.data

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class AppLifecycleObserver : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        Log.i(LOG_TAG, "on create")
        super.onCreate(owner)
    }

    override fun onStart(owner: LifecycleOwner) {
        Log.i(LOG_TAG, "on start")
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        Log.i(LOG_TAG, "on resume")
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
        Log.i(LOG_TAG, "on pause")
        super.onPause(owner)
    }

    override fun onStop(owner: LifecycleOwner) {
        Log.i(LOG_TAG, "on stop")
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Log.i(LOG_TAG, "on destroy")
        super.onDestroy(owner)
    }
}