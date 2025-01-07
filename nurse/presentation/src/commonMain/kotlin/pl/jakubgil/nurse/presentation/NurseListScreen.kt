package pl.jakubgil.nurse.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nurseshiftcalculator.nurse.presentation.generated.resources.Res
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_add
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_delete
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NurseListScreen(
    viewModel: NurseListViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    NurseListScreen(
        uiState = uiState,
        onCreateNurseClick = {
            // TODO
        },
        onDeleteNurseClick = viewModel::deleteNurse,
        modifier = Modifier
            .fillMaxSize(),
    )
}

@Composable
private fun NurseListScreen(
    uiState: NurseListUiState,
    onCreateNurseClick: () -> Unit,
    onDeleteNurseClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.padding(vertical = 16.dp)) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(uiState.nurses) { index, nurse ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Text(text = "${index + 1}")
                        Text(text = "${nurse.name} ${nurse.surname}")
                        Icon(
                            painter = painterResource(Res.drawable.nurse_delete),
                            contentDescription = "Delete nurse",
                            modifier = Modifier
                                .clickable {
                                    onDeleteNurseClick.invoke(nurse.id)
                                },
                        )
                    }
                    Divider()
                }
            }
        }

        FloatingActionButton(
            onClick = onCreateNurseClick,
        ) {
            Icon(
                painter = painterResource(Res.drawable.nurse_add),
                contentDescription = "Add nurse",
            )
        }
    }
}

@Composable
@Preview
private fun NurseListScreenPreview() {
    NurseListScreen(
        uiState = NurseListUiState(
            nurses = listOf(
                NurseUiState(id = 1, name = "Jan", surname = "Kowalski"),
                NurseUiState(id = 2, name = "Ala", surname = "Kot"),
            ),
        ),
        onCreateNurseClick = {},
        onDeleteNurseClick = {},
    )
}
