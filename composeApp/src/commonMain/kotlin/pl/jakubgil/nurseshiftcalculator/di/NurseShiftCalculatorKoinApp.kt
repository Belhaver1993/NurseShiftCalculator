package pl.jakubgil.nurseshiftcalculator.di

import androidx.compose.runtime.Composable
import pl.jakubgil.calendar.data.di.calendarDataModule
import pl.jakubgil.calendar.domain.di.calendarDomainModule
import pl.jakubgil.database.data.di.databaseDataModule
import pl.jakubgil.database.domain.di.databaseDomainModule
import pl.jakubgil.nurse.domain.di.nurseDomainModule
import pl.jakubgil.nurse.presentation.di.nursePresentationModule

val nurseShiftCalculatorModules = listOf(
    appModule,
    calendarDomainModule,
    calendarDataModule,
    databaseDataModule,
    databaseDomainModule,
    nurseDomainModule,
    nursePresentationModule,
)

@Composable
expect fun NurseShiftCalculatorKoinApp(content: @Composable () -> Unit)
