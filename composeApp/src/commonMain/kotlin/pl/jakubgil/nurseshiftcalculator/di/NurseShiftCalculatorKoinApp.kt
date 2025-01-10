package pl.jakubgil.nurseshiftcalculator.di

import androidx.compose.runtime.Composable
import pl.jakubgil.calendar.data.di.calendarDataModule
import pl.jakubgil.calendar.domain.di.calendarDomainModule
import pl.jakubgil.database.core.di.databaseCoreModule
import pl.jakubgil.nurse.data.di.nurseDataModule
import pl.jakubgil.nurse.domain.di.nurseDomainModule
import pl.jakubgil.nurse.presentation.di.nursePresentationModule

val nurseShiftCalculatorModules = listOf(
    appModule,
    calendarDomainModule,
    calendarDataModule,
    databaseCoreModule,
    nurseDataModule,
    nurseDomainModule,
    nursePresentationModule,
)

@Composable
expect fun NurseShiftCalculatorKoinApp(content: @Composable () -> Unit)
