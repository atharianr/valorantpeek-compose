package id.atharianr.valorantpeek.ui.screen.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.atharianr.valorantpeek.R
import id.atharianr.valorantpeek.di.Injection
import id.atharianr.valorantpeek.model.Agent
import id.atharianr.valorantpeek.ui.ViewModelFactory
import id.atharianr.valorantpeek.ui.common.UiState
import id.atharianr.valorantpeek.ui.components.AgentItem
import id.atharianr.valorantpeek.ui.theme.Blue172336

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteAgents()
            }
            is UiState.Success -> {
                FavoriteContent(
                    agentList = uiState.data,
                    navigateToDetail = navigateToDetail,
                    modifier = modifier
                        .background(Blue172336)
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    modifier: Modifier = Modifier,
    agentList: List<Agent>,
    navigateToDetail: (Long) -> Unit
) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
        ) {
            Text(
                text = stringResource(R.string.favorite_agents),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            )
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 8.dp,
                    top = 8.dp
                ),
                modifier = modifier
                    .fillMaxSize()
            ) {

                items(agentList, key = { it.id }) { agent ->
                    AgentItem(
                        image = agent.image,
                        name = agent.name,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .clickable { navigateToDetail(agent.id) }
                    )
                }
            }
        }

        if (agentList.isEmpty()) {
            Column(
                modifier = modifier
                    .align(Alignment.Center)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(id = R.string.search),
                    tint = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(64.dp)

                )
                Text(
                    text = "There is no favorite agent yet :(",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}