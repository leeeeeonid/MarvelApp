package com.lealpy.marvelapp.di

import com.lealpy.marvelapp.data.repositories.TestCharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoriesModule::class]
)
class TestRepositoriesModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(): TestCharactersRepositoryImpl {
        return TestCharactersRepositoryImpl()
    }

}