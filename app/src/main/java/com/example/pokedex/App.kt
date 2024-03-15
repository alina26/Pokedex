package com.example.pokedex

import android.app.Application
import com.example.pokedex.di.DaggerMainScreenComponent
import com.example.pokedex.di.MainScreenComponent

class App : Application() {
    lateinit var mainScreenComponent: MainScreenComponent
    override fun onCreate() {
        super.onCreate()
        mainScreenComponent = DaggerMainScreenComponent.create()
    }
}