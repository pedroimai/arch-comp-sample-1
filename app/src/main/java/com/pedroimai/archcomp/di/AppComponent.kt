package com.pedroimai.archcomp.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {
    infix fun inject(target: Application)
}