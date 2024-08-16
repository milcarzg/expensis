package com.cardlay.expensis.network

import com.cardlay.expensis.model.CardModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface CardsApi {

    @Headers("X-Access-Key:\$2a\$10\$/xCFTGtLDGVE4lvlqrw5nO8jH47.ZKMsHymFl9Y3tVSOnaEjEiKI")
    @GET("65943ceadc746540188c0898?meta=false")
    suspend fun getCards(): Response<List<CardModel>>
}


