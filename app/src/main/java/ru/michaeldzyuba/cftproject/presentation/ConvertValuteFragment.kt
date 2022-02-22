package ru.michaeldzyuba.cftproject.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ru.michaeldzyuba.cftproject.R
import ru.michaeldzyuba.cftproject.ValuteApp
import ru.michaeldzyuba.cftproject.databinding.FragmentConvertValuteBinding
import javax.inject.Inject


class ConvertValuteFragment : Fragment() {

    private val args by navArgs<ConvertValuteFragmentArgs>()

    private var _binding: FragmentConvertValuteBinding? = null
    private val binding: FragmentConvertValuteBinding
        get() = _binding ?: throw RuntimeException("FragmentConvertValuteBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as ValuteApp).component
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ConvertValuteViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConvertValuteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setInputLayoutChangeListener()
        observeViewModel()
    }

    private fun setData() {
        val valuteItem = args.valute
        val title = getString(R.string.valuteName)
        val valuteCost = getString(R.string.valute_value)
        with(binding) {
            with(valuteItem) {
                valuteName.text = String.format(title, name, charCode)
                valuteValue.text = String.format(valuteCost, value)
                editTextValute.setText(String.format("%.2f", value))
                convertValute()
            }
        }
    }

    private fun setInputLayoutChangeListener() {
        binding.editTextValute.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorInputRubles()
                convertValute()
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun observeViewModel() {
        viewModel.resultValute.observe(viewLifecycleOwner) {
            binding.valuteResult.text = String.format("%.2f %s", it, args.valute.charCode)
        }
        viewModel.errorInputRubles.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_value)
            } else {
                null
            }
            binding.textInputLayoutValute.error = message
        }
    }

    private fun convertValute() {
        val inputValute = binding.editTextValute.text?.toString()
        val valuteCost = args.valute.value
        viewModel.convertValute(inputValute, valuteCost)
    }
}