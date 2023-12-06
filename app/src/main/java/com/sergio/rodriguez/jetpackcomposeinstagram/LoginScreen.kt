package com.sergio.rodriguez.jetpackcomposeinstagram

import android.app.Activity
import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergio.rodriguez.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme

@Composable
fun LoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {
        Header(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
        )
        Body(
            modifier = Modifier
                .align(alignment = Alignment.Center)
        )
        Footer(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
        )
    }
}


@Composable
fun Header(
    modifier: Modifier = Modifier
) {
    //OBTENEMOS LA ACTIVIDAD
    val activity: Activity? = (LocalContext.current as? Activity)

    IconButton(
        onClick = {
            activity?.finish()
        },
        modifier = modifier
    ) {
        Image(
            imageVector = Icons.Default.Close,
            contentDescription = "Close app",
        )
    }
}

@Composable
fun Body(
    modifier: Modifier = Modifier
) {

    var emailState: String by rememberSaveable {
        mutableStateOf("")
    }

    var passwordState: String by rememberSaveable {
        mutableStateOf("")
    }

    var isLogginEnable by rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = modifier
    ) {
        ImageLogo(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Email(
            emailState = emailState,
            onTextChanged = { email: String ->
                emailState = email
                isLogginEnable = enabledButtonLogin(emailState, passwordState)
            }
        )
        Spacer(modifier = Modifier.height(4.dp))
        Password(
            passwordState = passwordState,
            onTextChanged = { password: String ->
                passwordState = password
                isLogginEnable = enabledButtonLogin(emailState, passwordState)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPassword(
            modifier = Modifier
                .align(alignment = Alignment.End)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(loginEnable = isLogginEnable)
        Spacer(modifier = Modifier.height(16.dp))
        LoginDivider()
        Spacer(modifier = Modifier.height(32.dp))
        SocialLogin()
    }
}

@Composable
fun ImageLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo Instagram",
        modifier = modifier
    )
}

@Composable
fun Email(
    emailState: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = emailState,
        onValueChange = { email: String ->
            onTextChanged(email)
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder_email)
            )
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFAFAFA),
            unfocusedContainerColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(size = 4.dp)
            )
    )
}

@Composable
fun Password(
    passwordState: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var passwordVisivility: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = passwordState,
        onValueChange = { password: String ->
            onTextChanged(password)
        },
        label = {
            Text(
                text = stringResource(id = R.string.placeholder_password)
            )
        },
        visualTransformation = if (passwordVisivility)
            VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val iconPassword: ImageVector = if (passwordVisivility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(
                onClick = {
                    passwordVisivility = !passwordVisivility
                }
            ) {
                Icon(
                    imageVector = iconPassword,
                    contentDescription = "Icon"
                )
            }
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ForgotPassword(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Forgot password?",
        color = Color(0xFF4EA8E9),
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        modifier = modifier
    )
}

@Composable
fun LoginButton(
    loginEnable: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        enabled = loginEnable,
        shape = ShapeDefaults.ExtraSmall,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4EA8E9),
            disabledContainerColor = Color(0xFF78C8F9),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "Log In"
        )
    }
}

@Composable
fun LoginDivider(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(modifier = Modifier.weight(1f))
        Text(
            color = Color(0xFFB5B5B5),
            text = "OR",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp),
        )
        Divider(modifier = Modifier.weight(1f))
    }
}

@Composable
fun SocialLogin(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painterResource(id = R.drawable.fb),
            contentDescription = "Social login fb",
            modifier = Modifier.size(16.dp)
        )
        Text(
            color = Color(0xFF4EA8E9),
            text = "Continue as S_H_U_R_A",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun Footer(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Divider(
            modifier = Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 24.dp))
        SignUp()
        Spacer(modifier = Modifier.height(height = 24.dp))
    }
}

@Composable
fun SignUp(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            color = Color(0xFFB5B5B5),
            text = "Don't have an account?",
            fontSize = 12.sp,
        )
        Text(
            text = "Sign Up.",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF4EA8E9),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}


fun enabledButtonLogin(
    email: String,
    password: String,
): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
}


@Preview(
    name = "Modo claro",
    showBackground = true,
)
@Preview(
    name = "Modo oscuro",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginScreenPreview() {
    JetpackComposeInstagramTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LoginScreen()
        }
    }
}
