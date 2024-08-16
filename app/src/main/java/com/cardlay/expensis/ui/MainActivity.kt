package com.cardlay.expensis.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cardlay.expensis.R
import com.cardlay.expensis.R.drawable.card_logo
import com.cardlay.expensis.model.CardModel
import com.cardlay.expensis.ui.theme.ExLightGrey
import com.cardlay.expensis.ui.theme.ExpensisTheme
import com.cardlay.expensis.ui.theme.GreenBalance
import com.cardlay.expensis.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val cardViewModel: CardViewModel by viewModels()
        setContent {
            ExpensisTheme {
                MyApp(cardViewModel = cardViewModel)
            }
        }
    }
}

@Composable
fun MyApp(cardViewModel: CardViewModel = hiltViewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Welcome(
            modifier = Modifier.padding(innerPadding),
            cardViewModel = cardViewModel
        )
    }
}


@Composable
private fun Card(modifier: Modifier = Modifier, card: CardModel) {
    Box(modifier = Modifier
        .height(80.dp)
        .width(327.dp)
    ) {
        //background
        Image(painter = painterResource(id = R.drawable.card_background)
            , contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(80.dp)
                .width(327.dp)
            , contentDescription = "")

        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //logo
            Image(painter = painterResource(id = card_logo),
                contentDescription = null,
                modifier = Modifier.weight(1f))
            Column(modifier = Modifier.weight(2f)
            ) {
                Text("xxxx" + card.pan.takeLast(4), color = Color.White)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(id = R.drawable.card_mastercard_logo), contentDescription = "" )
                    Text(text = card.name, color = ExLightGrey)
                }
            }
            Text(text = card.currency + card.available,
                modifier = Modifier.weight(2f),
                color = GreenBalance)
        }
        LinearProgressIndicator(
            progress = { card.available.toFloat()/card.limit.toFloat() },
            color = GreenBalance,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    val tempCard = CardModel(
        available = 105.90,
        currency= "USD",
        id= "",
        limit= 500,
        name= "Test",
        pan= "1234"
    )
    Card(modifier = Modifier, tempCard)
}

@Composable
private fun Welcome(modifier: Modifier = Modifier, cardViewModel: CardViewModel = hiltViewModel() ) {

    val cards by cardViewModel.cards.collectAsState()
    Column {
        Text(
            text = "Welcome!",
            modifier = modifier
        )
        Button(onClick = { cardViewModel.onRefreshCardsEvent() }) {
            Text(text = "${cards.size} Cards - refresh")
        }
        LazyColumn {
            items(cards) { cardModel: CardModel -> Card(modifier, cardModel) }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    ExpensisTheme {
        Welcome()
    }
}




