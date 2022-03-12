package com.lealpy.marvelapp.di

import com.lealpy.marvelapp.data.repositories.TestCharactersRepositoryImpl
import com.lealpy.marvelapp.domain.use_cases.GetCharacterByIdUseCase
import com.lealpy.marvelapp.domain.use_cases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UseCasesModule::class]
)
class TestUseCasesModule {

    @Provides
    fun provideGetCharactersUseCase(
        charactersRepository: TestCharactersRepositoryImpl,
    ): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository = charactersRepository)
    }

    @Provides
    fun provideGetCharacterByIdUseCase(
        charactersRepository: TestCharactersRepositoryImpl,
    ): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCase(charactersRepository = charactersRepository)
    }

}