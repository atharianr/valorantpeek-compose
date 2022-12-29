package id.atharianr.valorantpeek.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.atharianr.valorantpeek.R
import id.atharianr.valorantpeek.ui.theme.Blue172336
import id.atharianr.valorantpeek.ui.theme.RedDC3D4B
import id.atharianr.valorantpeek.ui.theme.ValorantPeekJetpackComposeTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    ProfileContent(modifier = modifier)
}

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Blue172336),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.fotorian),
            contentDescription = stringResource(id = R.string.profile_picture),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(256.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(2.dp, RedDC3D4B, RoundedCornerShape(10.dp))
        )
        Text(
            text = stringResource(id = R.string.profile_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = stringResource(id = R.string.profile_email),
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ValorantPeekJetpackComposeTheme {
        ProfileScreen()
    }
}
