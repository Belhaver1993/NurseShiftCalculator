package pl.jakubgil.nurseshiftcalculator.di

import androidx.compose.runtime.Composable
import pl.jakubgil.calendar.data.di.calendarDataModule
import pl.jakubgil.calendar.domain.di.calendarDomainModule
import pl.jakubgil.database.data.di.databaseDataModule
import pl.jakubgil.database.domain.di.databaseDomainModule

val nurseShiftCalculatorModules = listOf(
    appModule,
    calendarDomainModule,
    calendarDataModule,
    databaseDataModule,
    databaseDomainModule,
)

@Composable
expect fun NurseShiftCalculatorKoinApp(content: @Composable () -> Unit)
