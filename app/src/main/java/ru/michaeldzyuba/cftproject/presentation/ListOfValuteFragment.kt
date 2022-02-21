package ru.michaeldzyuba.cftproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.michaeldzyuba.cftproject.R
import ru.michaeldzyuba.cftproject.databinding.FragmentListOfValuteBinding
import ru.michaeldzyuba.cftproject.domain.ValuteItem
import ru.michaeldzyuba.cftproject.presentation.adapter.ValuteListAdapter


class ListOfValuteFragment : Fragment() {

    private var _binding: FragmentListOfValuteBinding? = null
    private val binding: FragmentListOfValuteBinding
        get() = _binding ?: throw RuntimeException("FragmentListOfValuteBinding == null")

    private val viewModel by lazy {
        ViewModelProvider(this)[ListOfValuteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfValuteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        checkInternet()
    }

    private fun setRecyclerView() {
        val adapter = ValuteListAdapter(requireActivity())
        binding.recyclerView.adapter = adapter
        viewModel.valuteList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.refreshLayout.isRefreshing = false
        }
        adapter.onValuteClickListener = object : ValuteListAdapter.OnValuteClickListener {
            override fun onValuteClick(valuteItem: ValuteItem) {
                findNavController().navigate(
                    ListOfValuteFragmentDirections
                        .actionListOfCurrenciesFragmentToConvertValuteFragment(valuteItem)
                )
            }
        }
        binding.refreshLayout.setOnRefreshListener {
            viewModel.loadData()
        }
    }

    private fun checkInternet() {
        viewModel.internetToast.observe(viewLifecycleOwner) {
            if(it){
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.connect_internet),
                    Toast.LENGTH_SHORT
                ).show()
                binding.refreshLayout.isRefreshing = false
                viewModel.resetInternetToast()
            }
        }
    }
}