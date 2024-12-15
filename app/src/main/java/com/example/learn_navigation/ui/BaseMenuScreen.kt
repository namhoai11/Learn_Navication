package com.example.learn_navigation.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.learn_navigation.R
import com.example.learn_navigation.data.DataSource
import com.example.learn_navigation.model.MenuItem
import com.example.learn_navigation.model.MenuItem.EntreeItem
import com.example.learn_navigation.ui.theme.AppTheme


@Composable
fun BaseMenuScreen(
    options: List<MenuItem>,
    onSelectionChanged: (MenuItem) -> Unit,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedItemName by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            items(options) { item ->
                val onClick = {
                    selectedItemName = item.name
                    onSelectionChanged(item)
                }
                ItemRow(
                    item = item,
                    onClick = onClick,
                    selectedItemName = selectedItemName
                )
            }
        }
        ButtonGroup(
            selectedItemName = selectedItemName,
            onCancelButtonClicked = onCancelButtonClicked,
            onNextButtonClicked = onNextButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
        )
    }
}

@Composable
fun ItemRow(
    item: MenuItem,
    onClick: () -> Unit,
    selectedItemName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.selectable(
            selected = selectedItemName == item.name,
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selectedItemName == item.name,
            onClick = onClick
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = item.getFormattedPrice(),
                style = MaterialTheme.typography.bodyMedium
            )
            HorizontalDivider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
        }
    }

}

@Composable
fun ButtonGroup(
    selectedItemName: String,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
//        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = onCancelButtonClicked
        ) {
            Text(stringResource(R.string.cancel).uppercase())
        }
        Button(
            modifier = Modifier.weight(1f),
            enabled = selectedItemName.isNotEmpty(),
            onClick = onNextButtonClicked,
        ) {
            Text(stringResource(R.string.next).uppercase())
        }
    }

}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ItemRowPreview() {
    AppTheme {
        ItemRow(
            item = EntreeItem(
                name = "Mushroom Pasta",
                description = "Penne pasta, mushrooms, basil, with plum tomatoes cooked in garlic " +
                        "and olive oil",
                price = 5.50,
            ),
            onClick = {},
            selectedItemName = "Mushroom Pasta"
        )
    }
}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BaseMenuScreenPreview() {
    AppTheme {
        BaseMenuScreen(
            options = DataSource.accompanimentMenuItems,
            {},
            {},
            {}
        )
    }
}
