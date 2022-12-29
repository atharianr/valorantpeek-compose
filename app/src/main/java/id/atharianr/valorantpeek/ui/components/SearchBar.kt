package id.atharianr.valorantpeek.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.atharianr.valorantpeek.R
import id.atharianr.valorantpeek.ui.theme.ValorantPeekJetpackComposeTheme

@Composable
fun SearchBar(
    query: String,
    modifier: Modifier = Modifier,
    onQueryChange: (String) -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search),
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = Color.White.copy(alpha = 0.15f),
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.5f)
            )
        },
        shape = RoundedCornerShape(100.dp),
        singleLine = true,
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    ValorantPeekJetpackComposeTheme {
        SearchBar(
            query = "",
            onQueryChange = {}
        )
    }
}