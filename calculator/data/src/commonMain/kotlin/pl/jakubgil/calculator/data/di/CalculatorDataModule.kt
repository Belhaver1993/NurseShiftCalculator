package pl.jakubgil.calculator.data.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.jakubgil.calculator.data.CalculatorRepositoryImpl
import pl.jakubgil.calculator.data.GeneticGlobalProperties
import pl.jakubgil.calculator.data.month.MonthlyScheduleChildCreator
import pl.jakubgil.calculator.data.month.MonthlyScheduleCreator
import pl.jakubgil.calculator.data.month.fitness.MonthlyScheduleFitnessCalculator
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthAmountOfNursesPerDayScoreCalculation
import pl.jakubgil.calculator.data.month.fitness.calculations.MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation
import pl.jakubgil.calculator.data.nurse.NurseChildCreator
import pl.jakubgil.calculator.data.nurse.NurseMonthlyScheduleCreator
import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessCalculator
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseAmountOfConsecutiveShiftsScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseDayCannotBeAfterNightScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseShiftsShouldMatchWithPossibleShiftsScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseShortShiftShouldOnlyBeInWorkdayScoreCalculation
import pl.jakubgil.calculator.data.nurse.fitness.calculations.NurseSpecialShiftsAmountShouldBeAccurateScoreCalculation
import pl.jakubgil.calculator.data.nurse.possibility.NurseMonthlySchedulePossibilityChecker
import pl.jakubgil.calculator.data.nurse.possibility.NursePossibleShiftsCreator
import pl.jakubgil.calculator.domain.CalculatorRepository

val calculatorDataModule = module {
    singleOf(::GeneticGlobalProperties)

    includes(calculatorDataMonthModule, calculatorDataNurseModule)

    factory<CalculatorRepository> { CalculatorRepositoryImpl(get()) }
}

internal val calculatorDataMonthModule = module {
    factoryOf(::MonthAmountOfNursesPerDayScoreCalculation)
    factoryOf(::MonthSpecialShiftsShouldBeEqualToRequiredAmountScoreCalculation)
    factoryOf(::MonthlyScheduleFitnessCalculator)

    factoryOf(::MonthlyScheduleChildCreator)
    factoryOf(::MonthlyScheduleCreator)
}

internal val calculatorDataNurseModule = module {
    factoryOf(::NursePossibleShiftsCreator)
    factoryOf(::NurseMonthlySchedulePossibilityChecker)

    factoryOf(::NurseAmountOfConsecutiveShiftsScoreCalculation)
    factoryOf(::NurseDayCannotBeAfterNightScoreCalculation)
    factoryOf(::NurseShiftsShouldMatchWithPossibleShiftsScoreCalculation)
    factoryOf(::NurseShortShiftShouldOnlyBeInWorkdayScoreCalculation)
    factoryOf(::NurseSpecialShiftsAmountShouldBeAccurateScoreCalculation)
    factoryOf(::NurseFitnessCalculator)

    factoryOf(::NurseChildCreator)
    factoryOf(::NurseMonthlyScheduleCreator)
}
