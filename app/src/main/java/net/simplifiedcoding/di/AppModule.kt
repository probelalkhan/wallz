package net.simplifiedcoding.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.simplifiedcoding.data.db.WallzDatabase
import net.simplifiedcoding.data.network.RemoteDataSource
import net.simplifiedcoding.data.network.WallzApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWallzDatabase(
        @ApplicationContext context: Context
    ): WallzDatabase {
        return WallzDatabase(context)
    }

    @Singleton
    @Provides
    fun provideWallzApi(
        remoteDataSource: RemoteDataSource
    ): WallzApi {
        return remoteDataSource.buildApi(WallzApi::class.java)
    }
}