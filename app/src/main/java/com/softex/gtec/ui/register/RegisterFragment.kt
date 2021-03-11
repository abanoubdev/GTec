package com.softex.gtec.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentRegisterBinding
import com.softex.gtec.extensions.snackbarShort
import com.softex.gtec.model.Country
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels()

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {

        _binding?.signIn1?.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).popBackStack()
        }

        _binding?.signIn2?.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).popBackStack()
        }

        _binding?.signUp?.setOnClickListener {
            viewModel.setStateEvent(RegisterStateEvent.Register)
        }

        _binding?.etName?.addTextChangedListener {
            viewModel.name = it.toString()
        }

        _binding?.etUsernameOrEmail?.addTextChangedListener {
            viewModel.usernameOrEmail = it.toString()
        }

        _binding?.etPassword?.addTextChangedListener {
            viewModel.password = it.toString()
        }
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            if (dataState != null) {
                when (dataState) {
                    is DataState.Success<Int?> -> {
                        displayLoading(false)
                        snackbarShort(getString(R.string.account_created_success))
                        Navigation.findNavController(
                            requireActivity(),
                            R.id.nav_host_fragment
                        ).popBackStack()
                    }

                    is DataState.Error -> {
                        displayLoading(false)
                        displayError(dataState.exception.message)
                    }

                    is DataState.Loading -> {
                        displayLoading(true)
                    }
                }
            }
        })

        viewModel.countriesDataState.observe(viewLifecycleOwner, { dataState ->
            if (dataState != null) {
                when (dataState) {
                    is DataState.Success<List<Country>?> -> {
                        validateCountriesResponse(dataState)
                    }

                    is DataState.Error -> {
                        displayLoading(false)
                        displayError(dataState.exception.message)
                    }

                    is DataState.Loading -> {
                        displayLoading(true)
                    }
                }
            }
        })

        viewModel.errorState.observe(viewLifecycleOwner, {
            when (it) {
                is RegisterStateEvent.InvalidName -> {
                    _binding?.etName?.error =
                        context?.getString(R.string.invalid_name)
                }

                is RegisterStateEvent.InvalidUsernameOrEmail -> {
                    _binding?.etUsernameOrEmail?.error =
                        context?.getString(R.string.invalid_username_or_email)
                }

                is RegisterStateEvent.InvalidPassword -> {
                    _binding?.etPassword?.error =
                        context?.getString(R.string.invalid_password)
                }
                else -> {

                }
            }
        })

        viewModel.setStateEvent(RegisterStateEvent.GetCountriesWithCities)
    }

    private fun validateCountriesResponse(dataState: DataState.Success<List<Country>?>) {
        displayLoading(false)
        val countries = dataState.data
        countries?.let {
            val countriesLabels = mutableListOf<String>()
            repeat(it.size) { index ->
                countriesLabels.add(it[index].Name)
            }
            _binding?.spinnerCountry?.setItems(countriesLabels)
            _binding?.spinnerCountry?.setOnSpinnerItemSelectedListener<String> { _, _, newIndex, text ->
                viewModel.countryId = it[newIndex].ID
                val cities = it[newIndex].cities
                val citiesLabels = mutableListOf<String>()
                cities.let {
                    for (city in cities) {
                        city?.let {
                            citiesLabels.add(city.Name)
                        }
                    }
                }
                _binding?.spinnerCity?.setItems(citiesLabels)
                _binding?.spinnerCity?.setOnSpinnerItemSelectedListener<String> { _, _, cityNewIndex, cityText ->
                    viewModel.cityId = it[newIndex].cities[cityNewIndex]?.ID!!
                }
            }

            viewModel.countryId = -1
            viewModel.cityId = -1
        }
    }

    private fun displayError(message: String?) {
        snackbarShort(message)
    }

    private fun displayLoading(isLoading: Boolean) {
        displayLoadingDialog(isLoading)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.spinnerCountry?.dismiss()
        _binding?.spinnerCity?.dismiss()
        _binding = null
    }
}