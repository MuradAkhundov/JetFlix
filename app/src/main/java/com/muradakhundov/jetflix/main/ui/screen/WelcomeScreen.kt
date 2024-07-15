package com.muradakhundov.jetflix.main.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.muradakhundov.jetflix.R
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_LOG_IN_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_REGISTER_NAVIGATION
import com.muradakhundov.jetflix.main.ui.theme.PrimaryAccent
import com.muradakhundov.jetflix.main.ui.theme.PrimaryDark


@Composable
fun WelcomeScreen(navController: NavController?) {
    Surface() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.welcome_wallpaper),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.matchParentSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f))
            )


            Card(
                colors = CardDefaults.cardColors(containerColor = PrimaryDark),
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.size(0.dp, 30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.jetflix),
                        contentDescription = null,
                        Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.size(0.dp, 30.dp))
                    Text(
                        text = "Register to start your survey",
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.size(0.dp, 30.dp))
                    Button(
                        onClick = {
                            navController?.navigate(KEY_REGISTER_NAVIGATION)
                        },
                        Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryAccent)
                    ) {
                        Text(text = "Sign Up")
                    }
                    Spacer(modifier = Modifier.size(0.dp, 30.dp))
                    Row {
                        Text(text = "Already have an account?", color = Color.White)
                        ClickableText(text = "Login", color = PrimaryAccent, onClick = {
                            navController?.navigate(
                                KEY_LOG_IN_NAVIGATION
                            )
                        })
                    }
                    Spacer(modifier = Modifier.size(0.dp, 30.dp))
                }
            }


        }
    }


}

@Composable
fun ClickableText(
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = color,
        modifier = Modifier
            .padding(start = 5.dp)
            .clickable { onClick() }
    )
}


@Preview
@Composable
fun WelcomePreview() {
    WelcomeScreen(null)
}