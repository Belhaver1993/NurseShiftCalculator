package pl.jakubgil.nurse.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.jakubgil.nurse.presentation.NurseListViewModel

val nursePresentationModule = module {
    viewModelOf(::NurseListViewModel)
}
