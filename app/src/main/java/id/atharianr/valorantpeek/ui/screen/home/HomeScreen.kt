package id.atharianr.valorantpeek.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.atharianr.valorantpeek.di.Injection
import id.atharianr.valorantpeek.model.Agent
import id.atharianr.valorantpeek.ui.ViewModelFactory
import id.atharianr.valorantpeek.ui.common.UiState
import id.atharianr.valorantpeek.ui.components.AgentItem
import id.atharianr.valorantpeek.ui.components.SearchBar
import id.atharianr.valorantpeek.ui.theme.Blue172336

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllAgents()
            }
            is UiState.Success -> {
                HomeContent(
                    agentList = uiState.data,
                    navigateToDetail = navigateToDetail,
                    query = query,
                    onQueryChange = viewModel::getQueryAgents,
                    modifier = modifier
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    agentList: List<Agent>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    query: String,
    onQueryChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(Blue172336)
            .fillMaxSize()
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(agentList) { data ->
                AgentItem(
                    image = data.image,
                    name = data.name,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }
        }
    }
}