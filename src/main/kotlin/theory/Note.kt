package theory

enum class Note {

    A, AB, B, C, CD, D, DE, E, F, FG, G, GA;

    operator fun plus(st: Int) = values().run { get((ordinal + st) % size) }
    operator fun minus(st: Int) = values().run { get(((ordinal - st) % size + size) % size) } // Fix for negative mod here

    fun flat() = minus(1)
    fun sharp() = plus(1)

    fun halfStep() = plus(1)
    fun wholeStep() = plus(2)

    fun minor2() = plus(1)
    fun major2() = plus(2)
    fun minor3() = plus(3)
    fun major3() = plus(4)
    fun perfect4() = plus(5)
    fun tritone() = plus(6)
    fun perfect5() = plus(7)
    fun minor6() = plus(8)
    fun major6() = plus(9)
    fun minor7() = plus(10)
    fun major7() = plus(11)
    fun octave() = plus(12)

}