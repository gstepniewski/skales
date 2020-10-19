package theory

enum class ScaleMode { Major, Dorian, Phrygian, Lydian, Mixolydian, Minor, Locrian }

data class Scale(val rootNote: Note, val mode: ScaleMode) {

    // TODO Use circular list here
    private val notesFormula: List<Note.() -> Note> = listOf (
        {wholeStep()}, {wholeStep()}, {halfStep()}, {wholeStep()}, {wholeStep()}, {wholeStep()}, {halfStep()},
        {wholeStep()}, {wholeStep()}, {halfStep()}, {wholeStep()}, {wholeStep()}, {wholeStep()}, {halfStep()},
        {wholeStep()}, {wholeStep()}, {halfStep()}, {wholeStep()}, {wholeStep()}, {wholeStep()}, {halfStep()}
    )

    val notes = notesFormula.drop(mode.ordinal).take(6) // Already includes the root
                                       .fold(listOf(rootNote)) { acc, f -> acc + f(acc.last()) }

    // TODO Use circular list here
    private val chordsFormula = listOf(
        ChordDegree.Major, ChordDegree.Minor, ChordDegree.Minor, ChordDegree.Major, ChordDegree.MajorDominant, ChordDegree.Minor, ChordDegree.Diminished,
        ChordDegree.Major, ChordDegree.Minor, ChordDegree.Minor, ChordDegree.Major, ChordDegree.MajorDominant, ChordDegree.Minor, ChordDegree.Diminished,
        ChordDegree.Major, ChordDegree.Minor, ChordDegree.Minor, ChordDegree.Major, ChordDegree.MajorDominant, ChordDegree.Minor, ChordDegree.Diminished,
    )

    val chords = chordsFormula.drop(mode.ordinal).take(7)
                                         .zip(notes) { degree, note -> Triad(note, degree) }

    val relativeMajor = notes.plus(rootNote).reversed()[mode.ordinal]
}