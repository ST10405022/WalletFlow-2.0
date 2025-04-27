package com.example.prog7313ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.prog7313ui.data.dao.BudgetCategoryDao
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.flow.Flow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    val allCategories: LiveData<List<BudgetCategory>> = repository.getAllCategories().asLiveData()
}

class CategoryRepository (private val dao: BudgetCategoryDao) {
    fun getAllCategories(): Flow<List<BudgetCategory>> = dao.getAllCategories()
}
