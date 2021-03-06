package com.redpond.user.repository

import com.redpond.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Singleton
    @Binds
    internal abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
