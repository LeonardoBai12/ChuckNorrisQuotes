package io.lb.chucknorrisquotes.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import io.lb.chucknorrisquotes.data.remote.HttpRoutes.DEFAULT_ICON

@Composable
fun QuoteScreen(
    state: QuoteState,
    onGenerateClick: () -> Unit
) {
    val quote = state.quote

    val loadingImage = remember {
        mutableStateOf(true)
    }
    Column {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .weight(1f, false),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                painter = rememberAsyncImagePainter(
                    // The icons form this API are broken :/
                    model = /*quote?.iconUrl ?: */DEFAULT_ICON,
                    onSuccess = {
                        loadingImage.value = false
                    },
                    onError = {
                        loadingImage.value = false
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "mealThumb",
            )

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            Button(
                onClick = {
                    onGenerateClick.invoke()
                }
            ) {
                Text(
                    text = "Generate Chuck Norris Legend",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }

            Spacer(
                modifier = Modifier
                    .height(12.dp)
            )

            if (state.isLoading.not())
                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 12.dp,
                    ),
                    text = quote?.quote ?: "",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            else CircularProgressIndicator()
        }
    }
}
