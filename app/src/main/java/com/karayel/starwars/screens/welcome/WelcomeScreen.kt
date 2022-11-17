package com.karayel.starwars.screens.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karayel.starwars.R
import com.karayel.starwars.core.MovieList

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val nameState = rememberSaveable { mutableStateOf("") }
    val buttonState by remember { derivedStateOf { nameState.value.isNotBlank() } }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        WelcomeText(
            modifier = modifier,
            textRes = stringResource(id = R.string.welcome_message)
        )
        WelcomeTextLabel(nameState)
        WelcomeButton(isEnabled = buttonState) {
            navController.navigate(MovieList(nameState.value).route)
        }
    }
}

@Composable
fun WelcomeText(
    modifier: Modifier,
    textRes: String,
) {
    Text(
        modifier = modifier,
        text = textRes,
        color = Color.DarkGray,
        fontSize = 18.sp
    )
}

@Composable
fun WelcomeTextLabel(nameState: MutableState<String>) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = nameState.value,
        label = {
            Text(text = stringResource(id = R.string.welcome_label_name))
        },
        onValueChange = { name -> nameState.value = name },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.LightGray,
            cursorColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.overline
    )
}

@Composable
fun WelcomeButton(isEnabled: Boolean, onButtonClicked: () -> Unit) {
    Button(
        modifier = Modifier.height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.teal_700),
            disabledBackgroundColor = Color.DarkGray
        ),
        onClick = { onButtonClicked() },
        shape = RoundedCornerShape(8.dp),
        enabled = isEnabled
    ) {
        Text(
            text = stringResource(id = R.string.welcome_button_label_name),
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun WelcomeTextPreview() {
    WelcomeText(modifier = Modifier, textRes = "DENEME")
}
