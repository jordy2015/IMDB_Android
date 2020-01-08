package com.example.imbdapp.application

import android.app.Application
import com.example.imbdapp.services.NetworkModule
import com.example.imbdapp.di.ViewModule
import dagger.Component


class MasterApp: Application() {
    override fun onCreate() {
        super.onCreate()
        rootFactory = DaggerMasterComponent.builder().appModule(AppModule(this)).build()
    }

    companion object{
        lateinit var rootFactory:MasterComponent
    }
}

@Component(modules = [AppModule::class, ViewModule::class, NetworkModule::class])
interface MasterComponent{
    fun getHomeComponent(): com.example.imbdapp.di.Component

    @Component.Builder
    interface Builder {
        fun build(): MasterComponent
        fun appModule(appModule: AppModule): Builder
    }
}