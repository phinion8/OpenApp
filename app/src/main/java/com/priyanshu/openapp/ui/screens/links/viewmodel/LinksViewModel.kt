package com.priyanshu.openapp.ui.screens.links.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyanshu.openapp.R
import com.priyanshu.openapp.domain.models.AppResult
import com.priyanshu.openapp.domain.models.OverviewItem
import com.priyanshu.openapp.domain.usecases.MainUseCase
import com.priyanshu.openapp.ui.theme.blue
import com.priyanshu.openapp.ui.theme.purple
import com.priyanshu.openapp.ui.theme.softRed
import com.priyanshu.openapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LinksViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _appResultState = MutableStateFlow<AppResult?>(null)
    val appResultState = _appResultState.asStateFlow()

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        getAppResult()
    }

    fun getOverviewItems(): List<OverviewItem>{

        val items = listOf<OverviewItem>(
            OverviewItem(icon = R.drawable.ic_click, iconTint = purple, title = "Today's clicks", value = _appResultState.value?.today_clicks.toString()),
            OverviewItem(icon = R.drawable.ic_location, iconTint = blue, "Top Location", _appResultState.value?.top_location.toString()),
            OverviewItem(icon = R.drawable.ic_web, iconTint = softRed, "Top Sources", _appResultState.value?.top_source.toString())
        )

        return items
    }

    private fun getAppResult() {
        viewModelScope.launch {
            mainUseCase.getAppResult().collect { result ->

                when (result) {
                    is Resource.Loading -> {
                        _loading.value = true
                    }

                    is Resource.Success -> {
                        _loading.value = false
                        _appResultState.emit(result.data)
                        getOverviewItems()
                    }

                    is Resource.Error -> {

                        _loading.value = false
                        _error.value = result.message

                    }
                }

            }
        }
    }


}