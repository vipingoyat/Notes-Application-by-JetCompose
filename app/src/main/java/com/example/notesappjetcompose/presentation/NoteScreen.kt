package com.example.notesappjetcompose.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AlignVerticalCenter
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notesappjetcompose.ui.theme.Pink80

@Composable
fun NoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent: (NotesEvent)->Unit
) {
    Scaffold(

        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(Color.Black)
                .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                Text(text = "Notes App",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Green
                )
                IconButton(onClick = { onEvent(NotesEvent.SortNotes) }) {
                    Icon(imageVector = Icons.Rounded.AlignVerticalCenter, contentDescription =null,
                        modifier = Modifier.size(35.dp), tint = Color.Green)

                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(containerColor = Color.Cyan,onClick = {
                state.title.value= ""
                state.description.value = ""
                navController.navigate("AddNoteScreen")
            }) {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        LazyColumn(contentPadding = it,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {

            items(state.notes.size){
                NoteItem(
                    state = state,
                    index = it,
                    onEvent= onEvent
                )
            }

        }
    }
}

@Composable
fun NoteItem(state: NoteState, index: Int, onEvent: (NotesEvent) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(Pink80)
        .padding(12.dp))
    {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = state.notes.get(index=index).title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
           )
           Spacer(modifier = Modifier.height(8.dp))
            Text(text = state.notes.get(index=index).description,
                fontSize = 16.sp,
                color = Color.Black)

        }
        IconButton(onClick = { onEvent(NotesEvent.DeleteNote(
            state.notes.get(index=index)
        )) }) {
            Icon(imageVector = Icons.Rounded.Delete,
                contentDescription = null,
                modifier = Modifier.size(35.dp))

        }



    }

}
