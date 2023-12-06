package com.sergio.rodriguez.jetpackcomposeinstagram

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sergio.rodriguez.jetpackcomposeinstagram.ui.theme.JetpackComposeInstagramTheme

@Composable
fun LoginScreenOwn(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp)
    ) {

        val (header, body, footer) = createRefs()

        HeaderOwn(
            modifier = Modifier
                .constrainAs(header) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )
        BodyOwn(
            modifier = Modifier
                .constrainAs(body) {
                    start.linkTo(header.start)
                    top.linkTo(header.bottom)
                    end.linkTo(header.end)
                    bottom.linkTo(footer.top)
                    width = Dimension.fillToConstraints
                }
        )
        FooterOwn(
            modifier = Modifier
                .constrainAs(footer) {
                    start.linkTo(body.start)
                    end.linkTo(body.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.percent(2f)
                }
        )

    }
}

@Composable
fun HeaderOwn(
    modifier: Modifier = Modifier
) {
    //OBTENEMOS LA ACTIVIDAD
    val activity: Activity? = (LocalContext.current as? Activity)

    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {

        val refClose = createRef()

        IconButton(
            onClick = {
                activity?.finish()
            },
            modifier = Modifier.constrainAs(refClose) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }
        ) {
            Image(
                imageVector = Icons.Default.Close,
                contentDescription = "Close app",
            )
        }

    }

}

@Composable
fun BodyOwn(
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

    ConstraintLayout(
        modifier = modifier
    ) {

        val (
            imageLogo,
            email,
            password,
            forgotPassword,
            loginButton,
            loginDivider,
            socialLogin,
        ) = createRefs()

        ImageLogoOwn(
            modifier = Modifier
                .constrainAs(imageLogo) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
        )

        EmailOwn(
            emailState = emailState,
            onTextChanged = {
                emailState = it
            },
            modifier = Modifier
                .constrainAs(email) {
                    start.linkTo(imageLogo.start)
                    top.linkTo(imageLogo.bottom, margin = 16.dp)
                    end.linkTo(imageLogo.end)
                    width = Dimension.fillToConstraints
                }
        )

        PasswordOwn(
            passwordState = passwordState,
            onTextChanged = {
                passwordState = it
            },
            modifier = Modifier
                .constrainAs(password) {
                    start.linkTo(email.start)
                    top.linkTo(email.bottom, margin = 16.dp)
                    end.linkTo(email.end)
                    width = Dimension.fillToConstraints
                }
        )

        ForgotPasswordOwn(
            modifier = Modifier
                .constrainAs(forgotPassword) {
                    top.linkTo(password.bottom, margin = 16.dp)
                    end.linkTo(password.end)
                }
        )

        LoginButtonOwn(
            loginEnable = isLogginEnable,
            modifier = Modifier
                .constrainAs(loginButton) {
                    start.linkTo(password.start)
                    top.linkTo(forgotPassword.bottom, margin = 16.dp)
                    end.linkTo(password.end)
                    width = Dimension.fillToConstraints
                }
        )

        LoginDividerOwn(
            modifier = Modifier
                .constrainAs(loginDivider) {
                    start.linkTo(loginButton.start)
                    top.linkTo(loginButton.bottom, margin = 32.dp)
                    end.linkTo(loginButton.end)
                }
        )

        SocialLoginOwn(
            modifier = Modifier
                .constrainAs(socialLogin) {
                    start.linkTo(loginDivider.start)
                    top.linkTo(loginDivider.bottom, margin = 32.dp)
                    end.linkTo(loginDivider.end)
                }
        )


    }
}

@Composable
fun ImageLogoOwn(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.insta),
        contentDescription = "Logo Instagram",
        modifier = modifier
    )
}

@Composable
fun EmailOwn(
    emailState: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = emailState,
        onValueChange = { email: String ->
            onTextChanged(email)
        },
        label = {
            Text(
                text = stringResource(id = R.string.placeholder_email)
            )
        },
        modifier = modifier
    )
}

@Composable
fun PasswordOwn(
    passwordState: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ForgotPasswordOwn(
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
fun LoginButtonOwn(
    loginEnable: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        enabled = loginEnable,
        shape = ShapeDefaults.Small,
        modifier = modifier
    ) {
        Text(
            text = "Log In"
        )
    }
}


@Composable
fun LoginDividerOwn(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
    ) {

        val (
            dividerLeft,
            textOr,
            dividerRight
        ) = createRefs()

        Divider(
            modifier = Modifier
                .constrainAs(dividerLeft) {
                    start.linkTo(parent.start)
                    end.linkTo(textOr.start)
                }
        )
        Text(
            color = Color(0xFFB5B5B5),
            text = "OR",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(textOr) {
                    start.linkTo(dividerLeft.end, margin = 16.dp)
                    end.linkTo(dividerRight.start, margin = 16.dp)
                    top.linkTo(dividerLeft.top)
                    bottom.linkTo(dividerLeft.bottom)
                },
        )
        Divider(
            modifier = Modifier
                .constrainAs(dividerRight) {
                    start.linkTo(textOr.end)
                    end.linkTo(parent.end)
                    top.linkTo(textOr.top)
                    bottom.linkTo(textOr.bottom)
                }
        )


    }
}

//EJEMPLO DE COMBINAR CONSTRAINT LAYOUT CON CAJAS CLASICAS
@Composable
fun SocialLoginOwn(
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
fun FooterOwn(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier.height(60.dp)
    ) {

        val (
            dividerFooter,
            signup
        ) = createRefs()

        Divider(
            modifier = Modifier
                .constrainAs(dividerFooter) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        SignUpOwn(
            modifier = Modifier
                .constrainAs(signup) {
                    start.linkTo(dividerFooter.start)
                    top.linkTo(dividerFooter.bottom, margin = 16.dp)
                    end.linkTo(dividerFooter.end)
                    bottom.linkTo(parent.bottom)
                }
        )

    }
}

@Composable
fun SignUpOwn(
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
    ) {

        val (
            textAccount,
            textSignup
        ) = createRefs()

        createHorizontalChain(
            textAccount,
            textSignup,
            chainStyle = ChainStyle.Packed
        )

        Text(
            color = Color(0xFFB5B5B5),
            text = "Don't have an account?",
            fontSize = 12.sp,
            modifier = Modifier
                .constrainAs(textAccount) {
                    start.linkTo(parent.start)
                    end.linkTo(textSignup.start)
                }
        )
        Text(
            text = "Sign Up.",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color(0xFF4EA8E9),
            modifier = Modifier
                .constrainAs(textSignup) {
                    start.linkTo(textAccount.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = 16.dp)
        )
    }
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
fun LoginScreenOwnPreview() {
    JetpackComposeInstagramTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LoginScreenOwn()
        }
    }
}