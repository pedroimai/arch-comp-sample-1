package com.pedroimai.archcomp.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Module
class CoreModule(val app: Application) {
    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext
}