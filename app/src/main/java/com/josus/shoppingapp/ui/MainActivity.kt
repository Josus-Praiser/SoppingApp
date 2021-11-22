package com.josus.shoppingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.josus.shoppingapp.R
import com.josus.shoppingapp.data.local.ProductListDatabase
import com.josus.shoppingapp.data.repository.ProductRepository
import com.josus.shoppingapp.presentation.ProductListViewModel
import com.josus.shoppingapp.presentation.ProductListViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : AppCompatActivity() {

    var productViewModel:ProductListViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository =ProductRepository(ProductListDatabase(this))
        val viewModelProviderFactory = ProductListViewModelProviderFactory(repository)

        productViewModel = ViewModelProvider(this,viewModelProviderFactory).get(ProductListViewModel::class.java)

    }
}