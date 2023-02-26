package com.lopanovych.newstarrepos

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.platform.LocalContext
import com.lopanovych.newstarrepos.ui.RepositoryListScreen
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: RootViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getLatestMostStarredRepos(1)

        setContent {
            RepositoryListScreen(
                repos = viewModel.repos.value,
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