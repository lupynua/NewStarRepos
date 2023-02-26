package com.lopanovych.newstarrepos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.lopanovych.newstarrepos.ui.RepositoryListScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: RootViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getLatestMostStarredRepos()

        setContent {
            val repos = viewModel.repos
            val lazyColumnListState = rememberLazyListState()

            val shouldStartPaginate = remember {
                derivedStateOf {
                    viewModel.canPaginate
                            && (lazyColumnListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) >= (lazyColumnListState.layoutInfo.totalItemsCount - 5)
                }
            }

            LaunchedEffect(key1 = shouldStartPaginate.value) {
                if (shouldStartPaginate.value && viewModel.state == RepoListState.IDLE)
                    viewModel.getLatestMostStarredRepos()
            }

            RepositoryListScreen(
                repos = repos,
                lazyListState = lazyColumnListState,
                repoListState = viewModel.state,
                context = LocalContext.current
            )
        }
    }
}

fun openUrl(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabColorSchemeParams = CustomTabColorSchemeParams.Builder()
        .build()
    builder.setDefaultColorSchemeParams(customTabColorSchemeParams)
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}