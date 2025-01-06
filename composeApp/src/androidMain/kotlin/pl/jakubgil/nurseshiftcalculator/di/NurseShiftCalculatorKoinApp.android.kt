package pl.jakubgil.nurseshiftcalculator.di

import androidx.compose.runtime.Composable
import org.koin.androidx.compose.KoinAndroidContext

@Composable
actual fun NurseShiftCalculatorKoinApp(content: @Composable () -> Unit) {
    KoinAndroidContext {
        content()
    }
}