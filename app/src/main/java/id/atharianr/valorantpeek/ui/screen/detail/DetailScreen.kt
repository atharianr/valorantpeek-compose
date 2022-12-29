package id.atharianr.valorantpeek.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import id.atharianr.valorantpeek.R
import id.atharianr.valorantpeek.di.Injection
import id.atharianr.valorantpeek.model.Agent
import id.atharianr.valorantpeek.ui.ViewModelFactory
import id.atharianr.valorantpeek.ui.common.UiState
import id.atharianr.valorantpeek.ui.theme.Blue172336
import id.atharianr.valorantpeek.ui.theme.RedDC3D4B
import id.atharianr.valorantpeek.ui.theme.ValorantPeekJetpackComposeTheme

@Composable
fun DetailScreen(
    agentId: Long,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAgentById(agentId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    modifier = modifier
                        .background(Blue172336),
                    data = data,
                    onBackClick = navigateBack,
                    onFavoriteClick = { isFavorite ->
                        viewModel.updateAgentById(data.id, isFavorite)
                    },
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: Agent,
    onBackClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit
) {
    var isFavorite by rememberSaveable { mutableStateOf(data.isFavorite) }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(data.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(1000.dp, 256.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(Color.DarkGray)
            )
            Text(
                text = data.name,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp, 16.dp)
            )
            Row(
                modifier = Modifier
                    .clickable { onBackClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                )
                Text(
                    text = stringResource(id = R.string.back),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            Icon(
                imageVector =
                if (isFavorite) {
                    Icons.Outlined.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                tint = if (isFavorite) {
                    RedDC3D4B
                } else {
                    Color.White
                },
                contentDescription = stringResource(id = R.string.favorite),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clickable {
                        isFavorite = !isFavorite
                        onFavoriteClick(isFavorite)
                    }
            )
        }
        Text(
            text = stringResource(R.string.description),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 32.dp)
        )
        Text(
            text = data.description,
            color = Color.Gray,
            modifier = Modifier
                .padding(
                    horizontal = 32.dp,
                    vertical = 8.dp
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    ValorantPeekJetpackComposeTheme {
        DetailContent(
            data = Agent(
                9,
                "https://s.yimg.com/os/creatr-uploaded-images/2021-06/420ce0e0-cf47-11eb-bdff-08e4ec3d7dac",
                "KAY/O",
                "",
                false
            ),
            onBackClick = {},
            onFavoriteClick = {}
        )
    }
}