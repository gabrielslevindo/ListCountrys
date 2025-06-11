package com.example.listcountrys.di

import android.content.Context
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.example.listcountrys.data.local.AppDataBase
import com.example.listcountrys.data.remote.ApolloCountryClient
import com.example.listcountrys.domain.CountryClient
import com.example.listcountrys.domain.GetCountriesUseCase
import com.example.listcountrys.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@InstallIn(SingletonComponent::class)
@Module
object DataBaseHiltProvider {
    @Singleton
    @Provides
    fun dataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        "Country-DataBase"
    ).build()

}

@InstallIn(SingletonComponent::class)
@Module
object HiltProviders {
    @Singleton
    @Provides
    fun providesDao(dataBase: AppDataBase) = dataBase.countryDao()

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext = Dispatchers.IO

//aqui configuro o ApolloClient com a URL do servidor GraphQL
    @Provides
    @Singleton
    fun apolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun countryClient(apolloCountryClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient = apolloCountryClient)
    }

    @Provides
    @Singleton
    fun getCountryUseCase(countryClient: CountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient = countryClient)
    }

    @Provides
    @Singleton
    fun getCountriesUseCase(countryClient: CountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient = countryClient)
    }
}
