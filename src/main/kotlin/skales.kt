import kotlinx.html.js.onChangeFunction
import kotlinx.html.style
import org.w3c.dom.HTMLSelectElement
import react.*
import react.dom.*
import theory.*

external interface SkalesProps: RProps

external interface SkalesState: RState {
    var rootNote: Note
    var mode: ScaleMode
}

class Skales: RComponent<SkalesProps, SkalesState>() {

    init {
        state.rootNote = Note.C
        state.mode = ScaleMode.Major
    }

    override fun RBuilder.render() {
        div("center") {
            rootNoteSelector { note ->
                setState { this.rootNote = note }
            }
            modeSelector { mode ->
                setState { this.mode = mode }
            }
        }
        div("center") {
            scaleTable()
        }
    }

    private fun RBuilder.rootNoteSelector(changed: (Note) -> Unit) {
        select {
            Note.values().forEach {
                option {
                    attrs.selected = it == state.rootNote
                    attrs.value = it.ordinal.toString()
                    +Formatter.asRootNote(it)
                }
            }
            attrs.onChangeFunction = {
                val selectedIndex = (it.target as HTMLSelectElement).value.toInt()
                changed(Note.values()[selectedIndex])
            }
        }
    }

    private fun RBuilder.modeSelector(changed: (ScaleMode) -> Unit) {
        select {
            ScaleMode.values().forEach {
                option {
                    attrs.selected = it == state.mode
                    attrs.value = it.ordinal.toString()
                    +it.name
                }
                attrs.onChangeFunction = {
                    val selectedIndex = (it.target as HTMLSelectElement).value.toInt()
                    changed(ScaleMode.values()[selectedIndex])
                }
            }
        }
    }

    private fun RBuilder.scaleTable() {
        val scale = Scale(state.rootNote, state.mode)

        table("center") {
            tr("header") {
                th {
                    attrs.colSpan = "8"
                    +"${Formatter.inContext(state.rootNote, scale)} ${state.mode.name}"
                }
            }
            tr("notes") {
                td { +"Notes" }
                scale.notes.forEach {
                    td { +Formatter.inContext(it, scale) }
                }
            }
            tr("chords") {
                td { +"Chords" }
                scale.chords.forEach {
                    td { +Formatter.inContext(it, scale) }
                }
            }
            tr("triads") {
                td { +"Triad" }
                scale.chords.map { it.notes.map { Formatter.inContext(it, scale) } }.forEach {
                    td { +it.joinToString("\n") }
                }
            }
            tr("seventh") {
                td { +"Seventh" }
                scale.chords.map { Formatter.inContext(it.seventh.last(), scale) }.forEach {
                    td { +it }
                }
            }
        }
    }
}