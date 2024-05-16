package com.muradakhundov.jetflix.authentication.presentation.auth

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.muradakhundov.jetflix.authentication.data.query.AuthQuery
import com.muradakhundov.jetflix.authentication.ui.viewmodel.AuthAction
import com.muradakhundov.jetflix.authentication.ui.viewmodel.AuthViewModel
import com.muradakhundov.jetflix.util.Constants.Companion.homeKey
import com.muradakhundov.jetflix.util.Constants.Companion.registrationKey
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryAccent
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryDark
import com.muradakhundov.jetflix.movie.ui.theme.PrimarySoft
import com.muradakhundov.jetflix.util.Constants.Companion.loginKey


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(navController: NavController?, viewModel: AuthViewModel = hiltViewModel()) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }
    val uiStateFlow = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current


//    LaunchedEffect(uiStateFlow) {
//           if (uiStateFlow.value.isLoading) {
//                // Show loading indicator
//                CircularProgressIndicator(
//                    modifier = Modifier
//                        .size(50.dp)
//                        .align(Alignment.Center)
//                )
//            }
//            if (uiState.valerror != null) {
//                // Show error message in snackbar
//                scaffoldState.snackbarHostState.showSnackbar(uiState.error)
//            }
//            if (uiState.success) {
//                // Navigate to home screen
//                navController?.navigate(homeKey)
//            }
//    }

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
                            "Sign Up",
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
                    Text(text = "Let’s get started", color = Color.White, fontSize = 28.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "The latest movies and series \n are here",
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Full Name", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedPlaceholderColor = Color.Gray,
                            unfocusedPlaceholderColor = Color.Gray,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            disabledTextColor = Color.White,
                            disabledSupportingTextColor = Color.White,
                            errorTextColor = Color.White,
                            errorSupportingTextColor = Color.White,
                            focusedSupportingTextColor = Color.White,
                            unfocusedSupportingTextColor = Color.White
                        ),
                        shape = CircleShape,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
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

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {
                        Checkbox(
                            colors = CheckboxDefaults.colors(checkedColor = PrimaryAccent),
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it }
                        )
                        Text(
                            text = "I agree to the Terms and Services \nand Privacy Policy.",
                            color = Color.White,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {

                            if (fullName.isNotEmpty() || password.isNotEmpty() || email.isNotEmpty()) {
                                viewModel.setAction(
                                    AuthAction.RegisterAction(
                                        AuthQuery.RegistrationQuery(
                                            username = fullName,
                                            password = password,
                                            email = email.trim()
                                        )
                                    )
                                )
                            }
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
                        Text(text = "Already have an account?", color = Color.White)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Sign in.",
                            color = PrimaryAccent,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                navController?.navigate(loginKey)
                            })
                    }
                }
            }
        )
    }
}

@Composable
fun ProgressDialog() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun ShowMessage(message: String) {
    AlertDialog(
        onDismissRequest = { },
        title = {
            Text(
                text = "Error",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Red
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        },
        confirmButton = {
            Button(onClick = { /* Dismiss action if needed */ }) {
                Text("OK", color = Color.White)
            }
        }
    )
}

@Preview
@Composable
fun LoginPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = PrimaryDark
    ) {
        LoginScreen(null)
    }
}