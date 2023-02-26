package com.lopanovych.newstarrepos.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.lopanovych.newstarrepos.domain.models.GithubRepo
import com.lopanovych.newstarrepos.domain.models.GithubUser
import com.lopanovych.newstarrepos.ui.theme.NewStarReposTheme

@Composable
fun RepositoryListItem(
    repository: GithubRepo,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable(onClick = onClick),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = repository.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(repository.owner.avatarUrl).build()
                    ),
                    contentDescription = "Repository Owner",
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(

                    text = repository.owner.login,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Stars: " + repository.stargazersCount.toString(),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewRepositoryListItem() {
    NewStarReposTheme {
        RepositoryListItem(
            repository = GithubRepo(
                id = 123,
                name = "MyRepository",
                description = "A sample repository",
                stargazersCount = 100,
                owner
                = GithubUser(
                    login = "John Doe",
                    id = 1233,
                    avatarUrl = "https://avatars.githubusercontent.com/u/123?v=4",
                    htmlUrl = "https://github.com/johndoe",
                    nodeId = "32123",
                    type = "fssdfsdf",
                    url = "https://github.com/johndoe"
                ),
                fullName = "Tesdfasdf",
                url = "https://github.com/johndoe/myrepository",
            ),
            onClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRepositoryListItemDark() {
    NewStarReposTheme {
        RepositoryListItem(
            repository = GithubRepo(
                id = 123,
                name = "MyRepository",
                description = "A sample repository",
                stargazersCount = 100,
                owner
                = GithubUser(
                    login = "John Doe",
                    id = 1233,
                    avatarUrl = "https://avatars.githubusercontent.com/u/123?v=4",
                    htmlUrl = "https://github.com/johndoe",
                    nodeId = "32123",
                    type = "fssdfsdf",
                    url = "https://github.com/johndoe"
                ),
                fullName = "Tesdfasdf",
                url = "https://github.com/johndoe/myrepository",
            ),
            onClick = {}
        )
    }
}