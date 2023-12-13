/*
* Created by black_null on Wednesday 06 Dec 2023 at 5:39 PM 
* Achraf Herradi - Achrafherradi10@gmail.com
* https://github.com/black-null
*/

package com.black_null.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.black_null.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpace()
                }
            }
        }
    }
}

// Exposing the drawable resource for UI testing !!
val DrawableIdKey = SemanticsPropertyKey<@receiver:DrawableRes Int>("DrawableId")
val StringIdKey = SemanticsPropertyKey<@receiver:StringRes Int>("StringId")

private var SemanticsPropertyReceiver.drawableId by DrawableIdKey
private var SemanticsPropertyReceiver.stringId by StringIdKey

@Composable
fun ArtSpace() {
    val images = listOf<@receiver:DrawableRes Int>(
        R.drawable.sky_cloud_1080_2400,
        R.drawable.bright_landscape_1080_2400,
        R.drawable.street_kyoto_japan_1080_2400,
    )

    val titles = listOf<@receiver:StringRes Int>(
        R.string.evening_sky_clouds,
        R.string.bright_pop_landscape_design,
        R.string.street_tokyo_japan,
    )

    var i by remember { mutableStateOf(0) }
    @DrawableRes val imageId = images[i]
    @StringRes val titleId = titles[i]

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight(0.6f)
        ) {
            ArtworkWall(imageId)
        }

        Column(
            modifier = Modifier
                .padding(top = 100.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArtworkDescriptor(titleId)
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            ArtworkController(
                onClickNext = { when(i) {0, 1 -> i++; 2 -> i = 0 } },
                onClickPrevious = { when(i) {0 -> i = 2; 2, 1 -> i-- } },
            )
        }
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes resource: Int,
    modifier: Modifier = Modifier,
) {

    Image(
        painter = painterResource(resource),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .semantics { drawableId = resource }
    )
}

@Composable
fun ArtworkDescriptor(
    @StringRes stringRes: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp),
    ) {
        Text(
            text = stringResource(stringRes),
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.semantics { stringId = stringRes }
        )
        Text(
            text = stringResource(R.string.artwork_artist_year),
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun ArtworkController(
    onClickNext: () -> Unit,
    onClickPrevious: () -> Unit,
) {
    Row (
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ){
        Button(
            onClick = onClickPrevious,
            modifier = Modifier
                .width(120.dp)
                .testTag("buttonPrevious")

        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = onClickNext ,
            modifier = Modifier
                .width(120.dp)
                .testTag("buttonNext"),
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpace()
    }
}
