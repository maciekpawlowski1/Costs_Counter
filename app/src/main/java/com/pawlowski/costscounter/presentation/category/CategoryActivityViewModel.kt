package com.pawlowski.costscounter.presentation.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryActivityViewModel @Inject constructor(

    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val categoryId: Int = savedStateHandle.get<Int>("categoryId")!!

}