package com.example.learn_navigation.ui

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.learn_navigation.data.DataSource
import com.example.learn_navigation.model.MenuItem.EntreeItem
import com.example.learn_navigation.ui.theme.AppTheme


@Composable
fun EntreeMenuScreen(
    options: List<EntreeItem>,
    onSelectionChanged: (EntreeItem) -> Unit,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onSelectionChanged = { item ->
            onSelectionChanged(item as EntreeItem)
        },
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        modifier = modifier
    )
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EntreeMenuScreenPreview() {
    AppTheme {
        EntreeMenuScreen(
            options = DataSource.entreeMenuItems,
            {},
            {},
            {}
        )
    }
}