package com.example.learn_navigation.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.learn_navigation.R
import com.example.learn_navigation.data.DataSource
import com.example.learn_navigation.model.MenuItem
import com.example.learn_navigation.model.OrderUiState
import com.example.learn_navigation.ui.theme.AppTheme


@Composable
fun CheckoutScreen(
    orderUiState: OrderUiState,
    onSubmitButtonClicked: () -> Unit,
    onCancelButtonCliked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val itemListSummary = listOf(
        orderUiState.entree,
        orderUiState.sideDish,
        orderUiState.accompaniment
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
    ) {
        Text(
            text = stringResource(id = R.string.order_summary),
            fontWeight = FontWeight.Bold
        )
        LazyColumn(
            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            items(itemListSummary) { item ->
                ItemSummary(
                    item = item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        HorizontalDivider(
            thickness = dimensionResource(R.dimen.thickness_divider),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        Text(
            text = stringResource(
                id = R.string.subtotal,
                orderUiState.itemTotalPrice.formatPrice()
            ),
            modifier = Modifier
                .align(Alignment.End)
        )
        Text(
            text = stringResource(
                id = R.string.tax,
                orderUiState.orderTax.formatPrice()
            ),
            modifier = Modifier
                .align(Alignment.End)
        )
        Text(
            text = stringResource(
                R.string.total,
                orderUiState.orderTotalPrice.formatPrice()
            ),
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.weight(1f))
        ButtonCheckoutGr(
            onCancelButtonClicked = onCancelButtonCliked,
            onSubmitButtonClicked = onSubmitButtonClicked,
        )
    }
}

@Composable
fun ItemSummary(
    item: MenuItem?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(item?.name ?: "")
        Text(item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun ButtonCheckoutGr(
    onCancelButtonClicked: () -> Unit,
    onSubmitButtonClicked: () -> Unit,
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
            onClick = onSubmitButtonClicked,
        ) {
            Text(stringResource(R.string.submit).uppercase())
        }
    }

}

@Preview("Light Theme", showBackground = true)
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CheckoutScreenPreview() {
    AppTheme {
        CheckoutScreen(
            orderUiState = OrderUiState(
                entree = DataSource.entreeMenuItems[0],
                sideDish = DataSource.sideDishMenuItems[0],
                accompaniment = DataSource.accompanimentMenuItems[0],
                itemTotalPrice = 15.00,
                orderTax = 0.84,
                orderTotalPrice = 16.00
            ),
            {},
            {},
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_medium))
//                .fillMaxHeight()
        )
    }
}