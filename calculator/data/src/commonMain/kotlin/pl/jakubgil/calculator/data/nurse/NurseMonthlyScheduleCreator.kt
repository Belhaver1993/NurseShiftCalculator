package pl.jakubgil.calculator.data.nurse

import pl.jakubgil.calculator.data.nurse.fitness.NurseFitnessCalculator
import pl.jakubgil.calculator.data.nurse.possibility.NurseMonthlySchedulePossibilityChecker
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calculator.domain.model.ShiftSchedule
import pl.jakubgil.calendar.domain.model.Month

internal class NurseMonthlyScheduleCreator(
    private val fitnessCalculator: NurseFitnessCalculator,
    private val nurseChildCreator: NurseChildCreator,
    private val nurseMonthlySchedulePossibilityChecker: NurseMonthlySchedulePossibilityChecker,
) {

    fun create(
        nurseIndex: Int,
        month: Month,
        preconditions: List<ShiftSchedule>,
        shouldPrintLogs: Boolean = false,
    ): NurseMonthlySchedule {
        nurseMonthlySchedulePossibilityChecker.isSchedulePossible(nurseIndex, month, preconditions)

        var generation = 0
        var population: List<NurseMonthlySchedule> =
            createFirstGeneration(nurseIndex, month, preconditions)

        while (generation < MAX_NUMBER_OF_GENERATIONS && population.first().fitnessScore < GOOD_ENOUGH_THRESHOLD) {
            if (shouldPrintLogs) {
                println(
                    "Generation: ${generation.toString().padStart(3, '0')}, " +
                        "best in population: ${population.first()}",
                )
            }

            val newGeneration: MutableList<NurseMonthlySchedule> = mutableListOf()

            val topOfLastGenerationSize = TOP_OF_OLD_GENERATION_PERCENT * POPULATION_SIZE / 100
            val restOfLastGenerationSize = POPULATION_SIZE - topOfLastGenerationSize
            val fittestOfLastGenerationSize =
                FITTEST_OF_OLD_GENERATION_PERCENT * POPULATION_SIZE / 100

            newGeneration.addAll(population.take(topOfLastGenerationSize))

            val fittestOfLastGeneration = population.take(fittestOfLastGenerationSize)

            for (i in 0..restOfLastGenerationSize) {
                newGeneration.add(createChild(fittestOfLastGeneration))
            }

            val reorderedNewGeneration = newGeneration.calculateFitnessAndReorder(month)

            population = reorderedNewGeneration
            generation++
        }

        if (shouldPrintLogs) {
            print("[${"Pn Wt Sr Cz Pt So Nd ".repeat(5)}]")
            println()
            println("${population.first().shifts}")
        }

        return population.first()
    }

    private fun createFirstGeneration(
        nurseIndex: Int,
        month: Month,
        preconditions: List<ShiftSchedule>,
    ): List<NurseMonthlySchedule> =
        List(POPULATION_SIZE) {
            NurseMonthlySchedule(
                nurseIndex,
                List(month.numberOfDays()) { shiftIndex ->
                    if (preconditions.size > shiftIndex && preconditions[shiftIndex].isForced) {
                        preconditions[shiftIndex]
                    } else {
                        ShiftSchedule(Shift.availableRandomShifts().random())
                    }
                },
            )
        }.calculateFitnessAndReorder(month)

    private fun List<NurseMonthlySchedule>.calculateFitnessAndReorder(month: Month): List<NurseMonthlySchedule> =
        this
            .onEach { nurseMonthlySchedule ->
                nurseMonthlySchedule.fitnessScore = fitnessCalculator.calculate(month, nurseMonthlySchedule)
            }
            .sortedByDescending { it.fitnessScore }

    private fun createChild(fittestOfLastGeneration: List<NurseMonthlySchedule>): NurseMonthlySchedule {
        val parent1 = fittestOfLastGeneration.random()
        val parent2 = (fittestOfLastGeneration - parent1).random()

        return nurseChildCreator.createChild(parent1, parent2)
    }

    private companion object {
        const val POPULATION_SIZE = 100
        const val MAX_NUMBER_OF_GENERATIONS = 100

        const val TOP_OF_OLD_GENERATION_PERCENT = 10
        const val FITTEST_OF_OLD_GENERATION_PERCENT = 50

        const val GOOD_ENOUGH_THRESHOLD = 0
    }
}
