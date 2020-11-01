package ru.tanom.ui.ad_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.FlexColumn
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import ru.tanom.R
import ru.tanom.base.view.BaseFragment

class AdDetailsFragment : BaseFragment() {

    private lateinit var adDetailsViewModel: AdDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_ad_details, container, false)

        (root as ViewGroup).setContent {
            Hello("Jetpack Compose")
        }

        return root
    }

    @Composable
    fun Hello(name: String) = MaterialTheme {
        FlexColumn {
            inflexible {
                // Item height will be equal content height
                TopAppBar<MenuItem>( // App Bar with title
                    title = { Text("Jetpack Compose Sample") }
                )
            }
            expanded(1F) {
                // occupy whole empty space in the Column
                // Center content
                Text("Hello $name!") // Text label
            }
        }
    }

    override fun <T> onSuccess(content: T?) {
    }

    override fun showPlaceholder() {
    }

    override fun hidePlaceholder() {
    }

    override fun setToolbar() {
    }

}