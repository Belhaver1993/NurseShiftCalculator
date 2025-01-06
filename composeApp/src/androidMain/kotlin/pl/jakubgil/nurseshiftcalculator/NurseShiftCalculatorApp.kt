package pl.jakubgil.nurseshiftcalculator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinAppDeclaration
import pl.jakubgil.nurseshiftcalculator.di.nurseShiftCalculatorModules

@OptIn(KoinExperimentalAPI::class)
class NurseShiftCalculatorApp: Application(), KoinStartup {

    override fun onKoinStartup(): KoinAppDeclaration = {
        androidContext(this@NurseShiftCalculatorApp)
        modules(nurseShiftCalculatorModules)
    }

}