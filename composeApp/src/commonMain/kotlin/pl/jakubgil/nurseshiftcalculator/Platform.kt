package pl.jakubgil.nurseshiftcalculator

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform