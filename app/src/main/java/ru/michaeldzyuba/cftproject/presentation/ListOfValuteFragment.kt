package ru.michaeldzyuba.cftproject.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.michaeldzyuba.cftproject.databinding.FragmentListOfValuteBinding
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
    }

    private fun setRecyclerView(){
        val adapter = ValuteListAdapter(requireActivity())
        binding.recyclerView.adapter = adapter
        viewModel.valuteList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}