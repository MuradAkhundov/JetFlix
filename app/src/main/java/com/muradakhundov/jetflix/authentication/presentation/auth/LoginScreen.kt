package com.muradakhundov.jetflix.authentication.presentation.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.muradakhundov.jetflix.authentication.ui.viewmodel.AuthAction
import com.muradakhundov.jetflix.authentication.ui.viewmodel.AuthViewModel
import com.muradakhundov.jetflix.util.Constants.Companion.homeKey
import com.muradakhundov.jetflix.util.Constants.Companion.loginKey
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryAccent
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryDark
import com.muradakhundov.jetflix.movie.ui.theme.PrimarySoft
import com.muradakhundov.jetflix.util.Constants.Companion.registrationKey

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController?, viewModel: AuthViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryDark),
        color = PrimaryDark,

        ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = PrimaryDark,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = PrimaryDark,
                        titleContentColor = Color.White,
                    ),
                    title = {
                        Text(
                            "Sign In",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Back",
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .background(PrimarySoft, RoundedCornerShape(8.dp))
                                .padding(6.dp)
                                .size(24.dp)
                                .clickable {
                                    navController?.popBackStack()
                                },
                            tint = Color.White
                        )

                    }
                )
            },
            content =
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Hello again!", color = Color.White, fontSize = 28.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Welcome back! Please enter \nyour details.",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(45.dp))
                    OutlinedTextField(
                        value = email,
                        textStyle = TextStyle(color = Color.White),
                        onValueChange = { email = it },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedPlaceholderColor = Color.Gray,
                            unfocusedPlaceholderColor = Color.Gray,
                        ),
                        label = { Text("Email", color = Color.Gray) },
                        shape = CircleShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password", color = Color.Gray) },
                        textStyle = TextStyle(color = Color.White),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedPlaceholderColor = Color.Gray,
                            unfocusedPlaceholderColor = Color.Gray,
                        ),
                        shape = CircleShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { passwordVisible = !passwordVisible }
                            ) {
                                val icon: ImageVector = if (passwordVisible) {
                                    Icons.Filled.ThumbUp
                                } else {
                                    Icons.Filled.AccountBox
                                }
                                Icon(icon, contentDescription = "Toggle password visibility")
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {
                            viewModel.setAction(
                                AuthAction.LoginAction(
                                    email = email.trim(),
                                    password = password
                                )
                            )
                            navController?.navigate(homeKey)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(horizontal = 20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryAccent)
                    ) {
                        Text(text = "Sign Up")
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Row() {
                        Text(text = "Don't have an account?", color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Sign up.",
                            color = PrimaryAccent,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController?.navigate(registrationKey) {
                                    //TODO Murad remember to add stopping to go back button
//                                popUpTo(loginKey){
//                                    inclusive = true
//                                }
                                }
                            })
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun RegistrationPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PrimaryDark
    ) {
//        RegistrationScreen(null)
    }
}