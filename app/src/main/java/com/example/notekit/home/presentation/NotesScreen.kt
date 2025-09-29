package com.example.notekit.home.presentation

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notekit.R
import com.example.notekit.core.composables.AddItemFAB
import com.example.notekit.core.composables.AppTopBar
import com.example.notekit.core.composables.NoteItem
import com.example.notekit.core.domain.model.Note
import com.example.notekit.ui.theme.NoteKitTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NotesScreen(
    modifier: Modifier = Modifier,
    uiState: NotesScreenUiState,
    onAddItemFABClick: () -> Unit,
    onNoteClick: (String) -> Unit,
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<String>,
) {
    // Controls expansion state of the search bar
    // https://developer.android.com/develop/ui/compose/components/search-bar#search-bar
    var expanded by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Column {
                AppTopBar(
                    title = stringResource(R.string.app_name),
                    navigationIcon = {},
                    actions = {}
                )
                SearchBar(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .semantics { traversalIndex = 0f },
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = textFieldState.text.toString(),
                            onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                            onSearch = {
                                onSearch(textFieldState.text.toString())
                                expanded = false
                            },
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            placeholder = { Text("Search note") },
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = "Search")
                            }
                        )
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                ) {
                    // Display search results in a scrollable column
                    Column(Modifier.verticalScroll(rememberScrollState())) {
                        searchResults.forEach { result ->
                            ListItem(
                                headlineContent = { Text(result) },
                                modifier = Modifier
                                    .clickable {
                                        textFieldState.edit { replace(0, length, result) }
                                        expanded = false
                                    }
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            AddItemFAB {
                onAddItemFABClick()
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (uiState) {
                is NotesScreenUiState.Loading -> NotesScreenLoading()
                is NotesScreenUiState.Empty -> NotesScreenEmpty()
                is NotesScreenUiState.Content -> NotesScreenContent(
                    notes = uiState.notes,
                    onNoteClick = onNoteClick
                )
            }
        }
    }
}

@Composable
private fun NotesScreenLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun NotesScreenEmpty(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.no_data_yet),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = modifier.height(8.dp))
            Text(
                text = stringResource(R.string.tap_plus_btn_add_note),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun NotesScreenContent(
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
private fun NotesScreenEmptyPreview() {
    NoteKitTheme(dynamicColor = false) {
        NotesScreen(
            modifier = Modifier,
            uiState = NotesScreenUiState.Empty,
            onAddItemFABClick = {},
            onNoteClick = {},
            textFieldState = TextFieldState(),
            onSearch = {},
            searchResults = listOf(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotesScreenContentPreview() {
    val notes = listOf(
        Note(1, "Alfa", "Info alfa"),
        Note(2, "Beta", "Info beta"),
        Note(3, "Gamma", "Info gamma"),
        Note(4, "Epsilon", "Info epsilon")
    )
    NoteKitTheme(dynamicColor = false) {
        NotesScreen(
            modifier = Modifier,
            uiState = NotesScreenUiState.Content(notes),
            onAddItemFABClick = {},
            onNoteClick = {},
            textFieldState = TextFieldState(),
            onSearch = {},
            searchResults = listOf(),
        )
    }
}