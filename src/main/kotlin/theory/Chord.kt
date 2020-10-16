package theory

enum class ChordDegree { Major, Minor, Diminished, Augmented }

data class Triad(val root: Note, val degree: ChordDegree) {

    val notes = when (degree) {
        ChordDegree.Major -> root.run { listOf(this, major3(), perfect5()) }
        ChordDegree.Minor -> root.run { listOf(this, minor3(), perfect5()) }
        ChordDegree.Diminished -> root.run { listOf(this, minor3(), tritone()) }
        ChordDegree.Augmented -> root.run { listOf(this, major3(), minor6()) }
    }

    val seventh = notes + when (degree) {
        ChordDegree.Major -> root.major7()
        ChordDegree.Minor -> root.minor7()
        ChordDegree.Diminished -> root.major6()
        ChordDegree.Augmented -> root.major7()
    }

    val name = "${root.name} ${degree.name}"

}