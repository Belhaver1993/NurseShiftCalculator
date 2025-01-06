package pl.jakubgil.calculator.data.month

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import pl.jakubgil.calculator.data.nurse.NurseMonthlyScheduleCreator
import pl.jakubgil.calculator.domain.model.MonthlySchedule
import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calendar.domain.model.Month
import kotlin.random.Random

internal class MonthlyScheduleChildCreator(private val nurseMonthlyScheduleCreator: NurseMonthlyScheduleCreator) {

    suspend fun createChild(
        month: Month,
        partner1: MonthlySchedule,
        partner2: MonthlySchedule,
    ): MonthlySchedule = withContext(Dispatchers.IO) {
        val newChromosome: MutableList<NurseMonthlySchedule> = mutableListOf()

//        //TODO: Optimize creation of new chromosome, split to different asyncs
//        for (i in 0 until month.numberOfDays()) {
//            val actualIndex = newChromosome.size
//            val randomChance = Random.nextInt(100)
//            when (randomChance) {
//                in 0..45 -> newChromosome.add(partner1.nurses[actualIndex])
//                in 46..90 -> newChromosome.add(partner2.nurses[actualIndex])
//                else -> newChromosome.add(createRandomNurseMonthlySchedule(actualIndex, month, partner1.nurses[actualIndex]))
//            }
//        }

        withContext(Dispatchers.IO) {
            List(partner1.nurses.size) { nurseIndex ->
                async { createNewGene(nurseIndex, month, partner1, partner2) }
            }.awaitAll()
                .let { newChromosome.addAll(it) }
        }

        return@withContext MonthlySchedule(nurses = newChromosome)
    }

    private fun createNewGene(
        index: Int,
        month: Month,
        partner1: MonthlySchedule,
        partner2: MonthlySchedule,
    ): NurseMonthlySchedule {
        val randomChance = Random.nextInt(100)
        return when (randomChance) {
            in 0..45 -> partner1.nurses[index]
            in 46..90 -> partner2.nurses[index]
            else -> createRandomNurseMonthlySchedule(index, month, partner1.nurses[index])
        }
    }

    private fun createRandomNurseMonthlySchedule(
        nurseIndex: Int,
        month: Month,
        nurseMonthlySchedule: NurseMonthlySchedule,
    ): NurseMonthlySchedule =
        nurseMonthlyScheduleCreator.create(nurseIndex, month, preconditions = nurseMonthlySchedule.shifts)
}
