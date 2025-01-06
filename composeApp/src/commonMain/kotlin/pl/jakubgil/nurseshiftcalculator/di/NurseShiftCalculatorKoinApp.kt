package pl.jakubgil.nurseshiftcalculator.di

import androidx.compose.runtime.Composable
import pl.jakubgil.calendar.data.di.calendarDataModule
import pl.jakubgil.calendar.domain.di.calendarDomainModule

val nurseShiftCalculatorModules = listOf(
    appModule,
    calendarDomainModule,
    calendarDataModule
)

@Composable
expect fun NurseShiftCalculatorKoinApp(content: @Composable () -> Unit)
