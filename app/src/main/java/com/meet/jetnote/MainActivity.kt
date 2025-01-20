package com.meet.jetnote

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.meet.jetnote.model.Note
import com.meet.jetnote.screen.NoteScreen
import com.meet.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetNoteTheme {
                Surface(color = MaterialTheme.colorScheme.background) {

                    val notes = remember { mutableStateListOf<Note>() }

                    NoteScreen(
                        notes = notes,
                        onAddNote = { note ->
                           // Log.d("NoteScreen", "Adding Note: $note")
                            notes.add(note)
                           // Log.d("NoteScreen", "Notes list size: ${notes.size}")
                        },
                        onRemoveNote = { note ->
                           // Log.d("NoteScreen", "Removing Note: $note")
                            notes.remove(note)
                          //  Log.d("NoteScreen", "Notes list size after removal: ${notes.size}")
                        }

                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetNoteTheme {

    }
}
