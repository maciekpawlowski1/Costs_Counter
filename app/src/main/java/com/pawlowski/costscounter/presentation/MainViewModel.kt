package com.pawlowski.costscounter.presentation

import androidx.lifecycle.ViewModel
import com.pawlowski.costscounter.domain.use_cases.DeleteCollectionsUseCase
import com.pawlowski.costscounter.domain.use_cases.GetCollectionsUseCase
import com.pawlowski.costscounter.domain.use_cases.InsertCollectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
        private val getCollectionsUseCase: GetCollectionsUseCase,
        private val insertCollectionsUseCase: InsertCollectionsUseCase,
        private val deleteCollectionsUseCase: DeleteCollectionsUseCase
    ): ViewModel() {

}