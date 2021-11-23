package com.josus.shoppingapp.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.josus.shoppingapp.R
import com.josus.shoppingapp.databinding.FragmentHomeBinding
import com.josus.shoppingapp.presentation.ProductListViewModel
import com.josus.shoppingapp.util.ListAdapter
import com.josus.shoppingapp.util.Resource


class HomeFragment : Fragment() {

    private var viewModel: ProductListViewModel? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    lateinit var productListAdapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).productViewModel
        initObservers()
        setUpRecyclerView()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.filter_byPrice -> {
            try {
                viewModel?.productListByPrice?.observe(viewLifecycleOwner, Observer { list ->
                    productListAdapter.differ.submitList(list)
                })

            } catch (e: Exception) {
                showToast(e.toString(), 0)
            }

            true
        }
        R.id.filter_byTitle -> {
            try {
                viewModel?.productListByName?.observe(viewLifecycleOwner, Observer { list ->
                    productListAdapter.differ.submitList(list)
                })

            } catch (e: Exception) {
                showToast(e.toString(), 1)
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    private fun initObservers() {
        viewModel?.productList?.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    productListAdapter.differ.submitList(response.data!!)
                }
            }
        })

    }

    private fun setUpRecyclerView() {
        productListAdapter = ListAdapter(requireContext())
        binding.rvProductList.apply {
            adapter = productListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun hideProgressBar() {
        binding.pbProductList.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.pbProductList.visibility = View.VISIBLE
    }

    private fun showToast(msg: String, dur: Int) {
        Toast.makeText(requireContext(), msg, dur).show()
    }
}