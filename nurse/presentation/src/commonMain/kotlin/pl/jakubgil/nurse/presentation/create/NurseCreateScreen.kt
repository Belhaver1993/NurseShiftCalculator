package pl.jakubgil.nurse.presentation.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nurseshiftcalculator.nurse.presentation.generated.resources.Res
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_create_name_hint
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_create_nurse_button
import nurseshiftcalculator.nurse.presentation.generated.resources.nurse_create_surname_hint
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NurseCreateScreen(
    navController: NavController,
    viewModel: NurseCreateViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(uiState.nurseCreated) {
        if (uiState.nurseCreated) {
            navController.navigateUp()
        }
    }

    NurseCreateScreen(
        uiState = uiState,
        onNameUpdate = viewModel::updateName,
        onSurnameUpdate = viewModel::updateSurname,
        onNurseCreateClick = viewModel::createNurse,
        modifier = modifier,
    )
}

@Composable
fun NurseCreateScreen(
    uiState: NurseCreateUiState,
    onNameUpdate: (String) -> Unit,
    onSurnameUpdate: (String) -> Unit,
    onNurseCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(16.dp),
    ) {
        TextField(
            value = uiState.name,
            onValueChange = onNameUpdate,
            label = { Text(stringResource(Res.string.nurse_create_name_hint)) },
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = uiState.surname,
            onValueChange = onSurnameUpdate,
            label = { Text(stringResource(Res.string.nurse_create_surname_hint)) },
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = onNurseCreateClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(Res.string.nurse_create_nurse_button))
        }
    }
}
