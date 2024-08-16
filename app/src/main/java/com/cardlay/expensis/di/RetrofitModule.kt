package com.cardlay.expensis.di

import com.cardlay.expensis.network.CardsApi
import com.cardlay.expensis.network.CardsService
import com.cardlay.expensis.network.GetCardsUseCase
import com.cardlay.expensis.repository.CardRepository
import com.cardlay.expensis.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

    @Provides
    @ViewModelScoped
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Cards
    @Provides
    @ViewModelScoped
    fun provideCardsApi(retrofit: Retrofit): CardsApi {
        return retrofit.create(CardsApi::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideCardsService(cardsApi: CardsApi): CardsService {
        return CardsService(cardsApi)
    }

    @Provides
    @ViewModelScoped
    fun provideCardsRepository(cardsService: CardsService): CardRepository {
        return CardRepository(cardsService)
    }

    @Provides
    @ViewModelScoped
    fun provideCardsUseCase(cardsRepository: CardRepository): GetCardsUseCase {
        return GetCardsUseCase(cardsRepository)
    }




}
