package pl.jakubgil.nurseshiftcalculator

class Greeting {
    private val platform = getPlatform()

    fun greet(): String = "Hello, ${platform.name}!"
}
