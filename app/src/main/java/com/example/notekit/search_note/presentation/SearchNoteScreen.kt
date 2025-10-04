package com.example.notekit.search_note.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notekit.core.composables.NoteItem
import com.example.notekit.core.domain.model.Note
import com.example.notekit.ui.theme.NoteKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchNoteScreen(
    modifier: Modifier = Modifier,
    state: SearchNoteScreenState,
    query: String,
    onArrowBackIconClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onClearIconClick: () -> Unit,
    onNoteClick: (String) -> Unit,
) {
    var queryInput by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column {
        val onActiveChange: (Boolean) -> Unit = {}
        val colors1 = SearchBarDefaults.colors()
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = onSearch,
                    expanded = true,
                    onExpandedChange = onActiveChange,
                    enabled = true,
                    placeholder = { Text("Search...") },
                    leadingIcon = {
                        IconButton(onClick = { onArrowBackIconClick() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Nav Back",
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            )
                        }
                    },
                    trailingIcon = {
                        if (active) {
                            IconButton(onClick = {
                                if (queryInput.isNotEmpty()) {
                                    queryInput = ""
                                    onClearIconClick()
                                } else {
                                    active = false
                                }
                            }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear search")
                            }
                        }
                    },
                    colors = colors1.inputFieldColors,
                    interactionSource = null,
                )
            },
            expanded = true,
            onExpandedChange = onActiveChange,
            modifier = Modifier,
            shape = SearchBarDefaults.inputFieldShape,
            colors = colors1,
            tonalElevation = SearchBarDefaults.TonalElevation,
            shadowElevation = SearchBarDefaults.ShadowElevation,
            windowInsets = SearchBarDefaults.windowInsets,
            content = {
                when (state) {
                    is SearchNoteScreenState.Idle -> {
                        SearchNoteScreenInfo(modifier, "No data yet.")
                    }

                    is SearchNoteScreenState.Loading -> SearchNoteScreenLoading(modifier)
                    is SearchNoteScreenState.Success -> {
                        val results = state.results
                        SearchNoteScreenContent(modifier, results, onNoteClick = onNoteClick)
                    }

                    is SearchNoteScreenState.Error -> {
                        val message = state.message
                        SearchNoteScreenInfo(modifier, message)
                    }
                }
            }
        )
    }
}

@Composable
private fun SearchNoteScreenLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SearchNoteScreenInfo(
    modifier: Modifier = Modifier,
    info: String
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = info,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
private fun SearchNoteScreenContent(
    modifier: Modifier = Modifier,
    notes: List<Note>,
    onNoteClick: (String) -> Unit
) {
    Spacer(
        modifier
            .fillMaxWidth()
            .height(24.dp)
    )
    LazyColumn(
        modifier = modifier
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        itemsIndexed(notes) { index, note ->
            NoteItem(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { onNoteClick(note.id.toString()) },
                title = note.name
            )
            if (index < notes.size - 1)
                HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchNoteScreenInfoPreview() {
    NoteKitTheme(dynamicColor = false) {
        SearchNoteScreen(
            modifier = Modifier,
            state = SearchNoteScreenState.Idle,
            query = "abc",
            onArrowBackIconClick = {},
            onQueryChange = {},
            onSearch = {},
            onClearIconClick = {},
            onNoteClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchNoteScreenContentPreview() {
    val notes = listOf(
        Note(1, "Aleta", "Info aleta"),
        Note(2, "Beta", "Info beta")
    )
    NoteKitTheme(dynamicColor = false) {
        SearchNoteScreen(
            modifier = Modifier,
            state = SearchNoteScreenState.Success(notes),
            query = "eta",
            onArrowBackIconClick = {},
            onQueryChange = {},
            onSearch = {},
            onClearIconClick = {},
            onNoteClick = {},
        )
    }
}