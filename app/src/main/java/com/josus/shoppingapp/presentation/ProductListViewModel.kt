package com.josus.shoppingapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.josus.shoppingapp.data.repository.ProductRepository
import com.josus.shoppingapp.data.model.Product
import com.josus.shoppingapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ProductListViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productList: MutableLiveData<Resource<List<Product>>> = MutableLiveData()
    val productList: LiveData<Resource<List<Product>>> = _productList

    private val _productListByPrice: MutableLiveData<List<Product>> = MutableLiveData()
    val productListByPrice: LiveData<List<Product>> = _productListByPrice

    private val _productListByName: MutableLiveData<List<Product>> = MutableLiveData()
    val productListByName: LiveData<List<Product>> = _productListByName

    init {
        getProductList()
        getProductByPriceDesc()
        getProductByTitleAsc()
    }

    fun getProductList() = viewModelScope.launch {
        _productList.postValue(Resource.Loading())

        val productList = repository.getProductList().map { it.toProduct() }
        _productList.postValue(Resource.Loading(data = productList))

        try {
            val remoteProductList = repository.getRemoteProductList()
            repository.deleteProductList(remoteProductList.map { it.id })
            repository.insertProduct(remoteProductList.map { it.toProductEntity() })
        } catch (e: HttpException) {
            _productList.postValue(Resource.Error("Unknown Network error!", data = productList))
        } catch (e: IOException) {
            _productList.postValue(Resource.Error("Error: $e", data = productList))
        } catch (e: Exception) {
            _productList.postValue(Resource.Error("Error: $e", data = productList))
        }

        val newProductList = repository.getProductList().map { it.toProduct() }
        _productList.postValue(Resource.Success(data = newProductList))

    }

     fun getProductByPriceDesc() =viewModelScope.launch {
         _productListByPrice.postValue(repository.getProductByPrice())  }

    fun getProductByTitleAsc() =viewModelScope.launch {
        _productListByName.postValue(repository.getProductByTitle()) }


}
