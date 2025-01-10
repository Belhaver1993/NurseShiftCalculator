package pl.jakubgil.nurse.presentation.list

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
import androidx.navigation.NavController
import nurseshiftcalculator.nurse.presentation.generated.resources.Res
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_add
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_delete
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import pl.jakubgil.nurse.presentation.create.NurseCreateRoute

@Composable
fun NurseListScreen(
    navController: NavController,
    viewModel: NurseListViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState().value

    NurseListScreen(
        uiState = uiState,
        onCreateNurseClick = {
            navController.navigate(NurseCreateRoute)
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                    ) {
                        Text(text = "${index + 1}")
                        Text(
                            text = "${nurse.surname} ${nurse.name}",
                            modifier = Modifier.weight(1f),
                        )
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
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
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
