package pl.jakubgil.nurseshiftcalculator.di

import androidx.compose.runtime.Composable
import org.koin.compose.KoinApplication

@Composable
actual fun NurseShiftCalculatorKoinApp(content: @Composable () -> Unit) {
    KoinApplication(application = {
        modules(nurseShiftCalculatorModules)
    }) {
        content()
    }
}