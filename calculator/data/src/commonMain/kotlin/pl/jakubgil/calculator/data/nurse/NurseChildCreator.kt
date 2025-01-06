package pl.jakubgil.calculator.data.nurse

import pl.jakubgil.calculator.domain.model.NurseMonthlySchedule
import pl.jakubgil.calculator.domain.model.Shift
import pl.jakubgil.calculator.domain.model.ShiftSchedule
import kotlin.random.Random

internal class NurseChildCreator {

    fun createChild(partner1: NurseMonthlySchedule, partner2: NurseMonthlySchedule): NurseMonthlySchedule {
        val newChromosome: MutableList<ShiftSchedule> = mutableListOf()

        while (newChromosome.size < partner1.shifts.size) {
            val actualIndex = newChromosome.size
            val randomChance = Random.nextInt(100)
            when {
                partner1.shifts[actualIndex].isForced -> newChromosome.add(partner1.shifts[actualIndex])
                randomChance in 0..45 -> newChromosome.add(partner1.shifts[actualIndex])
                randomChance in 46..90 -> newChromosome.add(partner2.shifts[actualIndex])
                else -> newChromosome.add(createRandomShift())
            }
        }

        return NurseMonthlySchedule(index = partner1.index, shifts = newChromosome)
    }

    private fun createRandomShift(): ShiftSchedule = ShiftSchedule(Shift.availableRandomShifts().random())
}
