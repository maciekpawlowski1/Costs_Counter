package com.pawlowski.costscounter.presentation.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.pawlowski.costscounter.data.entities.CategoryCostItemEntity
import com.pawlowski.costscounter.domain.use_cases.delete.DeleteItemsUseCase
import com.pawlowski.costscounter.domain.use_cases.edit.EditCategoryUseCase
import com.pawlowski.costscounter.domain.use_cases.get.GetCategoryUseCase
import com.pawlowski.costscounter.domain.use_cases.insert.InsertItemToCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryActivityViewModel @Inject constructor(
    private val insertItemToCategoryUseCase: InsertItemToCategoryUseCase,
    private val getCategoryUseCase: GetCategoryUseCase,
    private val editCategoryUseCase: EditCategoryUseCase,
    private val deleteItemsUseCase: DeleteItemsUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val categoryId: Int = savedStateHandle.get<Int>("categoryId")!!

    val category = getCategoryUseCase.execute(categoryId).asLiveData()

    fun insertItemToCategory(name: String, cost: Double, amount: Int)
    {
        viewModelScope.launch {
            insertItemToCategoryUseCase.execute(categoryId, name, cost, amount)
        }
    }

    fun editCategoryName(newName: String)
    {
        viewModelScope.launch {
            category.value?.let {
                editCategoryUseCase.execute(it.categoryEntity.copy(categoryName = newName))
            }
        }
    }

    fun deleteItems(items: List<CategoryCostItemEntity>)
    {
        viewModelScope.launch {
            deleteItemsUseCase.executeCategoryItems(items)
        }
    }

}