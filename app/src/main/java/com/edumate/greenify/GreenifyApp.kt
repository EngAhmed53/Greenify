package com.edumate.greenify

import android.app.Application
import com.bumptech.glide.annotation.GlideModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
@GlideModule
class GreenifyApp:Application()