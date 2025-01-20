package com.meet.jetnote.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meet.jetnote.R
import com.meet.jetnote.components.NoteButton
import com.meet.jetnote.components.NoteInputText
import com.meet.jetnote.data.NotesDataSource
import com.meet.jetnote.model.Note
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        // Top AppBar
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            actions = {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Icon"
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFDF6BA0D))
        )

        // Content Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = { title = it } // Update title state
            )
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a Note",
                onTextChange = { description = it } // Update description state
            )
            NoteButton(
                modifier = Modifier,
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        //Log.d("NoteScreen", "Saving Note with title: $title and description: $description")
                        // Log.d("TAG", "NoteScreen: title = $title")
                        // Add the note
                        onAddNote(
                            Note(
                                title = title,
                                description = description
                            )

                        )
                        // Clear input fields
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    } else {
                        //Log.d("NoteScreen", "Save failed: Title or description is empty")
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        Divider(modifier = Modifier.padding(10.dp))


        // LazyColumn to display notes
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            items(notes) { note ->
                NoteRow(
                    note = note,
                    onNoteClicked = { onRemoveNote(note) },
                )
            }
        }
        //Text(text = notes.size.toString())
    }
}


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
   // Log.d("NoteRow", "Rendering Note: $note") // Log when a note is rendered
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 25.dp, bottomStart = 25.dp))
            .fillMaxWidth()
        .clickable {
        //Log.d("NoteRow", "Clicked on Note: $note") // Log when a note is clicked
        onNoteClicked(note)
    },
        color = Color(color = 0xFFCEB673),
        shadowElevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {onNoteClicked(note)}
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.bodySmall)
            Text(text = note.description, style = MaterialTheme.typography.bodySmall)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.bodySmall
            )
        }

    }

}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {

    NoteScreen(
        notes = NotesDataSource().loadNotes(),
        onAddNote = {},
        onRemoveNote = {}
    )
}