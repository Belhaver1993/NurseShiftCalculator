package pl.jakubgil.nurseshiftcalculator

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import pl.jakubgil.calendar.domain.model.Month
import pl.jakubgil.calendar.domain.useCase.GetMonth
import pl.jakubgil.nurse.presentation.NurseListScreen
import pl.jakubgil.nurseshiftcalculator.di.NurseShiftCalculatorKoinApp

@Composable
@Preview
fun App() {
    NurseShiftCalculatorKoinApp {
        var monthState by remember { mutableStateOf<Month?>(null) }
        val getMonth = koinInject<GetMonth>()
        LaunchedEffect(Unit) {
            val month = getMonth.invoke(2025, 1)

            monthState = month
        }

        MaterialTheme {
//            var showContent by remember { mutableStateOf(false) }
//            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Button(onClick = { showContent = !showContent }) {
//                    Text("Click me!")
//                }
//                AnimatedVisibility(showContent) {
//                    val greeting = remember { Greeting().greet() }
//                    Column(
//                        Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                    ) {
//                        Image(painterResource(Res.drawable.compose_multiplatform), null)
//                        Text("$monthState")
//                    }
//                }
//            }

            NurseListScreen()
        }
    }
}
