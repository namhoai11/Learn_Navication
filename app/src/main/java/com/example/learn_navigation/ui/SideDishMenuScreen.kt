package com.example.learn_navigation.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learn_navigation.data.DataSource
import com.example.learn_navigation.model.MenuItem.SideDishItem
import com.example.learn_navigation.ui.theme.AppTheme


@Composable
fun SideDishMenuScreen(
    options: List<SideDishItem>,
    onSelectionChanged: (SideDishItem) -> Unit,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onSelectionChanged = { item ->
            onSelectionChanged(item as SideDishItem)
        },
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        modifier = modifier
    )
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SideDishMenuScreenPreview() {
    AppTheme {
        SideDishMenuScreen(
            options = DataSource.sideDishMenuItems,
            {},
            {},
            {}
        )
    }
}