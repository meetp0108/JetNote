package com.meet.jetnote.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.meet.jetnote.R
import com.meet.jetnote.components.NoteButton
import com.meet.jetnote.components.NoteInputText
import com.meet.jetnote.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {


    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    
    Column(modifier = Modifier.padding(6.dp)) {
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
        //Content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it

                }
            )
            NoteInputText(
                modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a Note",
                onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )
            NoteButton(
                modifier = Modifier,
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {

                        //save /add to the list

                        title = ""
                        description = ""
                    }
                }
            )


        }

    }

}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {

    NoteScreen(
        notes = emptyList(),
        onAddNote = {},
        onRemoveNote = {}
    )
}