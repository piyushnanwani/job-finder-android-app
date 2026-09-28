package com.piyushnanwani.joblistingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class JobListingViewModel(
    private val repository: JobRepository = JobRepository()
): ViewModel() {
    private val _uiState = MutableStateFlow<JobUiState>(JobUiState.Loading)
    val uiState: StateFlow<JobUiState> = _uiState.asStateFlow()

    init {
        loadJobs()
    }

    fun loadJobs() {
        _uiState.value = JobUiState.Loading

        viewModelScope.launch {
            repository.getJob()
                .catch { exception ->
                    _uiState.value = JobUiState.Error(exception.message ?: "Unknown error")
                }
                .collect { jobs ->
                    _uiState.value = JobUiState.Success(jobs)
                }
        }

    }
}