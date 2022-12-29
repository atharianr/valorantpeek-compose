package id.atharianr.valorantpeek.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import id.atharianr.valorantpeek.ui.theme.ValorantPeekJetpackComposeTheme

@Composable
fun AgentItem(
    image: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberAsyncImagePainter(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(1000.dp, 128.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .background(Color.DarkGray)
        )
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp, 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AgentItemPreview() {
    ValorantPeekJetpackComposeTheme {
        AgentItem(
            "https://cdn.vox-cdn.com/thumbor/-m50mL5PlNDtsT4DEHGyNZ2avMM=/440x0:2894x2113/1200x800/filters:focal(829x0:1443x614)/cdn.vox-cdn.com/uploads/chorus_image/image/68885520/Astra_Wallpapers_Blue1.0.jpg",
            "Astra"
        )
    }
}