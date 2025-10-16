package com.example.testcomposeapp.ui.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.testcomposeapp.data.model.ListResponse
import com.example.testcomposeapp.ui.main.viewmodel.MainScreenViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier,
               viewModel: MainScreenViewModel = hiltViewModel()
) {

    val list by viewModel.list.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getList()
    }

    Column {
        TopAppBar(title = { Text("Marvel") })

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            items(list) {
                ListItem(it)
            }
        }
    }

}


@Composable
fun ListItem(item: ListResponse){

    Column(modifier = Modifier
        .fillMaxWidth()
        .shadow(.5.dp, RoundedCornerShape(12.dp))
        .background(
        Color.White).padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {

        AsyncImage(model = item.imageurl,
            contentDescription = "Cover",
            modifier = Modifier.fillMaxWidth().height(240.dp),
            contentScale = ContentScale.Crop)


        Text(text = item.name ?: "No Title",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp))

        Text(text = item.bio ?: "No Description",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp))
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    val sample = listOf(
        ListResponse(
            name = "Iron Man",
            bio = "Genius, billionaire, playboy, philanthropist.",
            imageurl = "https://picsum.photos/800/400?1"
        ),
        ListResponse(
            name = "Captain Marvel",
            bio = "Cosmic Avenger.",
            imageurl = "https://picsum.photos/800/400?2"
        )
    )
    Scaffold(topBar = { TopAppBar(title = { Text("Marvel") }) }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sample) { ListItem(it) }
        }
    }
}