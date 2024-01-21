package me.hib4.wejanganmadyo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.hib4.wejanganmadyo.domain.usecases.appentry.AppEntryUseCases
import me.hib4.wejanganmadyo.presentation.navgraph.Route
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appEntryUseCases: AppEntryUseCases,
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.name)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach {
            startDestination = if (it) {
                Route.NewsNavigation.name
            } else {
                Route.AppStartNavigation.name
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}