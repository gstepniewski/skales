package theory

import theory.Note

object Formatter {

    private const val sharp = "♯"
    private const val flat = "♭"
    private const val dim = "o"
    private const val aug = "+"

    fun asRootNote(note: Note) = when (note) {
        Note.AB -> "A$sharp / B$flat"
        Note.CD -> "C$sharp / D$flat"
        Note.DE -> "D$sharp / E$flat"
        Note.FG -> "F$sharp / G$flat"
        Note.GA -> "G$sharp / A$flat"
        else -> note.name
    }

    fun asSharp(note: Note) = when (note) {
        Note.AB -> "A$sharp"
        Note.CD -> "C$sharp"
        Note.DE -> "D$sharp"
        Note.FG -> "F$sharp"
        Note.GA -> "G$sharp"
        else -> note.name
    }

    fun asFlat(note: Note) = when (note) {
        Note.AB -> "B$flat"
        Note.CD -> "D$flat"
        Note.DE -> "E$flat"
        Note.FG -> "G$flat"
        Note.GA -> "A$flat"
        else -> note.name
    }

    fun inContext(note: Note, scale: Scale) = when (scale.relativeMajor) {
        Note.A -> asSharp(note)
        Note.AB -> asFlat(note)
        Note.B -> asSharp(note)
        Note.C -> asSharp(note)
        Note.CD -> asFlat(note)
        Note.D -> asSharp(note)
        Note.DE -> asFlat(note)
        Note.E -> asSharp(note)
        Note.F -> asSharp(note)
        Note.FG -> asSharp(note)
        Note.G -> asSharp(note)
        Note.GA -> asFlat(note)
    }

    fun inContext(triad: Triad, scale: Scale) = "${inContext(triad.root, scale)} ${triad.degree.display()}"

    fun inContextShort(triad: Triad, scale: Scale): String {
        val root = inContext(triad.root, scale)
        return when (triad.degree) {
            ChordDegree.Major -> root.toUpperCase()
            ChordDegree.MajorDominant -> root.toUpperCase()
            ChordDegree.Minor -> root.toLowerCase()
            ChordDegree.Diminished -> "${root.toLowerCase()}$dim"
            ChordDegree.Augmented -> "${root.toUpperCase()}$aug"
        }
    }
}