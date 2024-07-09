package com.example.notesappjetcompose.presentation

import com.example.notesappjetcompose.data.Note

sealed interface NotesEvent {
     object SortNotes : NotesEvent
     data class DeleteNote(var note: Note) : NotesEvent
    data class SaveNote(
        var title: String,
        var description: String
        ): NotesEvent
}