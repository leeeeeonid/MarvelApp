package com.lealpy.marvelapp.di

import com.lealpy.marvelapp.data.repositories.CharactersRepositoryImpl
import com.lealpy.marvelapp.domain.use_cases.GetCharacterByIdUseCase
import com.lealpy.marvelapp.domain.use_cases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideGetCharactersUseCase(
        charactersRepository: CharactersRepositoryImpl,
    ): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository = charactersRepository)
    }

    @Provides
    fun provideGetCharacterByIdUseCase(
        charactersRepository: CharactersRepositoryImpl,
    ): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCase(charactersRepository = charactersRepository)
    }

}
