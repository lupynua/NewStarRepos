package com.lopanovych.newstarrepos.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lopanovych.newstarrepos.domain.models.GithubRepo
import com.lopanovych.newstarrepos.openUrl
import com.lopanovych.newstarrepos.ui.theme.NewStarReposTheme

@Composable
fun RepositoryListScreen(repos: List<GithubRepo>, context: Context) {
    NewStarReposTheme(dynamicColor = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            LazyColumn {
                item {
                    Text(
                        text = "Most starred Github repos for past month",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    )
                }
                itemsIndexed(repos) { _, item ->
                    RepositoryListItem(repository = item) {
                        openUrl(context, item.url)
                    }
                }
            }
        }
    }
}