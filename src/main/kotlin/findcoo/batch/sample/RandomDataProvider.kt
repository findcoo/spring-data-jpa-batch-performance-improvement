package findcoo.batch.sample

import kotlin.random.Random

private val CHAR_POOL: List<Char> = ('a'..'z') + ('A'..'Z')

fun getRandomName() = getRandomString(6)

fun getRandomMessage() = getRandomString(60)

fun getRandomString(maxLength: Int): String = String(
        (0 until maxLength - 1)
                .map { Random.nextInt(0, CHAR_POOL.size) }
                .map(CHAR_POOL::get)
                .fold(charArrayOf(), { array, ch -> array.plus(ch) }))
