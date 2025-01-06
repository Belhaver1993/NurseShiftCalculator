package pl.jakubgil.calculator.data.month

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import pl.jakubgil.calculator.data.month.fitness.MonthlyScheduleFitnessCalculator
import pl.jakubgil.calculator.data.nurse.NurseMonthlyScheduleCreator
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calendar.domain.model.Month

internal class MonthlyScheduleCreator(
    private val fitnessCalculator: MonthlyScheduleFitnessCalculator,
    private val monthlyScheduleChildCreator: MonthlyScheduleChildCreator,
    private val nurseMonthlyScheduleCreator: NurseMonthlyScheduleCreator,
) {

    suspend fun create(
        month: Month,
        preconditions: List<NurseMonthlySchedule>,
    ): MonthlySchedule {
        val startTime = Clock.System.now().toEpochMilliseconds()
        var generation = 0
        var population: List<MonthlySchedule> =
            createFirstGeneration(month, preconditions)

        while (generation < MAX_NUMBER_OF_GENERATIONS && population.first().fitnessScore() < GOOD_ENOUGH_THRESHOLD) {
            println(
                "Time: ${(Clock.System.now().toEpochMilliseconds() - startTime) / 1000}s, Generation: ${generation.toString().padStart(
                    4,
                    '0',
                )}, " +
                    "score: ${population.first().fitnessScore()}, " +
                    "best in population: ${population.first()}",
            )

            val newGeneration: MutableList<MonthlySchedule> = mutableListOf()

            val topOfLastGenerationSize = TOP_OF_OLD_GENERATION_PERCENT * POPULATION_SIZE / 100
            val restOfLastGenerationSize = POPULATION_SIZE - topOfLastGenerationSize
            val fittestOfLastGenerationSize =
                FITTEST_OF_OLD_GENERATION_PERCENT * POPULATION_SIZE / 100

            newGeneration.addAll(population.take(topOfLastGenerationSize))

            val fittestOfLastGeneration = population.take(fittestOfLastGenerationSize)

            withContext(Dispatchers.IO) {
                List(restOfLastGenerationSize) {
                    async { createChild(month, fittestOfLastGeneration) }
                }.awaitAll()
                    .let { newGeneration.addAll(it) }
            }

            val reorderedNewGeneration = newGeneration.calculateFitnessAndReorder(month)

            population = reorderedNewGeneration
            generation++
        }

        val bestIndividual = population.first()

        print("[${"P, W, S, C, P, S, N, ".repeat(5)}]")
        println()
        bestIndividual.nurses.forEach {
            println(
                "${it.shifts} Days: ${it.shifts.count {
                    it.shift == Shift.DAY
                }} Nights: ${it.shifts.count {
                    it.shift == Shift.NIGHT
                }} Shorts: ${it.numberOfShortShifts}, Empty: ${it.numberOfEmptyShifts}",
            )
        }
        println(
            "Sum: ${bestIndividual.fitnessScore()} Sum of nurses: ${bestIndividual.nurses.sumOf {
                it.fitnessScore
            }} Violations: ${bestIndividual.violations}",
        )

        return population.first()
    }

    private suspend fun createFirstGeneration(
        month: Month,
        preconditions: List<NurseMonthlySchedule>,
    ): List<MonthlySchedule> = withContext(Dispatchers.IO) {
        return@withContext List(POPULATION_SIZE) {
            async {
                MonthlySchedule(
                    List(preconditions.size) { nurseIndex ->
                        nurseMonthlyScheduleCreator.create(
                            nurseIndex,
                            month,
                            preconditions = preconditions[nurseIndex].shifts,
                        )
                    },
                )
            }
        }.awaitAll().calculateFitnessAndReorder(month)
    }

    private fun List<MonthlySchedule>.calculateFitnessAndReorder(month: Month): List<MonthlySchedule> =
        this
            .onEach { monthlySchedule ->
                monthlySchedule.violations = fitnessCalculator.calculate(month, monthlySchedule)
            }
            .sortedByDescending { it.fitnessScore() }

    private suspend fun createChild(
        month: Month,
        fittestOfLastGeneration: List<MonthlySchedule>,
    ): MonthlySchedule {
        val parent1 = fittestOfLastGeneration.random()
        val parent2 = (fittestOfLastGeneration - parent1).random()

        return monthlyScheduleChildCreator.createChild(month, parent1, parent2)
    }

    private companion object {
        const val POPULATION_SIZE = 1000
        const val MAX_NUMBER_OF_GENERATIONS = 5000

        const val TOP_OF_OLD_GENERATION_PERCENT = 10
        const val FITTEST_OF_OLD_GENERATION_PERCENT = 50

        const val GOOD_ENOUGH_THRESHOLD = -1000
    }
}
