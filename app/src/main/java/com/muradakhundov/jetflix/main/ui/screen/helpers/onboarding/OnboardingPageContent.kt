package com.muradakhundov.jetflix.main.ui.screen.helpers.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muradakhundov.jetflix.R

@Composable
fun PageContent(page: Int) {
    val imageResIds =
        listOf(R.drawable.onboarding_3, R.drawable.onboarding_2, R.drawable.onboarding_1)

    val textList = listOf(
        "First ipsum dolor sit amet consecteur esplicit",
        "Second ipsum dolor sit amet consecteur esplicit",
        "Third ipsum dolor sit amet consecteur esplicit"
    )

    val textDescriptionList =
        listOf(
            "First Text in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient.",
            "Second Text in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient.",
            "Third Text in cursus magna et eu varius nunc adipiscing. Elementum justo, laoreet id sem semper parturient."
        )


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResIds[page]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(421.dp),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = textList[page],
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 20.dp, start = 30.dp, end = 30.dp)
        )

        Text(
            text = textDescriptionList[page], style = TextStyle(
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(top = 20.dp, start = 55.dp, end = 55.dp)
        )
    }

}

