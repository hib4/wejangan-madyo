package me.hib4.wejanganmadyo.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.hib4.wejanganmadyo.data.manger.LocalUserMangerImpl
import me.hib4.wejanganmadyo.domain.manger.LocalUserManger
import me.hib4.wejanganmadyo.domain.usecases.AppEntryUseCases
import me.hib4.wejanganmadyo.domain.usecases.ReadAppEntry
import me.hib4.wejanganmadyo.domain.usecases.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application,
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger,
    ): AppEntryUseCases = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManger),
        readAppEntry = ReadAppEntry(localUserManger)
    )
}