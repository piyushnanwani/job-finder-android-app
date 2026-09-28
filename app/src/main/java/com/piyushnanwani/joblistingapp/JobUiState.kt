package com.piyushnanwani.joblistingapp

sealed interface JobUiState {
    object Loading: JobUiState
    data class Success(val jobs: List<JobItem>): JobUiState
    data class Error(val message: String): JobUiState
}