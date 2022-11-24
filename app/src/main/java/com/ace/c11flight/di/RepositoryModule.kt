package com.ace.whatmovie.di

import android.content.Context
import com.ace.c11flight.data.local.user.AccountDataSource
import com.ace.c11flight.data.model.AccountDataStoreManager
import com.ace.c11flight.data.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

//    @ViewModelScoped
//    @Provides
//    fun provideRepository(apiHelper: ApiHelper) = MoviesRepository(apiHelper)

    @ViewModelScoped
    @Provides
    fun provideDataSource(accountDataSource: AccountDataSource, prefs: AccountDataStoreManager) =
        LocalRepository(accountDataSource, prefs)


    @ViewModelScoped
    @Provides
    fun provideContext(@ApplicationContext context: Context) = AccountDataStoreManager(context)
}