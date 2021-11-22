package com.josus.shoppingapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josus.shoppingapp.data.repository.ProductRepository

class ProductListViewModelProviderFactory(
    private val repository: ProductRepository
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ProductListViewModel(repository) as T
    }
}