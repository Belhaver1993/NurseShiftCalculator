package pl.jakubgil.nurse.presentation.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import pl.jakubgil.nurse.presentation.create.NurseCreateViewModel
import pl.jakubgil.nurse.presentation.list.NurseListViewModel

val nursePresentationModule = module {
    viewModelOf(::NurseCreateViewModel)
    viewModelOf(::NurseListViewModel)
}
